package com.example.planningpokerprojectclient.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.PropertyName;

import java.util.List;

public class Group_Has {

    @PropertyName("start_time")
    public String start_time;

    @PropertyName("end_time")
    public String end_time;

    @PropertyName("questions")
    public List<Question_Has> questions;

    public Group_Has() {
    }

    public Group_Has(String start_time, String end_time, List<Question_Has> questionList){
        this.start_time = start_time;
        this.end_time = end_time;
        this.questions = questionList;
    }

    public void setStart_time(String start_time) {

        this.start_time = start_time;
    }

    public void setEnd_time(String end_time) {

        this.end_time = end_time;
    }

    public void setQuestions(List<Question_Has> questionList) {

        this.questions = questionList;
    }

    public String getStart_time()
    {
        return this.start_time;
    }

    public String getEnd_time()
    {
        return this.end_time;
    }

    public List<Question_Has> getQuestionList()
    {
        return this.questions;
    }
}

