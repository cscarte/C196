package C196.mainactivity.UI;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import C196.mainactivity.R;

public class CourseDetails extends AppCompatActivity {
    EditText courseName;
    EditText courseStatus;
    EditText courseStartDate;
    CheckBox courseStartDateAlert;
    EditText courseEndDate;
    CheckBox courseEndDateAlert;
    EditText courseNotes;
    Button  courseShareNotesButton;
    Button courseSaveButton;

    String name;
    String status;
    String startDate;
    String endDate;
    String notes;


    @Override
    public ComponentName getComponentName() {
        return super.getComponentName();
    }

    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_details);

        courseName = findViewById(R.id.courseDetailsCourseTitle);
        name = getIntent().getStringExtra("courseName");
        courseName.setText(name);

        courseStatus = findViewById(R.id.courseDetailsCourseStatus);
        status = getIntent().getStringExtra("courseStatus");
        courseStatus.setText(status);

        courseStartDate = findViewById(R.id.courseDetailsCourseStartDate);
        startDate = getIntent().getStringExtra("courseStartDate");
        courseStartDate.setText(startDate);

        courseEndDate = findViewById(R.id.courseDetailsCourseEndDate);
        endDate = getIntent().getStringExtra("courseEndDate");
        courseEndDate.setText(endDate);

        courseNotes = findViewById(R.id.courseDetailsCourseNotesMultiLineText);
        notes = getIntent().getStringExtra("courseShareNotes");
        courseNotes.setText(notes);
    }
}