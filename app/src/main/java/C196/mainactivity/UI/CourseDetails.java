package C196.mainactivity.UI;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import C196.mainactivity.Adapters.CourseDetailsAssessmentsAdapter;
import C196.mainactivity.Database.Repository;
import C196.mainactivity.Entity.Assessment;
import C196.mainactivity.Entity.Course;
import C196.mainactivity.R;

/**
 * Add drop down menu for course status
 * Add share notes feature
 * Add checkbox logic
 */
public class CourseDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText courseName;
    Spinner courseStatus;
    TextView courseStartDate;
    CheckBox courseStartDateAlert;
    TextView courseEndDate;
    CheckBox courseEndDateAlert;
    EditText courseNotes;
    EditText courseInstructorName;
    EditText courseInstructorPhone;
    EditText courseInstructorEmail;

    Repository repository = new Repository(getApplication());

    Button courseShareNotesButton;
    Button courseSaveButton;


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
    boolean startDateAlert;
    boolean endDateAlert;

    int courseID;
    int assessmentID;
    Course course;


    List<Assessment> courseDetailsAssessmentList = new ArrayList<>();
    List<Integer> assessmentsSelected = new ArrayList<>();
    List<Assessment> fullAssessmentList = repository.getmAllAssessments();

    @Override
    public ComponentName getComponentName() {
        return super.getComponentName();
    }

    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_courses_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        courseID = getIntent().getIntExtra("courseID", -1);
        //assessmentID = getIntent().getIntExtra("assessmentID", -1);

        courseName = findViewById(R.id.courseDetailsCourseTitle);
        name = getIntent().getStringExtra("courseName");
        courseName.setText(name);


        courseStartDate = findViewById(R.id.courseDetailsCourseStartDate);
        startDate = getIntent().getStringExtra("courseStartDate");
        courseStartDate.setText(startDate);
        courseStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(CourseDetails.this, android.R.style.Theme_Holo_Light_Dialog, startDateListener, year, month, day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        startDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                String textViewDueDate = month + "/" + day + "/" + year;
                courseStartDate.setText(textViewDueDate);
            }
        };

        courseStartDateAlert = findViewById(R.id.courseDetailsCourseStartDateAlertCheckBox);
        startDateAlert = getIntent().getBooleanExtra("courseStartDateAlert", false);
        if (startDateAlert == true) {
            courseStartDateAlert.setChecked(true);
        }

        courseEndDateAlert = findViewById(R.id.courseDetailsCourseEndDateAlertCheckBox);
        endDateAlert = getIntent().getBooleanExtra("courseEndDateAlert", false);
        if (endDateAlert == true) {
            courseEndDateAlert.setChecked(true);
        }


        courseEndDate = findViewById(R.id.courseDetailsCourseEndDate);
        endDate = getIntent().getStringExtra("courseEndDate");
        courseEndDate.setText(endDate);
        courseEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(CourseDetails.this, android.R.style.Theme_Holo_Light_Dialog, endDateListener, year, month, day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        endDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                String textViewDueDate = month + "/" + day + "/" + year;
                courseEndDate.setText(textViewDueDate);
            }
        };


        /**
         *
         * */
        RecyclerView recyclerViewAssessments = findViewById(R.id.recyclerViewCourseAssessmentList);

        courseDetailsAssessmentList = repository.getmAllAssessments();


        final CourseDetailsAssessmentsAdapter courseDetailsAssessmentsAdapter = new CourseDetailsAssessmentsAdapter(this);
        recyclerViewAssessments.setAdapter(courseDetailsAssessmentsAdapter);
        recyclerViewAssessments.setLayoutManager(new LinearLayoutManager(this));
        courseDetailsAssessmentsAdapter.setCourseDetailsAssessmentArray(courseDetailsAssessmentList);

        courseNotes = findViewById(R.id.courseDetailsCourseNotesMultiLineText);
        notes = getIntent().getStringExtra("courseShareNotes");
        courseNotes.setText(notes);

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

        Spinner spinnerCourseStatus = findViewById(R.id.courseDetailsCourseStatus);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.courseStatus, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourseStatus.setAdapter(adapter);
        spinnerCourseStatus.setOnItemSelectedListener(this);

        if (status != null) {
            if (status.equals("In progress")) {
                spinnerCourseStatus.setSelection(0);
            } else {
                if (status.equals("Completed")) {
                    spinnerCourseStatus.setSelection(1);
                } else {
                    if (status.equals("Dropped")) {
                        spinnerCourseStatus.setSelection(2);
                    } else {
                        spinnerCourseStatus.setSelection(3);
                    }
                }
            }
        }

        courseSaveButton = findViewById(R.id.courseDetailsSaveButton);

        courseSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String spinnerText = spinnerCourseStatus.getSelectedItem().toString();

                assessmentsSelected = CourseDetailsAssessmentsAdapter.getSelectedAsssessmentIDs();

                for (int element : assessmentsSelected) {
                    assessmentID = courseID;
                }

                if (courseID == -1) {
                    course = new Course(0, courseName.getText().toString(), spinnerText, courseStartDate.getText().toString(), courseEndDate.getText().toString(), courseNotes.getText().toString(), courseInstructorName.getText().toString(), courseInstructorPhone.getText().toString(), courseInstructorEmail.getText().toString(), courseStartDateAlert.isChecked(), courseEndDateAlert.isChecked(), courseID);
                    repository.insert(course);

                    onBackPressed();
                } else {
                    course = new Course(courseID, courseName.getText().toString(), spinnerText, courseStartDate.getText().toString(), courseEndDate.getText().toString(), courseNotes.getText().toString(), courseInstructorName.getText().toString(), courseInstructorPhone.getText().toString(), courseInstructorEmail.getText().toString(), courseStartDateAlert.isChecked(), courseEndDateAlert.isChecked(), courseID);
                    repository.update(course);

                    onBackPressed();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, CoursesList.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}