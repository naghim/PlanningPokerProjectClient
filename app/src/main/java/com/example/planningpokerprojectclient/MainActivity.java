package com.example.planningpokerprojectclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_place, new MainFragment());
        ft.commit();
    }

    public void onLoginButtonClick(View view) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_place, new VoteFragment());
        ft.addToBackStack(null);
        ft.commit();
    }

    public void onClickVoteImage(View v)
    {
        Toast.makeText(v.getContext(),
                "The favorite list would appear on clicking this icon",
                Toast.LENGTH_LONG).show();
    }
}
