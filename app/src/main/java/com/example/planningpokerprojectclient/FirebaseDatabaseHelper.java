package com.example.planningpokerprojectclient;

import com.example.planningpokerprojectclient.Model.Question;
import com.example.planningpokerprojectclient.Model.UserVote;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.UUID;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;


    public FirebaseDatabaseHelper(){
    }

    public void readAllQuestions(String groupName, final String questionText, final List<UserVote> voteList){
        mReference = FirebaseDatabase.getInstance().getReference();//ezzel ferunk hozza
        mReference.child("groups")
                .child(groupName)
                .child("questions")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //ez csinal egy masolatot es itt toltom le az adatokat
                        for (DataSnapshot iter : dataSnapshot.getChildren()) {/**/
                            Question question = iter.getValue(Question.class);
                            if (question.getQuestionText().equals(questionText)){
                                for (UserVote user : question.getUser_resp()){
                                    if (user != null) {
                                        voteList.add(user);
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //ha nem sikerul az adat lekerdezes
                        throw databaseError.toException();
                    }
                });
    }

    public void addVote(UserVote vote,String groupName, final String questionText)
    {
        String cQuestionID = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        String cUser = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();

        DatabaseReference addQuestionDatabaseReference = FirebaseDatabase.getInstance()
                .getReference("groups").child(groupName).child("questions").child(Globals.getInstance().getGroupName());
        addQuestionDatabaseReference.child("user_resp").child(cUser).child("name").setValue(vote.getName());
        addQuestionDatabaseReference.child("user_resp").child(cUser).child("value").setValue(vote.getValue());

        Globals.getInstance().setLastVotedQuestion(questionText);
    }

    public String getActiveQuestionByGroup() {
        return Globals.getInstance().getQuestionText();
    }
}
