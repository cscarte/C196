package C196.mainactivity.UI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import C196.mainactivity.R;

public class AssessmentDetails extends AppCompatActivity {
    EditText assessmentTitle;
    EditText assessmentDueDate;
    EditText assessmentGoalDate;
    CheckBox assessmentGoalDateAlert;
    Switch assessmentObjective;

    String name;
    String dueDate;
    String goalDate;
    int goalDateAlert;
    int assessmentID;
    int courseID;


    @SuppressLint({"WrongViewCast"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessments_details);

        assessmentID = getIntent().getIntExtra("assessmentID", -1);
        courseID = getIntent().getIntExtra("courseID",-1);

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
        goalDateAlert = getIntent().getIntExtra("assessmentObjective", 0);
    }
}
