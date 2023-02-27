package C196.mainactivity.UI;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import C196.mainactivity.Database.Repository;
import C196.mainactivity.Entity.Assessment;
import C196.mainactivity.Entity.Course;
import C196.mainactivity.R;

public class AssessmentDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText assessmentTitle;
    private TextView assessmentDueDate;
    private TextView assessmentGoalDate;
    private CheckBox assessmentGoalDateAlert;

    private Course course;

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch assessmentObjectiveSwitch;

    private DatePickerDialog.OnDateSetListener startDateListener;
    private DatePickerDialog.OnDateSetListener endDateListener;
    boolean goalDateAlert;
    boolean assessmentObjectiveBooleanValue;

    private int assessmentID;
    private int originalCourseID;
    private String selectedCourseIDString;
    private int selectedCourseID;

    private Spinner courseIDSpinner;

    private final Repository repository = new Repository(getApplication());
    List<Course> courseList = repository.getmAllCourses();
    ArrayList<Course> courseArrayList = new ArrayList<>();

    Assessment assessment;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessments_details);

        courseIDSpinner = findViewById(R.id.spinnerCourseID);

        assessmentID = getIntent().getIntExtra("assessmentID", -1);
        originalCourseID = getIntent().getIntExtra("courseID", -1);

        assessmentTitle = findViewById(R.id.assessmentDetailsTitle);
        String name = getIntent().getStringExtra("assessmentTitle");
        assessmentTitle.setText(name);

        assessmentDueDate = findViewById(R.id.assessmentDetailsDueDate);
        String dueDate = getIntent().getStringExtra("assessmentDueDate");
        assessmentDueDate.setText(dueDate);
        //Create and populate date picker menu when clicking on the due date
        assessmentDueDate.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(AssessmentDetails.this, android.R.style.Theme_Holo_Light_Dialog, startDateListener, year, month, day);

            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            datePickerDialog.show();
        });


        //////////////////////////////////////////////////////////////////////////
        //Setting up spinner data for course IDs from course Entity


        courseArrayList.addAll(courseList);

        final ArrayAdapter<Course> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courseArrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        courseIDSpinner.setAdapter(adapter);
        courseIDSpinner.setOnItemSelectedListener(this);

        courseIDSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                course = (Course) adapterView.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //////////////////////////////////////////////////////////////////////////

        startDateListener = (datePicker, year, month, day) -> {
            month = month + 1;

            String textViewDueDate = month + "/" + day + "/" + year;
            assessmentDueDate.setText(textViewDueDate);
        };

        assessmentGoalDate = findViewById(R.id.assessmentDetailsGoalDate);
        String goalDate = getIntent().getStringExtra("assessmentGoalDate");
        assessmentGoalDate.setText(goalDate);
        assessmentGoalDate.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(AssessmentDetails.this, android.R.style.Theme_Holo_Light_Dialog, endDateListener, year, month, day);

            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            datePickerDialog.show();
        });

        endDateListener = (datePicker, year, month, day) -> {
            month = month + 1;

            String textViewGoalDate = month + "/" + day + "/" + year;
            assessmentGoalDate.setText(textViewGoalDate);
        };

        assessmentGoalDateAlert = findViewById(R.id.dueGoalAlertCheckBox);
        goalDateAlert = getIntent().getBooleanExtra("assessmentGoalDateAlert", false);
        if (goalDateAlert) {
            assessmentGoalDateAlert.setChecked(true);
        }

        assessmentObjectiveSwitch = findViewById(R.id.assessmentDetailsAssessmentTypeSwitch);
        assessmentObjectiveBooleanValue = getIntent().getBooleanExtra("assessmentObjective", false);
        if (assessmentObjectiveBooleanValue) {
            assessmentObjectiveSwitch.setChecked(true);
        }


        Button saveButton = findViewById(R.id.assessmentDetailsSaveButton);
        saveButton.setOnClickListener(view -> saveAssessment());
    }

    public void saveAssessment() {
        Repository repository = new Repository(getApplication());

        course = (Course) courseIDSpinner.getSelectedItem();
        selectedCourseID = course.getCourseID();

        if (assessmentID == -1) {
            assessment = new Assessment(0, assessmentTitle.getText().toString(), assessmentDueDate.getText().toString(), assessmentGoalDate.getText().toString(), goalDateAlert = assessmentGoalDateAlert.isChecked(), assessmentObjectiveBooleanValue = assessmentObjectiveSwitch.isChecked(), selectedCourseID);
            repository.insert(assessment);

        } else {
            assessment = new Assessment(assessmentID, assessmentTitle.getText().toString(), assessmentDueDate.getText().toString(), assessmentGoalDate.getText().toString(), goalDateAlert = assessmentGoalDateAlert.isChecked(), assessmentObjectiveBooleanValue = assessmentObjectiveSwitch.isChecked(), selectedCourseID);
            repository.update(assessment);
        }
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
