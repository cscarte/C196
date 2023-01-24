package C196.mainactivity.UI;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import C196.mainactivity.Adapters.CoursesAdapter;
import C196.mainactivity.Adapters.TermsAdapter;
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
/**
        DatePickerDialog.OnDateSetListener startDate;
        String startDateString = "MM/dd/yy";
        SimpleDateFormat simpleDateFormatStartDate = new SimpleDateFormat(startDateString);
        termStartDate.setText(stringStartDate);

        DatePickerDialog.OnDateSetListener endDate;
        String endDateString = "MM/dd/yy";
        SimpleDateFormat simpleDateFormatEndDate = new SimpleDateFormat(endDateString);
 */
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

        //final CoursesAdapter

        Button saveButton = findViewById(R.id.termDetailsSaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (termID == -1) {
                    term = new Term(0, termTitle.getText().toString(), stringStartDate, stringEndDate);
                    repository.insert(term);
                    //Toast.makeText(this, "Term has been added to list", Toast.LENGTH_LONG).show();
                } else {
                    term = new Term(termID, termTitle.getText().toString(), stringStartDate, stringEndDate);
                    repository.update(term);
                }
                //TermDetails.super.onRestart();
                onResume();
                finish();
                TermDetails.super.onBackPressed();
            }
        });
    }
/**
 @Override protected void onResume() {
 super.onResume();
 List<Term> allTerms = repository.getmAllTerms();
 RecyclerView recyclerView = findViewById(R.id.termsListRecyclerView);
 final TermsAdapter termsAdapter = new TermsAdapter(this);
 recyclerView.setAdapter(termsAdapter);
 recyclerView.setLayoutManager(new LinearLayoutManager(this));
 termsAdapter.setTermsArrayList(allTerms);
 }
 */
}
