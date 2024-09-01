package com.example.gymapplication;

import java.util.ArrayList;

public class Utils {
    private static ArrayList<Workout> workouts;

    private static ArrayList<Plan> plans;

    public static void initWorkouts(){
        if(null == workouts){
            workouts = new ArrayList<>();
            Workout pushUp = new Workout(1, "Push up", "The push-up is a common calisthenics exercise",
                    " beginning from the prone position. By raising and lowering the body using the arms, push-ups exercise the pectoral muscles, triceps, and anterior deltoids",
                    "https://www.realsimple.com/thmb/-PW_i-RMLQ4cj3-okjqccGGUock=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/health-benefits-of-pushups-GettyImages-498315681-7008d40842444270868c88b516496884.jpg");
            workouts.add(pushUp);

            Workout squat = new Workout(2, "Squat", "A squat is a strength exercise",
                    " the trainee lowers their hips from a standing position and then stands back up. During the descent, the hip and knee joints flex while the ankle joint dorsiflexes",
                    "https://assets.vogue.in/photos/5f645595705e820dfee8dfd2/2:3/w_2560%2Cc_limit/squat%2520fitness%2520exercise.jpg");
            workouts.add(squat);
        }
    }

    public static ArrayList<Workout> getWorkouts() {
        return workouts;

    }

    public static boolean addPlan(Plan plan){
        if(null == plans){
            plans = new ArrayList<>();
        }
        return plans.add(plan);
    }

    public static ArrayList<Plan> getPlans() {
        return plans;
    }

    public static boolean removePlan(Plan p){
        return plans.remove(p);
    }
}
