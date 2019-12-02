package com.example.planningpokerprojectclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.planningpokerprojectclient.Model.Group;
import com.example.planningpokerprojectclient.Model.Group_Has;
import com.example.planningpokerprojectclient.Model.Question;
import com.example.planningpokerprojectclient.Model.Question_Has;
import com.example.planningpokerprojectclient.Model.UserVote;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;


public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private FirebaseDatabaseHelper databaseHelper = new FirebaseDatabaseHelper();
    private Globals globals = Globals.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_place, new MainFragment());
        ft.commit();
    }

    // On login button click: save the user, load the question if the user has't voted before.
    public void onLoginButtonClick(View view)
    {
        TextView username = view.findViewById(R.id.userNameEditText);
        TextView groupname = view.findViewById(R.id.groupNameEditText);

        globals.setUserName(username.getText().toString());
        globals.setGroupName(groupname.getText().toString());

        /////////////////////////////////////////////////////////////////////

        mReference = FirebaseDatabase.getInstance().getReference(); // reference to the db
        mReference.child("groups")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        boolean isActive = false;
                        boolean alreadyVoted = false;
                        for (DataSnapshot iter : dataSnapshot.getChildren()) {/**/
                            Group_Has group = iter.getValue(Group_Has.class);
                            if (iter.getKey().equals(globals.getGroupName())){
                                isActive = true;
                                if (group.getQuestionList() != null){
                                    Iterator myVeryOwnIterator = group.getQuestionList().keySet().iterator();
                                    while(myVeryOwnIterator.hasNext()) {
                                        String key=(String)myVeryOwnIterator.next();
                                        Question_Has question=(Question_Has)group.getQuestionList().get(key);
                                        if (question != null && question.is_active == 1){
                                            globals.setQuestionText(question.question_txt);
                                            globals.setCurrentQuestion(key);
                                        }
                                    }
                                }
                            }
                        }
                        if (globals.getQuestionText() == globals.getLastVoted())
                        {
                            alreadyVoted = true;
                        }

                        if (isActive && alreadyVoted == false){
                            FragmentManager fm = getSupportFragmentManager();
                            FragmentTransaction ft = fm.beginTransaction();
                            ft.replace(R.id.fragment_place, new VoteFragment());
                            ft.addToBackStack(null);
                            ft.commit();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),
                                    "You have voted.", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // if the query doesn't succeed...
                        throw databaseError.toException();
                    }
                });

        /////////////////////////////////////////////////////////////////////
    }

    // On image clicked: aka user voted. Gets the id of the picture and calls onVote function to insert vote.
    public void onClickVoteImage(View v)
    {
        String vote = v.getResources().getResourceName(v.getId());
        String[] separated = vote.split("/");
        vote = separated[1];
        onVoteClick(vote);
    }

    // Inserts vote and replaces fragment to show votes.
    public void onVoteClick(String vote)
    {
        databaseHelper.addVote(fromVoteToUserVote(vote),globals.getGroupName(),globals.getQuestionText());
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_place, new FragmentRecyclerList());
        ft.addToBackStack(null);
        ft.commit();
    }

    // Converts from vote aka an id into a UserVote object.
    public UserVote fromVoteToUserVote(String vote){
        UserVote userVote = new UserVote();
        userVote.setName(globals.getUserName());
        switch (vote){
            case "one" :
                userVote.setValue(1);
                break;
            case "two":
                userVote.setValue(2);
            case "three" :
                userVote.setValue(3);
                break;
            case "five" :
                userVote.setValue(5);
                break;
            case "eight" :
                userVote.setValue(8);
                break;
            case "thirteen" :
                userVote.setValue(13);
                break;
            case "twentyone" :
                userVote.setValue(21);
                break;
            case "zero" :
                userVote.setValue(-1);
                break;
            default:
                userVote.setValue(-1);
                break;
        }

        return userVote;
    }
}
