package com.example.langlearn;

import com.google.gson.annotations.SerializedName;

public class Translate {
    @SerializedName("")
    private String lang;
    private String english;
    public Translate(String lang,String english){
        this.lang = lang;
        this.english = english;
    }

    public String get_eng(){
        return english;
    }

    public String get_lang(){
        return lang;
    }
}
