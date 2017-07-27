package com.example.khanh.listenwritedemo.module;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by khanh on 7/19/2017.
 */

public class Section implements Serializable{
    private int id;
    private String lang;
    private String title;
    private String title_translate;
    private String image_url;
    private ArrayList<Practice>phrases;
    private int corrects;

    public Section(int id, String lang, String title, String title_translate, String image_url, ArrayList<Practice> phrases, int corrects) {
        this.id = id;
        this.lang = lang;
        this.title = title;
        this.title_translate = title_translate;
        this.image_url = image_url;
        this.phrases = phrases;
        this.corrects = corrects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_translate() {
        return title_translate;
    }

    public void setTitle_translate(String title_translate) {
        this.title_translate = title_translate;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public ArrayList<Practice> getPhrases() {
        return phrases;
    }

    public void setPhrases(ArrayList<Practice> phrases) {
        this.phrases = phrases;
    }

    public int getCorrects() {
        return corrects;
    }

    public void setCorrects(int corrects) {
        this.corrects = corrects;
    }
}
