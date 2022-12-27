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
    TextView assessmentID;
    EditText assessmentTitle;
    EditText assessmentStatus;
    EditText assessmentDueDate;
    EditText assessmentGoalDate;
    CheckBox assessmentGoalDateAlert;
    Switch assessmentObjective;
    TextView courseID;


    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessments_details);

        assessmentID = findViewById(R.id.assessmentIDLabelDisplay);
        assessmentTitle = findViewById(R.id.editAssessmentTitle);
        assessmentStatus = findViewById(R.id.editAssessmentStatus);
        assessmentDueDate = findViewById(R.id.editAssessmentDueDate);
        assessmentGoalDate = findViewById(R.id.editAssessmentGoalDate);
        assessmentGoalDateAlert = findViewById(R.id.editGoalDateAlert);
        assessmentObjective = findViewById(R.id.editAssessmentObjective);
        courseID = findViewById(R.id.courseIDLabelDisplay);
    }
}
