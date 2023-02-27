package C196.mainactivity.UI;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import C196.mainactivity.Database.Repository;
import C196.mainactivity.Entity.Term;
import C196.mainactivity.R;

public class TermDetails extends AppCompatActivity {
    EditText termTitle;
    EditText termStartDate;
    EditText termEndDate;

    int termID;
    String title;
    String stringStartDate;
    String stringEndDate;

    Term term;
    Repository repository;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_details);

        termID = getIntent().getIntExtra("termID", -1);

        termTitle = findViewById(R.id.termDetailsTermTitle);
        title = getIntent().getStringExtra("termTitle");
        termTitle.setText(title);

        termStartDate = findViewById(R.id.termDetailsTermStartDate);
        stringStartDate = getIntent().getStringExtra("termStartDate");
        termStartDate.setText(stringStartDate);

        termEndDate = findViewById(R.id.termDetailsTermEndDate);
        stringEndDate = getIntent().getStringExtra("termEndDate");
        termEndDate.setText(stringEndDate);

        repository = new Repository(getApplication());

        System.out.println(stringStartDate);
        System.out.println(stringEndDate);

        Button saveButton = findViewById(R.id.termDetailsSaveButton);
        saveButton.setOnClickListener(v -> {
            if (termID == -1) {
                term = new Term(0, termTitle.getText().toString(), stringStartDate, stringEndDate);
                repository.insert(term);

                onBackPressed();
            } else {
                term = new Term(termID, termTitle.getText().toString(), stringStartDate, stringEndDate);
                repository.update(term);

                onBackPressed();
            }
        });
    }
}
