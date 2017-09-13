package com.jmrp.superhapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Superheros implements Serializable{

    @SerializedName("superheroes") public List<Superhero> superheros;

    public class Superhero implements Serializable{

        @SerializedName("name") public String name;

        @SerializedName("photo") public String photo;
        
        @SerializedName("height") public String height;

        @SerializedName("abilities") public String abilities;

        @SerializedName("power") public String power;

        @SerializedName("groups") public String groups;
    }
}
