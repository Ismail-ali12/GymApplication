package com.example.gymapplication;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;


public class Plan implements Parcelable {
    public boolean getTraining;
    private Workout workouts;
    private int minutes;
    private String day;
    private boolean isComplete;

    public Plan(Workout workouts, int minutes, String day, boolean isComplete) {
        this.workouts = workouts;
        this.minutes = minutes;
        this.day = day;
        this.isComplete = isComplete;
    }

    protected Plan(Parcel in) {
        workouts = in.readParcelable(Workout.class.getClassLoader());
        minutes = in.readInt();
        day = in.readString();
        isComplete = in.readByte() != 0;
    }

    public static final Creator<Plan> CREATOR = new Creator<Plan>() {
        @Override
        public Plan createFromParcel(Parcel in) {
            return new Plan(in);
        }

        @Override
        public Plan[] newArray(int size) {
            return new Plan[size];
        }
    };


    public Workout getWorkouts() {
        return workouts;
    }

    public void setWorkouts(Workout workouts) {
        this.workouts = workouts;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "workouts=" + workouts +
                ", minutes=" + minutes +
                ", day='" + day + '\'' +
                ", isComplete=" + isComplete +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {

    }
}
