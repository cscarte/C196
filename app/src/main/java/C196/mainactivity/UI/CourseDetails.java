package C196.mainactivity.UI;

import static C196.mainactivity.R.id.courseTermSpinner;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import C196.mainactivity.Adapters.AssessmentViewOnlyAdapter;
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
    final Calendar calendarStartDate = Calendar.getInstance();
    final Calendar calendarEndDate = Calendar.getInstance();

    private DatePickerDialog.OnDateSetListener startDateListener;
    private DatePickerDialog.OnDateSetListener endDateListener;

    String name;
    String status;
    String startDateString;
    Date startDate;
    String endDateString;
    Date endDate;
    String notes;
    String instructorName;
    String instructorPhone;
    String instructorEmail;
    Boolean booleanStartDate;
    Boolean booleanEndDate;

    static CheckBox alertStartDate;
    static CheckBox alertEndDate;

    int courseID;
    int courseTermID;
    int originalTermID;

    Course course;
    Term term;

    List<Assessment> courseDetailsAssessmentList = new ArrayList<>();

    private final Repository repository = new Repository(getApplication());
    List<Term> allTermsList = repository.getmAllTerms();
    ArrayList<Term> termArrayList = new ArrayList<>();

    RecyclerView recyclerView;

    @Override
    public ComponentName getComponentName() {
        return super.getComponentName();
    }

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_courses_details);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        String dateFormat = "MM/dd/yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);

        courseID = getIntent().getIntExtra("courseID", -1);
        originalTermID = getIntent().getIntExtra("courseTermID", -1);

        courseName = findViewById(R.id.courseDetailsCourseTitle);
        name = getIntent().getStringExtra("courseName");
        courseName.setText(name);

        alertStartDate = findViewById(R.id.alertStartDateCheckBox);
        alertEndDate = findViewById(R.id.alertEndDateCheckBox);

        booleanStartDate = getIntent().getBooleanExtra("courseStartDateAlert", false);
        booleanEndDate = getIntent().getBooleanExtra("courseEndDateAlert", false);

        courseStartDate = findViewById(R.id.courseDetailsCourseStartDate);
        courseStartDate.setText(getIntent().getStringExtra("courseStartDate"));
        courseStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dateStartString = courseStartDate.getText().toString();

                try {
                    calendarStartDate.setTime(simpleDateFormat.parse(dateStartString));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(CourseDetails.this, startDateListener, calendarStartDate.get(Calendar.YEAR), calendarStartDate.get(Calendar.MONTH), calendarStartDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        startDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendarStartDate.set(Calendar.YEAR, year);
                calendarStartDate.set(Calendar.MONTH, month);
                calendarStartDate.set(Calendar.DAY_OF_MONTH, day);

                updateLabelStart();
            }
        };


        courseEndDate = findViewById(R.id.courseDetailsCourseEndDate);
        endDateString = getIntent().getStringExtra("courseEndDate");
        courseEndDate.setText(endDateString);
        courseEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dateEndString = courseEndDate.getText().toString();

                try {
                    calendarEndDate.setTime((simpleDateFormat).parse(dateEndString));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(CourseDetails.this, endDateListener, calendarEndDate.get(Calendar.YEAR), calendarEndDate.get(Calendar.MONTH), calendarEndDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        endDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendarEndDate.set(Calendar.YEAR, year);
                calendarEndDate.set(Calendar.MONTH, month);
                calendarEndDate.set(Calendar.DAY_OF_MONTH, day);

                updateLabelEnd();
            }
        };


        courseDetailsAssessmentList = repository.getmAllAssessments();

        courseNotes = findViewById(R.id.courseDetailsCourseNotesMultiLineText);
        notes = getIntent().getStringExtra("courseShareNotes");
        courseNotes.setText(notes);

        courseShareNotesButton = findViewById(R.id.courseDetailsShareNotesButton);
        courseShareNotesButton.setOnClickListener(view -> {
                    shareCourseNotes();
                }
        );

        /////////////////////////////////////////////////////
        termSpinner = findViewById(courseTermSpinner);


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




        List<Assessment> assessmentList = repository.getmAllAssessments();
        List<Assessment> assessmentList1 = new ArrayList<>();

        for (Assessment assessment : assessmentList) {
            if (assessment.getAssessmentCourseID() == courseID) {
                assessmentList1.add(assessment);
            }
        }

        AssessmentViewOnlyAdapter assessmentViewOnlyAdapter = new AssessmentViewOnlyAdapter(this);
        recyclerView = findViewById(R.id.assessmentListRecyclerView);
        recyclerView.setAdapter(assessmentViewOnlyAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentViewOnlyAdapter.setAssessmentList(assessmentList1);

        courseSaveButton = findViewById(R.id.courseDetailsSaveButton);
        courseSaveButton.setOnClickListener(view -> saveCourse());
    }

    @SuppressLint("NotifyDataSetChanged")
    public void saveCourse() {
        Repository repository = new Repository(getApplication());

        String spinnerText = courseStatus.getSelectedItem().toString();
        term = (Term) termSpinner.getSelectedItem();
        courseTermID = term.getTermID();

        String formattedStartDate = "MM/dd/yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formattedStartDate, Locale.US);

        try {
            startDate = simpleDateFormat.parse(courseStartDate.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (courseID == -1) {
            course = new Course(0, courseName.getText().toString(), spinnerText, courseStartDate.getText().toString(), courseEndDate.getText().toString(), courseNotes.getText().toString(), courseInstructorName.getText().toString(), courseInstructorPhone.getText().toString(), courseInstructorEmail.getText().toString(), booleanStartDate, booleanEndDate, courseTermID);
            repository.insert(course);
        } else {
            course = new Course(courseID, courseName.getText().toString(), spinnerText, courseStartDate.getText().toString(), courseEndDate.getText().toString(), courseNotes.getText().toString(), courseInstructorName.getText().toString(), courseInstructorPhone.getText().toString(), courseInstructorEmail.getText().toString(), booleanStartDate, booleanEndDate, courseTermID);
            repository.update(course);
        }

        //scheduleAlertCourseEditor(course);

        CoursesList.courseList.clear();
        CoursesList.courseList.addAll(repository.getmAllCourses());
        CoursesList.coursesAdapter.notifyDataSetChanged();
        CoursesAdapter.courseListClickEnabled = true;
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        recyclerView.setEnabled(false);
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
        switch (item.getItemId()) {
            case R.id.saveActionBarButton:
                saveCourse();
                return true;
            case R.id.shareCourseNotes:
                shareCourseNotes();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CoursesAdapter.courseListClickEnabled = true;
    }


    public void shareCourseNotes() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, courseNotes.getText().toString());
        intent.putExtra(Intent.EXTRA_TITLE, "Share Message");
        intent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(intent, "Share notes from " + courseName.getText().toString());
        startActivity(shareIntent);
    }

    private void updateLabelStart() {
        String formattedStartDate = "MM/dd/yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formattedStartDate, Locale.US);

        courseStartDate.setText(simpleDateFormat.format(calendarStartDate.getTime()));
    }

    private void updateLabelEnd() {
        String formattedStartDate = "MM/dd/yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formattedStartDate, Locale.US);

        courseEndDate.setText(simpleDateFormat.format(calendarEndDate.getTime()));
    }
}