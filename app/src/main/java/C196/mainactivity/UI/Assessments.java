package C196.mainactivity.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import C196.mainactivity.R;

public class Assessments extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessments);
    }

    public void enterAssessmentDetails(View view){
        Intent intent = new Intent(Assessments.this, AssessmentDetails.class);
        startActivity(intent);
    }
}
