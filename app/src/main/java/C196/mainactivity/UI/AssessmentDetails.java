package C196.mainactivity.UI;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
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
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import C196.mainactivity.Adapters.AssessmentsAdapter;
import C196.mainactivity.Database.Repository;
import C196.mainactivity.Entity.Assessment;
import C196.mainactivity.Entity.Course;
import C196.mainactivity.R;

public class AssessmentDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText assessmentTitle;
    private TextView assessmentDueDate;
    private TextView assessmentGoalDate;
    private CheckBox assessmentDueDateAlert;
    private CheckBox assessmentGoalDateAlert;

    private Course course;

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch assessmentObjectiveSwitch;

    private DatePickerDialog.OnDateSetListener endDateListener;
    boolean goalDateAlert;
    boolean dueDateAlert;
    boolean assessmentObjectiveBooleanValue;

    final Calendar calendarDueDate = Calendar.getInstance();
    final Calendar calendarGoalDate = Calendar.getInstance();

    private DatePickerDialog.OnDateSetListener dueDateListener;
    private DatePickerDialog.OnDateSetListener goalDateListener;

    private int assessmentID;

    private Spinner courseIDSpinner;

    private final Repository repository = new Repository(getApplication());
    List<Course> courseList = repository.getmAllCourses();
    ArrayList<Course> courseArrayList = new ArrayList<>();

    String assessmentDueDateString;

    Assessment assessment;

    public AssessmentDetails() {
    }

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessments_details);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        String dateFormat = "MM/dd/yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);

        assessmentID = getIntent().getIntExtra("assessmentID", -1);
        int originalCourseID = getIntent().getIntExtra("assessmentCourseID", -1);

        courseIDSpinner = findViewById(R.id.spinnerCourseID);

        assessmentTitle = findViewById(R.id.assessmentDetailsTitle);
        String name = getIntent().getStringExtra("assessmentTitle");
        assessmentTitle.setText(name);

        assessmentDueDate = findViewById(R.id.assessmentDetailsDueDate);
        String dueDate = getIntent().getStringExtra("assessmentDueDate");
        assessmentDueDate.setText(dueDate);
        //Create and populate date picker menu when clicking on the due date
        assessmentDueDate.setOnClickListener(view -> {
            String dateDueString = assessmentDueDate.getText().toString();
            try {
                calendarDueDate.setTime(Objects.requireNonNull(simpleDateFormat.parse(dateDueString)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            new DatePickerDialog(AssessmentDetails.this, dueDateListener, calendarDueDate.get(Calendar.YEAR), calendarDueDate.get(Calendar.MONTH), calendarDueDate.get(Calendar.DAY_OF_MONTH)).show();
        });

        dueDateListener = (datePicker, year, month, day) -> {
            calendarDueDate.set(Calendar.YEAR, year);
            calendarDueDate.set(Calendar.MONTH, month);
            calendarDueDate.set(Calendar.DAY_OF_MONTH, day);

            updateLabelDue();
        };

        courseArrayList.addAll(courseList);

        final ArrayAdapter<Course> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courseArrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        courseIDSpinner.setAdapter(adapter);

        if (originalCourseID > 0) {
            courseIDSpinner.setSelection(originalCourseID - 1);
        }

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

        assessmentGoalDate = findViewById(R.id.assessmentDetailsGoalDate);

        String goalDate = getIntent().getStringExtra("assessmentGoalDate");
        assessmentGoalDate.setText(goalDate);
        assessmentGoalDate.setOnClickListener(view -> {
            String dateDueString = assessmentGoalDate.getText().toString();
            try {
                calendarGoalDate.setTime(Objects.requireNonNull(simpleDateFormat.parse(dateDueString)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            new DatePickerDialog(AssessmentDetails.this, goalDateListener, calendarGoalDate.get(Calendar.YEAR), calendarGoalDate.get(Calendar.MONTH), calendarGoalDate.get(Calendar.DAY_OF_MONTH)).show();
        });

        goalDateListener = (datePicker, year, month, day) -> {
            calendarGoalDate.set(Calendar.YEAR, year);
            calendarGoalDate.set(Calendar.MONTH, month);
            calendarGoalDate.set(Calendar.DAY_OF_MONTH, day);

            updateLabelGoal();
        };

        /**assessmentDueDateAlert = findViewById(R.id.dueDateAlertCheckBox);
         assessmentGoalDateAlert = findViewById(R.id.dueGoalAlertCheckBox);

         dueDateAlert = getIntent().getBooleanExtra("assessmentDueDateAlert", false);
         if (dueDateAlert) {
         assessmentDueDateAlert.setChecked(true);
         }

         goalDateAlert = getIntent().getBooleanExtra("assessmentGoalDateAlert", false);
         if (goalDateAlert) {
         assessmentGoalDateAlert.setChecked(true);
         }
         */

        assessmentObjectiveSwitch = findViewById(R.id.assessmentDetailsAssessmentTypeSwitch);

        assessmentObjectiveBooleanValue = getIntent().getBooleanExtra("assessmentObjective", false);
        if (assessmentObjectiveBooleanValue) {
            assessmentObjectiveSwitch.setChecked(true);
        }

        Button saveButton = findViewById(R.id.assessmentDetailsSaveButton);
        saveButton.setOnClickListener(view -> saveAssessment());
    }

    @SuppressLint("NotifyDataSetChanged")
    public void saveAssessment() {
        Repository repository = new Repository(getApplication());

        course = (Course) courseIDSpinner.getSelectedItem();
        int selectedCourseID = course.getCourseID();

        if (assessmentID == -1) {
            assessment = new Assessment(0, assessmentTitle.getText().toString(), assessmentDueDateString, assessmentGoalDate.getText().toString(), dueDateAlert, goalDateAlert, assessmentObjectiveBooleanValue = assessmentObjectiveSwitch.isChecked(), selectedCourseID);
            repository.insert(assessment);

        } else {
            assessment = new Assessment(assessmentID, assessmentTitle.getText().toString(), assessmentDueDate.getText().toString(), assessmentGoalDate.getText().toString(), dueDateAlert, goalDateAlert, assessmentObjectiveBooleanValue = assessmentObjectiveSwitch.isChecked(), selectedCourseID);
            repository.update(assessment);
        }

        AssessmentsList.assessmentList.clear();
        AssessmentsList.assessmentList.addAll(repository.getmAllAssessments());
        AssessmentsList.adapter.notifyDataSetChanged();
        AssessmentsAdapter.clickedEnabled = true;
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
        menuInflater.inflate(R.menu.menu_assessmentactionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveAssessmentButton:
                saveAssessment();
                return true;
            case R.id.setAssessmentDueDateAlertButton:
                String dueDateFromTextView = assessmentDueDate.getText().toString();

                String formattedDueDate = "MM/dd/yy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formattedDueDate, Locale.US);

                Date dueDate = null;

                try {
                    dueDate = simpleDateFormat.parse(dueDateFromTextView);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Long triggerDueDateAlert = dueDate.getTime();

                Intent intent = new Intent(AssessmentDetails.this, AlertReceiver.class);
                intent.putExtra("alert", assessmentTitle.getText().toString() + " is due!");

                PendingIntent pendingIntent = PendingIntent.getBroadcast(AssessmentDetails.this, HomeScreen.alertInt++, intent, PendingIntent.FLAG_IMMUTABLE);

                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, triggerDueDateAlert, pendingIntent);
                return true;

            case R.id.setAssessmentGoalDateAlertButton:
                String goalDateFromTextView = assessmentGoalDate.getText().toString();

                String formattedGoalDate = "MM/dd/yy";
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(formattedGoalDate, Locale.US);

                Date goalDate = null;

                try {
                    goalDate = simpleDateFormat2.parse(goalDateFromTextView);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Long triggerGoalDateAlert = goalDate.getTime();

                Intent intent2 = new Intent(AssessmentDetails.this, AlertReceiver.class);
                intent2.putExtra("alert", assessmentTitle.getText().toString() + "'s goal date is " + assessmentGoalDate.getText().toString() + "!");

                PendingIntent pendingIntent2 = PendingIntent.getBroadcast(AssessmentDetails.this, HomeScreen.alertInt++, intent2, PendingIntent.FLAG_IMMUTABLE);

                AlarmManager alarmManager2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager2.set(AlarmManager.RTC_WAKEUP, triggerGoalDateAlert, pendingIntent2);
                return true;
            case R.id.deleteAssessmentMenuButton:
                for (Assessment assessment : repository.getmAllAssessments()) {
                    if (assessment.getAssessmentID() == assessmentID) {
                        Assessment currentAssessment = assessment;
                        repository.delete(currentAssessment);
                    }
                }
                AssessmentsList.assessmentList.clear();
                AssessmentsList.assessmentList.addAll(repository.getmAllAssessments());
                AssessmentsList.adapter.notifyDataSetChanged();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AssessmentsAdapter.clickedEnabled = true;
    }

    private void updateLabelDue() {
        String formattedDueDate = "MM/dd/yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formattedDueDate, Locale.US);

        assessmentDueDate.setText(simpleDateFormat.format(calendarDueDate.getTime()));
    }

    private void updateLabelGoal() {
        String formattedGoalDate = "MM/dd/yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formattedGoalDate, Locale.US);

        assessmentGoalDate.setText(simpleDateFormat.format(calendarGoalDate.getTime()));
    }
}