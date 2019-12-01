package com.example.planningpokerprojectclient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class VoteFragment extends Fragment {

   private  TextView textView ;
    private FirebaseDatabaseHelper databaseHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        final View view = inflater.inflate(R.layout.vote_fragment, parent, false);
        databaseHelper = new FirebaseDatabaseHelper();

        textView = view.findViewById(R.id.questionTextView);
        textView.setText(databaseHelper.getActiveQuestionByGroup());
        return view;
    }
}
