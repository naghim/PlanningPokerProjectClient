package com.example.planningpokerprojectclient.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.PropertyName;
import com.example.planningpokerprojectclient.Model.User;

import java.util.List;

public class Question implements Parcelable {

    @PropertyName("is_active")
    public int is_active;

    @PropertyName("question_txt")
    public String question_txt;

    @PropertyName("user_resp")
    public List<User> user_resp;

    public Question(){}

    public Question(List<User> user_resp, int is_active, String question_txt){
        this.user_resp = user_resp;
        this.is_active = is_active;
        this.question_txt = question_txt;
    }

    protected Question(Parcel in) {
        is_active = in.readInt();
        question_txt = in.readString();
        user_resp = in.createTypedArrayList(User.CREATOR);//infok atrakasa // osszesurites
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public List<User> getUser_resp()
    {
        return this.user_resp;
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

    public void setuser_resp(List<User> user_resp) {

        this.user_resp = user_resp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(is_active);
        parcel.writeString(question_txt);
        parcel.writeTypedList(user_resp);
    }
}
