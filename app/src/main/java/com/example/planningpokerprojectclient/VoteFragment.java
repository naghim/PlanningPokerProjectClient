package com.example.planningpokerprojectclient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class VoteFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        final View view = inflater.inflate(R.layout.vote_fragment, parent, false);

        return view;
    }

    public void onClickVoteImage(View v)
    {
        Toast.makeText(v.getContext(),
                "The favorite list would appear on clicking this icon",
                Toast.LENGTH_LONG).show();
    }
}
