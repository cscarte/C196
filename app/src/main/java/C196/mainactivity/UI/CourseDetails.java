package C196.mainactivity.UI;

import static C196.mainactivity.R.id.assessmentsCourseDetailsRecyclerView;
import static C196.mainactivity.R.id.courseTermSpinner;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ComponentName;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import C196.mainactivity.Adapters.AssessmentsAdapter;
import C196.mainactivity.Adapters.CoursesAdapter;
import C196.mainactivity.Database.Repository;
import C196.mainactivity.Entity.Assessment;
import C196.mainactivity.Entity.Course;
import C196.mainactivity.Entity.Term;
import C196.mainactivity.R;

public class CourseDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText courseName;
    Spinner courseStatus;
    TextView courseStartDate;
    TextView courseEndDate;
    EditText courseNotes;
    EditText courseInstructorName;
    EditText courseInstructorPhone;
    EditText courseInstructorEmail;

    Button courseShareNotesButton;
    Button courseSaveButton;

    Spinner termSpinner;

    private DatePickerDialog.OnDateSetListener startDateListener;
    private DatePickerDialog.OnDateSetListener endDateListener;

    String name;
    String status;
    String startDate;
    String endDate;
    String notes;
    String instructorName;
    String instructorPhone;
    String instructorEmail;

    int courseID;
    int courseTermID;
    int originalTermID;

    Course course;
    Term term;

    List<Assessment> courseDetailsAssessmentList = new ArrayList<>();

    private final Repository repository = new Repository(getApplication());
    List<Term> allTermsList = repository.getmAllTerms();
    ArrayList<Term> termArrayList = new ArrayList<>();

    @Override
    public ComponentName getComponentName() {
        return super.getComponentName();
    }

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_courses_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        courseID = getIntent().getIntExtra("courseID", -1);
        originalTermID = getIntent().getIntExtra("courseTermID", -1);

        courseName = findViewById(R.id.courseDetailsCourseTitle);
        name = getIntent().getStringExtra("courseName");
        courseName.setText(name);


        courseStartDate = findViewById(R.id.courseDetailsCourseStartDate);
        startDate = getIntent().getStringExtra("courseStartDate");
        courseStartDate.setText(startDate);
        courseStartDate.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(CourseDetails.this, android.R.style.Theme_Holo_Light_Dialog, startDateListener, year, month, day);

            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            datePickerDialog.show();
        });

        startDateListener = (datePicker, year, month, day) -> {
            month = month + 1;

            String textViewDueDate = month + "/" + day + "/" + year;
            courseStartDate.setText(textViewDueDate);
        };


        courseEndDate = findViewById(R.id.courseDetailsCourseEndDate);
        endDate = getIntent().getStringExtra("courseEndDate");
        courseEndDate.setText(endDate);
        courseEndDate.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(CourseDetails.this, android.R.style.Theme_Holo_Light_Dialog, endDateListener, year, month, day);

            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            datePickerDialog.show();
        });

        endDateListener = (datePicker, year, month, day) -> {
            month = month + 1;

            String textViewDueDate = month + "/" + day + "/" + year;
            courseEndDate.setText(textViewDueDate);
        };


        courseDetailsAssessmentList = repository.getmAllAssessments();

        courseNotes = findViewById(R.id.courseDetailsCourseNotesMultiLineText);
        notes = getIntent().getStringExtra("courseShareNotes");
        courseNotes.setText(notes);

        /////////////////////////////////////////////////////
        termSpinner = findViewById(R.id.courseTermSpinner);


        termArrayList.addAll(allTermsList);

        final ArrayAdapter<Term> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, termArrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        termSpinner.setAdapter(adapter);

        if (originalTermID > 0) {
            termSpinner.setSelection(originalTermID - 1);
        }

        termSpinner.setOnItemSelectedListener(this);

        termSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                term = (Term) adapterView.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        /////////////////////////////////////////////////////

        courseInstructorName = findViewById(R.id.editTextCourseInstructorName);
        instructorName = getIntent().getStringExtra("courseInstructorName");
        courseInstructorName.setText(instructorName);

        courseInstructorPhone = findViewById(R.id.editTextCourseInstructorPhone);
        instructorPhone = getIntent().getStringExtra("courseInstructorPhone");
        courseInstructorPhone.setText(instructorPhone);

        courseInstructorEmail = findViewById(R.id.editTextCourseInstructorEmail);
        instructorEmail = getIntent().getStringExtra("courseInstructorEmail");
        courseInstructorEmail.setText(instructorEmail);

        courseStatus = findViewById(R.id.courseDetailsCourseStatus);
        status = getIntent().getStringExtra("courseStatus");

        ArrayAdapter<CharSequence> courseStatusAdapter = ArrayAdapter.createFromResource(this, R.array.courseStatus, android.R.layout.simple_spinner_item);
        courseStatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseStatus.setAdapter(courseStatusAdapter);
        courseStatus.setOnItemSelectedListener(this);

        if (status != null) {
            if (status.equals("In progress")) {
                courseStatus.setSelection(0);
            } else {
                if (status.equals("Completed")) {
                    courseStatus.setSelection(1);
                } else {
                    if (status.equals("Dropped")) {
                        courseStatus.setSelection(2);
                    } else {
                        courseStatus.setSelection(3);
                    }
                }
            }
        }

        RecyclerView recyclerView = findViewById(assessmentsCourseDetailsRecyclerView);

        List<Assessment> assessmentList = repository.getmAllAssessments();
        List<Assessment> assessmentList1 = new ArrayList<>();

        for (Assessment assessment : assessmentList) {
            if (assessment.getAssessmentCourseID() == courseID) {
                assessmentList1.add(assessment);
            }
        }

        AssessmentsAdapter assessmentsAdapter = new AssessmentsAdapter(this);
        recyclerView.setAdapter(assessmentsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentsAdapter.setAssessmentList(assessmentList1);


        courseSaveButton = findViewById(R.id.courseDetailsSaveButton);
        courseSaveButton.setOnClickListener(view -> saveCourse());
    }

    public void saveCourse() {
        Repository repository = new Repository(getApplication());

        String spinnerText = courseStatus.getSelectedItem().toString();
        term = (Term) termSpinner.getSelectedItem();
        courseTermID = term.getTermID();

        if (courseID == -1) {
            course = new Course(0, courseName.getText().toString(), spinnerText, courseStartDate.getText().toString(), courseEndDate.getText().toString(), courseNotes.getText().toString(), courseInstructorName.getText().toString(), courseInstructorPhone.getText().toString(), courseInstructorEmail.getText().toString(), courseTermID);
            repository.insert(course);
        } else {
            course = new Course(courseID, courseName.getText().toString(), spinnerText, courseStartDate.getText().toString(), courseEndDate.getText().toString(), courseNotes.getText().toString(), courseInstructorName.getText().toString(), courseInstructorPhone.getText().toString(), courseInstructorEmail.getText().toString(), courseTermID);
            repository.update(course);
        }

        CoursesList.courseList.clear();
        CoursesList.courseList.addAll(repository.getmAllCourses());
        CoursesList.coursesAdapter.notifyDataSetChanged();
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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
            saveCourse();
        }
        return super.onOptionsItemSelected(item);
    }
}