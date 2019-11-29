package com.example.planningpokerprojectclient;

public class Globals {
    private static Globals instance;

    // Global variable
    private String groupName;

    private String questionText;

    // Restrict the constructor from being instantiated
    private Globals(){}

    public void setGroupName(String data){
        this.groupName=data;
    }
    public String getGroupName(){
        return this.groupName;
    }

    public static synchronized Globals getInstance(){
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
}
