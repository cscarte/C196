package C196.mainactivity.UI;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import C196.mainactivity.R;

public class TermDetails extends AppCompatActivity {
    EditText termTitle;
    EditText termStartDate;
    EditText termEndDate;

    int termID;
    String title;
    String startDate;
    String endDate;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_details);

        termID = getIntent().getIntExtra("termID",-1);

        termTitle = findViewById(R.id.termDetailsTermTitle);
        title = getIntent().getStringExtra("termTitle");
        termTitle.setText(title);

        termStartDate = findViewById(R.id.termDetailsTermStartDate);
        startDate = getIntent().getStringExtra("termStartDate");
        termStartDate.setText(startDate);

        termEndDate = findViewById(R.id.termDetailsTermEndDate);
        endDate = getIntent().getStringExtra("termEndDate");
        termEndDate.setText(endDate);
    }
}
