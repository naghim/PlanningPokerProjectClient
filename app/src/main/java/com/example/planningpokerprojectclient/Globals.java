package com.example.planningpokerprojectclient;

// Global variables
public class Globals {
    private static Globals instance;

    private String groupName;

    private String questionText;

    private String userName;

    private String lastVotedQuestion;

    public String getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(String currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    private String currentQuestion;

    // Restricts the constructor from being instantiated.
    private Globals(){}

    // Get the instance. It is a singleton class...
    public static Globals getInstance(){
        if(instance==null){
            instance=new Globals();
        }
        return instance;
    }

    // Getters and setters...
    public void setGroupName(String data){
        this.groupName=data;
    }
    public String getGroupName(){ return this.groupName; }
    public void setQuestionText(String text) {
        this.questionText = text;
    }
    public String getQuestionText(){ return this.questionText; }
    public void setUserName(String username) {this.userName = username; }
    public String getUserName() { return this.userName; }
    public String getLastVoted() { return this.lastVotedQuestion; }
    public void setLastVotedQuestion(String vote) {this.lastVotedQuestion = vote;}

}
