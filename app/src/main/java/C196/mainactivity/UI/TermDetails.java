package C196.mainactivity.UI;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import C196.mainactivity.Adapters.CoursesAdapter;
import C196.mainactivity.Adapters.TermsAdapter;
import C196.mainactivity.Database.Repository;
import C196.mainactivity.Entity.Course;
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

    final Calendar calendarStartDate = Calendar.getInstance();
    final Calendar calendarEndDate = Calendar.getInstance();

    private DatePickerDialog.OnDateSetListener termStartDateListener;
    private DatePickerDialog.OnDateSetListener termEndDateListener;

    Term term;
    Repository repository = new Repository(getApplication());

    List<Course> courseList = new ArrayList<>();

    RecyclerView recyclerView;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_details);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        String dateFormat = "MM/dd/yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

        termID = getIntent().getIntExtra("termID", -1);

        termTitle = findViewById(R.id.termDetailsTermTitle);
        title = getIntent().getStringExtra("termTitle");
        termTitle.setText(title);

        termStartDate = findViewById(R.id.termDetailsTermStartDate);
        stringStartDate = getIntent().getStringExtra("termStartDate");
        termStartDate.setText(stringStartDate);
        termStartDate.setOnClickListener(view -> {
            String termStartDateString = termStartDate.getText().toString();

            try {
                calendarStartDate.setTime(Objects.requireNonNull(simpleDateFormat.parse(termStartDateString)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            new DatePickerDialog(TermDetails.this, termStartDateListener, calendarStartDate.get(Calendar.YEAR), calendarStartDate.get(Calendar.MONTH), calendarStartDate.get(Calendar.DAY_OF_MONTH)).show();
        });

        termStartDateListener = (datePicker, year, month, day) -> {
            calendarStartDate.set(Calendar.YEAR, year);
            calendarStartDate.set(Calendar.MONTH, month);
            calendarStartDate.set(Calendar.DAY_OF_MONTH, day);

            updateLabelStart();
        };

        termEndDate = findViewById(R.id.termDetailsTermEndDate);
        stringEndDate = getIntent().getStringExtra("termEndDate");
        termEndDate.setText(stringEndDate);
        termEndDate.setOnClickListener(View -> {
            String termEndDateString = termEndDate.getText().toString();

            try {
                calendarEndDate.setTime(Objects.requireNonNull(simpleDateFormat.parse(termEndDateString)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            new DatePickerDialog(TermDetails.this, termEndDateListener, calendarEndDate.get(Calendar.YEAR), calendarEndDate.get(Calendar.MONTH), calendarEndDate.get(Calendar.DAY_OF_MONTH)).show();
        });

        termEndDateListener = (datePicker, year, month, day) -> {
            calendarEndDate.set(Calendar.YEAR, year);
            calendarEndDate.set(Calendar.MONTH, month);
            calendarEndDate.set(Calendar.DAY_OF_MONTH, day);

            updateLabelEnd();
        };

        courseList = repository.getmAllCourses();
        List<Course> associatedCourseList = new ArrayList<>();

        for (Course course : courseList) {
            if (course.getCourseTermID() == termID) {
                associatedCourseList.add(course);
            }
        }

        CoursesAdapter coursesAdapter = new CoursesAdapter(this);

        recyclerView = findViewById(R.id.recyclerViewTermCourses);
        recyclerView.setAdapter(coursesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        coursesAdapter.setCourseList(associatedCourseList);
        CoursesAdapter.courseListClickEnabled = false;


        Button saveButton = findViewById(R.id.termDetailsSaveButton);
        saveButton.setOnClickListener(view -> saveTerm());
    }

    @SuppressLint("NotifyDataSetChanged")
    public void saveTerm() {
        if (termID == -1) {
            term = new Term(0, termTitle.getText().toString(), termStartDate.getText().toString(), termEndDate.getText().toString());
            repository.insert(term);

        } else {
            term = new Term(termID, termTitle.getText().toString(), termStartDate.getText().toString(), termEndDate.getText().toString());
            repository.update(term);
        }

        TermsList.termList.clear();
        TermsList.termList.addAll(repository.getmAllTerms());
        TermsList.termsAdapter.notifyDataSetChanged();
        TermsAdapter.termsListClickEnabled = true;
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_termsactionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.termDetailsSaveButton:
                saveTerm();
                return true;
            case R.id.termDeleteButton:
                Term currentTerm = null;
                int numberOfCourses = 0;

                for (Term term : repository.getmAllTerms()){
                    if (term.getTermID() == termID){
                        currentTerm = term;

                        for (Course course : repository.getmAllCourses()){
                            if (course.getCourseTermID() == termID){
                                numberOfCourses++;
                            }
                        }

                        if (numberOfCourses < 1){
                            repository.delete(currentTerm);
                            Toast.makeText(TermDetails.this, currentTerm.getTermTitle() + " was deleted", Toast.LENGTH_LONG).show();
                            TermsList.termList.clear();
                            TermsList.termList.addAll(repository.getmAllTerms());
                            TermsList.termsAdapter.notifyDataSetChanged();
                            finish();
                        } else {
                            Toast.makeText(TermDetails.this, "Cannot delete term with courses assigned to it.", Toast.LENGTH_LONG).show();
                        }
                    }




                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        TermsAdapter.termsListClickEnabled = true;
    }

    private void updateLabelStart() {
        String formattedStartDate = "MM/dd/yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formattedStartDate, Locale.US);

        termStartDate.setText(simpleDateFormat.format(calendarStartDate.getTime()));
    }

    private void updateLabelEnd() {
        String formattedEndDate = "MM/dd/yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formattedEndDate, Locale.US);

        termEndDate.setText(simpleDateFormat.format(calendarEndDate.getTime()));
    }
}
