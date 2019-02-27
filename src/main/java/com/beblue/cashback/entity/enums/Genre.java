package com.beblue.cashback.entity.enums;

public enum Genre {

    ROCK("Rock"), POP("Pop"), MPB("MPB"), CLASSIC("Clássica");

    private String label;

    Genre(String label){
        this.label = label;
    }

}
