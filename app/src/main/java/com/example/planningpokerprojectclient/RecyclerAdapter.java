package com.example.planningpokerprojectclient;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planningpokerprojectclient.Model.User;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecycleViewHolderUser> {

    private List<User> mRecyclerItemArrayList;

    public static class RecycleViewHolderUser extends RecyclerView.ViewHolder{

        public TextView mUserNameTextView,mValueTextView, mQuestionTextView;

        public RecycleViewHolderUser(@NonNull View itemView) {
            super(itemView);
            mUserNameTextView = itemView.findViewById(R.id.textName);
            mValueTextView = itemView.findViewById(R.id.textValue);
            mQuestionTextView = itemView.findViewById(R.id.textTask);
        }
    }

    public RecyclerAdapter(List<User> recyclerItemArrayList){
        this.mRecyclerItemArrayList = recyclerItemArrayList;
    }

    @NonNull
    @Override
    public RecyclerAdapter.RecycleViewHolderUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_vote_item, parent, false);
        RecyclerAdapter.RecycleViewHolderUser rvh = new RecyclerAdapter.RecycleViewHolderUser(view);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.RecycleViewHolderUser holder, int position) {
        User currentItem = mRecyclerItemArrayList.get(position);
        if (currentItem !=null){
            holder.mUserNameTextView.setText(currentItem.getName());
            holder.mValueTextView.setText(String.valueOf(currentItem.getValue()));
        }
    }

    @Override
    public int getItemCount() {
        return mRecyclerItemArrayList.size();
    }
}