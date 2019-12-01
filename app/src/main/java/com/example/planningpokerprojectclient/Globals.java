package com.example.planningpokerprojectclient;

public class Globals {
    private static Globals instance;

    // Global variable
    private String groupName;

    private String questionText;

    private String userName;

    private String lastVotedQuestion;

    // Restrict the constructor from being instantiated
    private Globals(){}

    public void setGroupName(String data){
        this.groupName=data;
    }
    public String getGroupName(){
        return this.groupName;
    }

    public static Globals getInstance(){
        if(instance==null){
            instance=new Globals();
        }
        return instance;
    }

    public void setQuestionText(String text) {
        this.questionText = text;
    }

    public String getQuestionText(){
        return this.questionText;
    }
    public void setUserName(String username) {this.userName = username; }
    public String getUserName() { return this.userName; }
    public String getLastVoted() { return this.lastVotedQuestion; }
    public void setLastVotedQuestion(String vote) {this.lastVotedQuestion = vote;}
}
