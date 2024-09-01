package com.example.gymapplication;

import static com.example.gymapplication.WorkoutsActivity.WORKOUT_KEY;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder>{

    private static final String TAG = "MainActivity";
    private ArrayList<Workout> workouts = new ArrayList<>();
    private Context context;

    public WorkoutAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public WorkoutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Called");
        int currentPosition =  position;
        holder.workoutName.setText(workouts.get(currentPosition).getName());
        holder.description.setText(workouts.get(currentPosition).getShortDescription());
        Glide.with(context)
                .asBitmap()
                .load(workouts.get(currentPosition).getImageUrl())
                .into(holder.workoutImage);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 09/08/2024 find out more about this context attribute
                Intent intent = new Intent(context, WorkoutsActivity.class);
                intent.putExtra(WORKOUT_KEY, workouts.get(currentPosition));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    public void setWorkouts(ArrayList<Workout> workouts) {
        this.workouts = workouts;
    }

    /**
     * view holder class to hold all the elements of the xml file workout_items
     */
    public class ViewHolder extends RecyclerView.ViewHolder  {

        private MaterialCardView parent;

        private TextView workoutName, description;

        private ImageView workoutImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.workoutItem);
            workoutName = itemView.findViewById(R.id.workoutName);
            description = itemView.findViewById(R.id.workoutDescription);
            workoutImage = itemView.findViewById(R.id.workoutImage);
            workoutName.setText(R.string.ismail);

        }
    }
}
