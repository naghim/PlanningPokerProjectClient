package com.example.planningpokerprojectclient.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.PropertyName;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Question_Has {

    public int is_active;

    public String question_txt;

    public HashMap<String, UserVote> user_resp;

    public Question_Has(){}

    public Question_Has(String question_txt, HashMap<String,UserVote> user_resp, int is_active){
        this.user_resp = user_resp;
        this.is_active = is_active;
        this.question_txt = question_txt;
    }

    public HashMap<String,UserVote> getUser_resp()
    {
        return this.user_resp;
    }

    public Collection<UserVote> getUser_vote_resp(){
        return this.user_resp.values();
    }

    public int getIsactive()
    {
        return this.is_active;
    }

    public String getQuestionText()
    {
        return this.question_txt;
    }

    public void setActive(int active) {

        is_active = active;
    }

    public void setQuestionText(String questionText) {

        this.question_txt = questionText;
    }

    public void setuser_resp(HashMap<String,UserVote> user_resp) {

        this.user_resp = user_resp;
    }
}
