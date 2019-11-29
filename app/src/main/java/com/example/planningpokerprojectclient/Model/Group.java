package com.example.planningpokerprojectclient.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.PropertyName;

import java.util.List;

public class Group implements Parcelable {

    @PropertyName("start_time")
    public String start_time;

    @PropertyName("end_time")
    public String end_time;

    @PropertyName("questions")
    public List<Question> questions;

    public Group() {
    }

    public Group(String start_time, String end_time, List<Question> questionList){
        this.start_time = start_time;
        this.end_time = end_time;
        this.questions = questionList;
    }

    protected Group(Parcel in) {
        start_time = in.readString();
        end_time = in.readString();
        questions = in.createTypedArrayList(Question.CREATOR);//infok atrakasa // osszesurites
    }

    public static final Creator<Group> CREATOR = new Creator<Group>() {
        @Override
        public Group createFromParcel(Parcel in) {
            return new Group(in);
        }

        @Override
        public Group[] newArray(int size) {
            return new Group[size];
        }
    };

    public void setStart_time(String start_time) {

        this.start_time = start_time;
    }

    public void setEnd_time(String end_time) {

        this.end_time = end_time;
    }

    public void setQuestions(List<Question> questionList) {

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

    public List<Question> getQuestionList()
    {
        return this.questions;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(start_time);
        dest.writeString(end_time);
        dest.writeTypedList(questions);
    }
}

