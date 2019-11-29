package com.example.planningpokerprojectclient;

import androidx.annotation.NonNull;

import com.example.planningpokerprojectclient.Model.Group;
import com.example.planningpokerprojectclient.Model.Question;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private List<Question> questions;

    public interface DataStatus{
        void DataIsLoaded(List<Question> items, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
    }

    public FirebaseDatabaseHelper(){
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("groups");
        questions = new ArrayList<>();
    }

    public void readAllQuestions(final DataStatus dataStatus){
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                questions.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode: dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Question item = keyNode.getValue(Question.class);
                    questions.add(item);
                }

                dataStatus.DataIsLoaded(questions,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addQuestion(Group group, final DataStatus dataStatus){
        String key = mReference.push().getKey();
        mReference.child(key).setValue(group)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsInserted();
                    }
                });
    }

    public void addGroup(String groupName, final DataStatus dataStatus){
        String key = mReference.push().getKey();
        mReference.child(key).setValue(groupName)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsInserted();
                    }
                });
    }

}
