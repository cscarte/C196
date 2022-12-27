package C196.mainactivity.UI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import C196.mainactivity.R;

public class AssessmentDetails extends AppCompatActivity {
    EditText assessmentTitle;
    EditText assessmentStatus;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessments_details);

        assessmentTitle = findViewById(R.id.editTextAssessmentTitle);
        assessmentStatus = findViewById()
    }
}
