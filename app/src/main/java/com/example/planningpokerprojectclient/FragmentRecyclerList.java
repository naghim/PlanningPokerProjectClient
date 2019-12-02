package com.example.planningpokerprojectclient;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planningpokerprojectclient.Model.Question_Has;
import com.example.planningpokerprojectclient.Model.UserVote;
import com.example.planningpokerprojectclient.Model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentRecyclerList extends Fragment {

    private RecyclerView mMy_recycler_view_users;
    private DatabaseReference databaseReference; // a reference to the database
    private Globals globals;
    private RecyclerAdapter mAdapterUser;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<UserVote> userRecycleArrayList;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        final View view = inflater.inflate(R.layout.recycler_view, parent, false);

        this.globals = Globals.getInstance();
        this.createRecycleList();
        this.mContext = getContext();
        this.buildRecycleView(view);

        return view;
    }

    // Creates the RecylcerList
    public void createRecycleList(){
        this.userRecycleArrayList = new ArrayList<>();
        this.getAllData();
    }

    // Queries the database for the votes.
    public void getAllData(){
        userRecycleArrayList.clear();
        databaseReference = FirebaseDatabase.getInstance().getReference();//ezzel ferunk hozza
        Toast.makeText(this.getContext(), globals.getGroupName(), Toast.LENGTH_LONG);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot iter : dataSnapshot.getChildren()) {/**/
                    Question_Has question = iter.getValue(Question_Has.class);
                    if (question.getUser_vote_resp() != null){
                        for (UserVote user : question.getUser_vote_resp().values()){
                            if (user != null) {
                                userRecycleArrayList.add(user);
                            }
                        }
                    }
                }
                mAdapterUser.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        };

        databaseReference.child("groups")
                .child(globals.getGroupName())
                .child("questions")
                .addValueEventListener(valueEventListener);
    }

    // Builds RecyclerView: Sets layouts and adapter. Sends list with data to the adapter.
    public void buildRecycleView(View view){
        mMy_recycler_view_users = view.findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(this.mContext);
        mAdapterUser = new RecyclerAdapter(this.userRecycleArrayList);

        mMy_recycler_view_users.setLayoutManager(mLayoutManager);
        mMy_recycler_view_users.setAdapter(mAdapterUser);
    }
}