package com.example.gymapplication;

import static com.example.gymapplication.WorkoutsActivity.WORKOUT_KEY;

import static java.lang.String.valueOf;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class PlanRecyclerAdapter extends RecyclerView.Adapter<PlanRecyclerAdapter.ViewHolder> {

    public void setPlans(ArrayList<Plan> plans) {
        this.plans = plans;
        notifyDataSetChanged();
    }

    public interface RemovePlan{
        void onRemovePlanResult(Plan plan);
    }

    private RemovePlan removePlan;

    private ArrayList<Plan> plans = new ArrayList<>();
    private final Context context;
    private String type = "";

    public PlanRecyclerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        int currentPosition = position;
        holder.workoutName.setText(plans.get(currentPosition).getWorkouts().getName());
        holder.workoutDescription.setText(plans.get(currentPosition).getWorkouts().getShortDescription());
        holder.workoutTime.setText(String.valueOf(plans.get(currentPosition).getMinutes()));
        Glide.with(context)
                .asBitmap()
                .load(plans.get(currentPosition).getWorkouts().getImageUrl())
                .into(holder.workoutImage);

        if(plans.get(currentPosition).isComplete()){
            holder.checkedImage.setVisibility(View.VISIBLE);
            holder.unCheckedImage.setVisibility(View.GONE);
        }else {
            holder.checkedImage.setVisibility(View.GONE);
            holder.unCheckedImage.setVisibility(View.VISIBLE);
        }

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WorkoutsActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(WORKOUT_KEY, plans.get(currentPosition).getWorkouts());
                context.startActivity(intent);
            }
        });

        if(type.equals("edit")){
            holder.unCheckedImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context)
                            .setTitle("Finished")
                            .setMessage("Have you finished this plan " + plans.get(position).getWorkouts().getName() + "?")
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    for(Plan p: Utils.getPlans()){
                                        if(p.equals(plans.get(position))){
                                            p.setComplete(true);
                                        }
                                    }
                                    notifyDataSetChanged();
                                }
                            });
                    builder.create().show();
                }
            });

            holder.parent.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context)
                            .setTitle("Remove")
                            .setMessage("Are you sure you want to remove " + plans.get(position).getWorkouts().getName())
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    try{
                                        removePlan = (RemovePlan) context;
                                        removePlan.onRemovePlanResult(plans.get(position));
                                    }catch(ClassCastException e){
                                        e.printStackTrace();
                                    }
                                }
                            });
                    builder.create().show();
                    return false;
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return plans.size();
    }

    public void setType(String type) {
        this.type = type;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView workoutName, workoutTime, workoutDescription;
        private MaterialCardView parent;
        private ImageView workoutImage, unCheckedImage, checkedImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            workoutName = itemView.findViewById(R.id.txtName5);
            workoutTime = itemView.findViewById(R.id.workoutTimeMinutes);
            workoutDescription = itemView.findViewById(R.id.workoutDescription);

            parent = itemView.findViewById(R.id.planItemParent);

            workoutImage = itemView.findViewById(R.id.workoutImage);
            unCheckedImage = itemView.findViewById(R.id.uncheckedImage);
            checkedImage = itemView.findViewById(R.id.checkedImage);
        }
    }
}
