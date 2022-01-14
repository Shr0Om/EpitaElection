package com.epita.epitaelection.Model;

import android.graphics.drawable.Drawable;

public class Joueur {
    private String name;
    private int pv;
    private int vote;
    private int gold;
    private int imageSrc;



    public Joueur(String name){
        this.name = name;
        this.pv = 100;
        this.vote = 0;
        this.gold = 140;
        this.imageSrc = 0;
    }

    public int getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(int imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
