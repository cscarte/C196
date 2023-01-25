package C196.mainactivity.UI;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;

import C196.mainactivity.Adapters.AssessmentsAdapter;
import C196.mainactivity.Database.Repository;
import C196.mainactivity.Entity.Assessment;
import C196.mainactivity.R;

public class AssessmentDetails extends AppCompatActivity {
    EditText assessmentTitle;
    TextView assessmentDueDate;
    TextView assessmentGoalDate;
    CheckBox assessmentGoalDateAlert;
    Switch assessmentObjectiveSwitch;

    String name;
    String dueDate;
    private DatePickerDialog.OnDateSetListener startDateListener;
    String goalDate;
    private DatePickerDialog.OnDateSetListener endDateListener;
    boolean goalDateAlert;
    boolean assessmentObjectiveBooleanValue;

    int assessmentID;
    int courseID;

    Assessment assessment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessments_details);

        assessmentID = getIntent().getIntExtra("assessmentID", -1);
        courseID = getIntent().getIntExtra("courseID", -1);

        assessmentTitle = findViewById(R.id.assessmentDetailsTitle);
        name = getIntent().getStringExtra("assessmentTitle");
        assessmentTitle.setText(name);

        assessmentDueDate = findViewById(R.id.assessmentDetailsDueDate);
        dueDate = getIntent().getStringExtra("assessmentDueDate");
        assessmentDueDate.setText(dueDate);
        assessmentDueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AssessmentDetails.this, android.R.style.Theme_Holo_Light_Dialog, startDateListener, year, month, day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        startDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                String textViewDueDate = month + "/" + day + "/" + year;
                assessmentDueDate.setText(textViewDueDate);
            }
        };

        assessmentGoalDate = findViewById(R.id.assessmentDetailsGoalDate);
        goalDate = getIntent().getStringExtra("assessmentGoalDate");
        assessmentGoalDate.setText(goalDate);
        assessmentGoalDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AssessmentDetails.this, android.R.style.Theme_Holo_Light_Dialog, endDateListener, year, month, day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        endDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                String textViewGoalDate = month + "/" + day + "/" + year;
                assessmentGoalDate.setText(textViewGoalDate);
            }
        };


        assessmentGoalDateAlert = findViewById(R.id.assessmentDetailsGoalDateAlertCheckBox);
        goalDateAlert = getIntent().getBooleanExtra("assessmentGoalDate", false);

        Repository repository = new Repository(getApplication());

        Button saveButton = findViewById(R.id.assessmentDetailsSaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (assessmentID == -1) {
                    assessment = new Assessment(0, assessmentTitle.getText().toString(), assessmentDueDate.getText().toString(), assessmentGoalDate.getText().toString(), goalDateAlert, assessmentObjectiveBooleanValue, courseID);
                    repository.insert(assessment);

                    onBackPressed();
                } else {
                    assessment = new Assessment(assessmentID, assessmentTitle.getText().toString(), assessmentDueDate.getText().toString(), assessmentGoalDate.getText().toString(), goalDateAlert, assessmentObjectiveBooleanValue, courseID);
                    repository.update(assessment);


                }
            }
        });
    }

    public boolean assessmentObjectiveBoolean(Switch assessmentObjectiveSwitch) {
        if (assessmentObjectiveSwitch.isChecked()) {
            assessmentObjectiveBooleanValue = true;
        } else {
            assessmentObjectiveBooleanValue = false;
        }
        return assessmentObjectiveBooleanValue;
    }
}