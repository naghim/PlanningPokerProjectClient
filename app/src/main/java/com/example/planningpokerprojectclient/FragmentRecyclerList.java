package com.example.planningpokerprojectclient;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planningpokerprojectclient.Model.User;
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
    private DatabaseReference databaseReference;//ramutatunk ezzel egy cimre
    private Globals globals = Globals.getInstance();
    private RecyclerAdapter mAdapterUser;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<User> userRecycleArrayList;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        final View view = inflater.inflate(R.layout.recycler_view, parent, false);

        this.createRecycleList();
        this.mContext = getContext();
        this.buildRecycleView(view);

        return view;
    }

    public void createRecycleList(){
        this.userRecycleArrayList = new ArrayList<>();
        this.getAllData();
    }

    public void getAllData(){
        databaseReference = FirebaseDatabase.getInstance().getReference();//ezzel ferunk hozza
        databaseReference.child("groups")
                .child(globals.getGroupName())
                .child("questions")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //ez csinal egy masolatot es itt toltom le az adatokat
                        for (DataSnapshot iter : dataSnapshot.getChildren()) {/**/
                            Question question = iter.getValue(Question.class);
                            if (question.getQuestionText() == globals.getQuestionText()){
                                for (User user : question.getUser_resp()){
                                    if (user != null) {
                                        userRecycleArrayList.add(user);
                                    }
                                }
                            }
                        }

                        mAdapterUser.notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //ha nem sikerul az adat lekerdezes
                        throw databaseError.toException();
                    }
                });
    }

    public void buildRecycleView(View view){
        mMy_recycler_view_users = view.findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(this.mContext);
        mAdapterUser = new RecyclerAdapter(this.userRecycleArrayList);

        mMy_recycler_view_users.setLayoutManager(mLayoutManager);
        mMy_recycler_view_users.setAdapter(mAdapterUser);
    }
}