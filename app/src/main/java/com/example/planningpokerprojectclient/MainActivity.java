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

    public void onLoginButtonClick(View view)
    {
        TextView username = view.findViewById(R.id.userNameEditText);
        TextView groupname = view.findViewById(R.id.groupNameEditText);

        globals.setUserName(username.getText().toString());
        globals.setGroupName(groupname.getText().toString());

        /////////////////////////////////////////////////////////////////////

        mReference = FirebaseDatabase.getInstance().getReference();//ezzel ferunk hozza
        mReference.child("groups")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //ez csinal egy masolatot es itt toltom le az adatokat
                        boolean isActive = false;
                        boolean alreadyVoted = false;
                        for (DataSnapshot iter : dataSnapshot.getChildren()) {/**/
                            Group_Has group = iter.getValue(Group_Has.class);
                            if (iter.getKey().equals(globals.getGroupName())){
                                isActive = true;
                                for (Question_Has question : group.getQuestionList()){
                                    if (question != null && question.is_active == 1){
                                        Globals.getInstance().setQuestionText(question.question_txt);
                                    }
                                }
                            }
                        }
                        if (Globals.getInstance().getQuestionText() == globals.getLastVoted())
                        {
                            alreadyVoted = true;
                        }

                        if (isActive && alreadyVoted == false){
                            FragmentManager fm = getSupportFragmentManager();
                            FragmentTransaction ft = fm.beginTransaction();
                            ft.replace(R.id.fragment_place, new VoteFragment());
                            ft.addToBackStack(null);
                            ft.commit();
                        }else{
                            Toast.makeText(getApplicationContext(),
                                    "This group doesn't exist or it has already expired.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //ha nem sikerul az adat lekerdezes
                        throw databaseError.toException();
                    }
                });

        /////////////////////////////////////////////////////////////////////
    }

    public void onClickVoteImage(View v)
    {
        String vote = v.getResources().getResourceName(v.getId());
        String[] separated = vote.split("/");
        vote = separated[1];
//        Toast.makeText(v.getContext(),
//                "You have selected: " + vote ,
//                Toast.LENGTH_LONG).show();

        onVoteClick(vote);
    }
    public void onVoteClick(String vote)
    {
        databaseHelper.addVote(fromVoteToUserVote(vote),globals.getGroupName(),globals.getQuestionText());
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_place, new FragmentRecyclerList());
        ft.addToBackStack(null);
        ft.commit();
    }

    public UserVote fromVoteToUserVote(String vote){
        UserVote userVote = new UserVote();
        userVote.setName(globals.getUserName());
        switch (vote){
            case "one" :
                userVote.setValue(1);
                break;
            case "three" :
                break;
            case "" :
                break;
        }

        return userVote;
    }
}
