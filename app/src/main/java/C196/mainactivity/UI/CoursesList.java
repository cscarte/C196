package C196.mainactivity.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import C196.mainactivity.Adapters.CoursesAdapter;
import C196.mainactivity.Database.Repository;
import C196.mainactivity.Entity.Course;
import C196.mainactivity.R;

public class CoursesList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.coursesRecyclerView);
        Repository repository = new Repository(getApplication());

        List<Course> courseList = repository.getmAllCourses();

        final CoursesAdapter adapter = new CoursesAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void enterCourseDetails(View view){
        Intent intent = new Intent(CoursesList.this, CourseDetails.class);
        startActivity(intent);
    }
}