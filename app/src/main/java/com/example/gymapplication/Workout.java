package com.example.gymapplication;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * must implement Parcelable to pass a java class uding intents
 */

public class Workout implements Parcelable {
    private int id;
    private String name;
    private String  shortDescription;
    private String  LongDescription;
    private String imageUrl;

    public Workout(int id, String name, String shortDescription, String longDescription, String imageUrl) {
        this.id = id;
        this.name = name;
        this.shortDescription = shortDescription;
        LongDescription = longDescription;
        this.imageUrl = imageUrl;
    }

    protected Workout(Parcel in) {
        id = in.readInt();
        name = in.readString();
        shortDescription = in.readString();
        LongDescription = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<Workout> CREATOR = new Creator<Workout>() {
        @Override
        public Workout createFromParcel(Parcel in) {
            return new Workout(in);
        }

        @Override
        public Workout[] newArray(int size) {
            return new Workout[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return LongDescription;
    }

    public void setLongDescription(String longDescription) {
        LongDescription = longDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", LongDescription='" + LongDescription + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(shortDescription);
        dest.writeString(LongDescription);
        dest.writeString(imageUrl);
    }
}
