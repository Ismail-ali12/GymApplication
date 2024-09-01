package com.example.gymapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class WorkoutsActivity extends AppCompatActivity implements PlanDetailsDialog.PassPlanInterface{


    private static final String TAG = "WorkoutsActivity";

    public static final String WORKOUT_KEY = "workout";

    private ConstraintLayout parent;
    private Button btnAddToPlan;

    private ImageView workOutImage;
    private TextView workoutName, txtDescription, scrollViewDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_workouts);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();

        Intent intent = getIntent();
        if(null != intent){
            Workout workout = intent.getParcelableExtra(WORKOUT_KEY);

            if(workout != null){
                workoutName.setText(workout.getName());
                scrollViewDescription.setText(workout.getLongDescription());
                Glide.with(this)
                        .asBitmap()
                        .load(workout.getImageUrl())
                        .into(workOutImage);
                btnAddToPlan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PlanDetailsDialog dialog = new PlanDetailsDialog();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(WORKOUT_KEY, workout);
                        dialog.setArguments(bundle);
                        dialog.show(getSupportFragmentManager(), "plan detail dialog");
                    }
                });
            }
        }

    }

    private void initView() {
        Log.d(TAG, "initView started");
        parent = findViewById(R.id.main);
        btnAddToPlan = findViewById(R.id.addToPlan);
        workOutImage = findViewById(R.id.workoutImage);
        workoutName = findViewById(R.id.workoutName1);
        txtDescription = findViewById(R.id.txtDescription);
        scrollViewDescription = findViewById(R.id.longDescription1);
    }

    @Override
    public void getPlan(Plan plan) {
        if(Utils.addPlan(plan)){
            Toast.makeText(this, plan.getWorkouts().getName() + " Added to plan", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, PlanActivity.class);
            startActivity(intent);
        }
    }

}