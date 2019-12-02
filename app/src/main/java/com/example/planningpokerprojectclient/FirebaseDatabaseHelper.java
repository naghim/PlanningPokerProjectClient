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

    // Constructor
    public FirebaseDatabaseHelper(){
    }

    // Inserts a vote into the database.
    public void addVote(UserVote vote,String groupName, final String questionText)
    {
        String cUser = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        DatabaseReference addQuestionDatabaseReference = FirebaseDatabase.getInstance()
                .getReference("groups").child(groupName).child("questions").child(Globals.getInstance().getCurrentQuestion());
        addQuestionDatabaseReference.child("user_resp").child(cUser).child("name").setValue(vote.getName());
        addQuestionDatabaseReference.child("user_resp").child(cUser).child("value").setValue(vote.getValue());
        Globals.getInstance().setLastVotedQuestion(questionText);
    }

    // Returns the active question's text.
    public String getActiveQuestionByGroup() {
        return Globals.getInstance().getQuestionText();
    }
}
