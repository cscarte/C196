package C196.mainactivity.UI;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import C196.mainactivity.Database.Repository;
import C196.mainactivity.Entity.Term;
import C196.mainactivity.R;

public class TermDetails extends AppCompatActivity {
    EditText termTitle;
    TextView termStartDate;
    TextView termEndDate;

    int termID;
    String title;
    String stringStartDate;
    String stringEndDate;

    private DatePickerDialog.OnDateSetListener termStartDateListener;
    private DatePickerDialog.OnDateSetListener termEndDateListener;

    Term term;
    Repository repository;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        termID = getIntent().getIntExtra("termID", -1);

        termTitle = findViewById(R.id.termDetailsTermTitle);
        title = getIntent().getStringExtra("termTitle");
        termTitle.setText(title);

        termStartDate = findViewById(R.id.termDetailsTermStartDate);
        stringStartDate = getIntent().getStringExtra("termStartDate");
        termStartDate.setText(stringStartDate);
        termStartDate.setOnClickListener(View -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(TermDetails.this, android.R.style.Theme_Holo_Light_Dialog, termStartDateListener, year, month, day);
            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            datePickerDialog.show();
        });

        termStartDateListener = (datePicker, year, month, day) -> {
            month = month + 1;

            String textViewTermStartDate = month + "/" + day + "/" + year;
            termStartDate.setText(textViewTermStartDate);
        };

        termEndDate = findViewById(R.id.termDetailsTermEndDate);
        stringEndDate = getIntent().getStringExtra("termEndDate");
        termEndDate.setText(stringEndDate);
        termEndDate.setOnClickListener(View -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(TermDetails.this, android.R.style.Theme_Holo_Light_Dialog, termEndDateListener, year, month, day);
            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            datePickerDialog.show();
        });

        termEndDateListener = (datePicker, year, month, day) -> {
            month = month + 1;

            String textViewTermEndDate = month + "/" + day + "/" + year;
            termStartDate.setText(textViewTermEndDate);
        };

        repository = new Repository(getApplication());

        Button saveButton = findViewById(R.id.termDetailsSaveButton);
        saveButton.setOnClickListener(view -> saveTerm());
    }

    public void saveTerm() {
        Repository repository = new Repository(getApplication());
        if (termID == -1) {
            term = new Term(0, termTitle.getText().toString(), termStartDate.getText().toString(), termEndDate.getText().toString());
            repository.insert(term);

            onBackPressed();
        } else {
            term = new Term(termID, termTitle.getText().toString(), termStartDate.getText().toString(), termEndDate.getText().toString());
            repository.update(term);

            onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_saveactionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.saveActionBarButton) {
            saveTerm();
        }
        return super.onOptionsItemSelected(item);
    }
}
