package com.example.memorandum;

import java.util.ArrayList;

public class Note {
    private String title;
    private String text;
    private String type;
    //static public final Note[] notes = {new Note("12333", "我的一天", "学习"),
     //       new Note("12333", "我的ssss一天", "学习"),
     //       new Note("12333", "我的ddd一天", "学习")};

    static ArrayList<Note> notes = new ArrayList<Note>();
    static ArrayList<Note> notesStu = new ArrayList<Note>();
    static ArrayList<Note> notesLife = new ArrayList<Note>();
    static ArrayList<Note> notesOther = new ArrayList<Note>();


    public Note(String title, String text, String type){
        this.title = title;
        this.text = text;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public String getType(){
        return type;
    }

    public String toString() {
        return this.title;
    }

}
