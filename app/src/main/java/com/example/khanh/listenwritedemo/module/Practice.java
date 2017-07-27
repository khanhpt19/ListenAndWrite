package com.example.khanh.listenwritedemo.module;

import java.io.Serializable;

/**
 * Created by khanh on 7/18/2017.
 */

public class Practice implements Serializable{
    private int id;
    private String text;
    private String text_translate;
    private String audio_url;

    public Practice(int id, String text, String text_translate, String audio_url) {
        this.id = id;
        this.text = text;
        this.text_translate = text_translate;
        this.audio_url = audio_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText_translate() {
        return text_translate;
    }

    public void setText_translate(String text_translate) {
        this.text_translate = text_translate;
    }

    public String getAudio_url() {
        return audio_url;
    }

    public void setAudio_url(String audio_url) {
        this.audio_url = audio_url;
    }
}
