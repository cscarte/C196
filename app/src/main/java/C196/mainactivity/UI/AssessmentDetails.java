package C196.mainactivity.UI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import C196.mainactivity.Database.Repository;
import C196.mainactivity.Entity.Assessment;
import C196.mainactivity.R;

public class AssessmentDetails extends AppCompatActivity {
    EditText assessmentTitle;
    EditText assessmentDueDate;
    EditText assessmentGoalDate;
    CheckBox assessmentGoalDateAlert;
    Switch assessmentObjectiveSwitch;

    String name;
    String dueDate;
    String goalDate;
    boolean goalDateAlert;
    boolean assessmentObjectiveBooleanValue;

    int assessmentID;
    int courseID;

    Assessment assessment;

    @SuppressLint({"WrongViewCast"})
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

        assessmentGoalDate = findViewById(R.id.assessmentDetailsGoalDate);
        goalDate = getIntent().getStringExtra("assessmentGoalDate");
        assessmentGoalDate.setText(goalDate);

        assessmentGoalDateAlert = findViewById(R.id.assessmentDetailsGoalDateAlertCheckBox);
        goalDateAlert = getIntent().getBooleanExtra("assessmentGoalDate", false);

        Repository repository = new Repository(getApplication());

        Button saveButton = findViewById(R.id.assessmentDetailsSaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (assessmentID == -1) {
                    assessment = new Assessment(0, assessmentTitle.getText().toString(), assessmentDueDate.toString(), assessmentGoalDate.toString(), goalDateAlert, assessmentObjectiveBooleanValue ,courseID);
                    repository.insert(assessment);
                } else {
                    assessment = new Assessment(0, assessmentTitle.getText().toString(), assessmentDueDate.toString(), assessmentGoalDate.toString(), goalDateAlert, assessmentObjectiveBooleanValue,courseID);
                    repository.update(assessment);
                }
                onResume();
                finish();
                AssessmentDetails.super.onBackPressed();
            }
        });
    }

    public boolean assessmentObjectiveBoolean(Switch assessmentObjectiveSwitch){
        if(assessmentObjectiveSwitch.isChecked()){
            assessmentObjectiveBooleanValue = true;
        } else {
            assessmentObjectiveBooleanValue = false;
        }
        return assessmentObjectiveBooleanValue;
    }
}