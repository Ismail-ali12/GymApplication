package com.example.gymapplication;

import static com.example.gymapplication.WorkoutsActivity.WORKOUT_KEY;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class PlanDetailsDialog extends DialogFragment {

    public interface PassPlanInterface{
        void getPlan(Plan plan) throws InterruptedException;

    }

    private PassPlanInterface passPlanInterface;

    private TextView workoutName;
    private EditText workoutLength;
    private Button btnDimmiss, btnAdd;

    private Spinner spinnerDay;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        /**
         * For a custom xml file you must inflate your viewObject
         */
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_plan_details, null);
        initView(view);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(view);

        Bundle bundle = getArguments();
        if (null != bundle){
            Workout workout = bundle.getParcelable(WORKOUT_KEY);
            if(null != workout){
                workoutName.setText((workout.getName()));
                btnDimmiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String day = spinnerDay.getSelectedItem().toString();
                        int minutes = Integer.valueOf(workoutLength.getText().toString());
                        Plan plan = new Plan(workout, minutes, day, false);

                        try {
                            passPlanInterface = (PassPlanInterface) getActivity();
                            passPlanInterface.getPlan(plan);
                            dismiss();
                        }catch (ClassCastException e){
                            e.printStackTrace();
                            dismiss();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        }
        return builder.create();

    }

    private void initView(View view) {
        workoutName = view.findViewById(R.id.workoutname2);
        workoutLength = view.findViewById(R.id.editMinutes);
        btnDimmiss = view.findViewById(R.id.btnDismiss);
        btnAdd = view.findViewById(R.id.btnAdd);
        spinnerDay = view.findViewById(R.id.daysSpinner);
    }

}
