package C196.mainactivity.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import C196.mainactivity.Adapters.CoursesAdapter;
import C196.mainactivity.Database.Repository;
import C196.mainactivity.Entity.Course;
import C196.mainactivity.R;

public class CoursesList extends AppCompatActivity {
    static RecyclerView recyclerView;
    private Repository repository;
    static CoursesAdapter coursesAdapter;
    static List<Course> courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.coursesRecyclerView);
        repository = new Repository(getApplication());

        courseList = repository.getmAllCourses();

        coursesAdapter = new CoursesAdapter(this);
        recyclerView.setAdapter(coursesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        coursesAdapter.setCourseList(courseList);
    }

    public void enterCoursesDetails(View view){
        Intent intent = new Intent(CoursesList.this, CourseDetails.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        CoursesAdapter.courseListClickEnabled = true;
    }
}