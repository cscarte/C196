package C196.mainactivity.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import C196.mainactivity.Database.Repository;
import C196.mainactivity.Entity.Assessment;
import C196.mainactivity.Entity.Course;
import C196.mainactivity.Entity.Term;
import C196.mainactivity.R;

public class HomeScreen extends AppCompatActivity {

    List termsList;
    List coursesList;
    List assessmentsList;

    public static int alertInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repository repository = new Repository(getApplication());
        assessmentsList = repository.getmAllAssessments();
        coursesList = repository.getmAllCourses();
        termsList = repository.getmAllTerms();

        int assessmentsListSize = assessmentsList.size();
        int courseListSize = coursesList.size();
        int termListSize = termsList.size();
    }

    public void enterCoursesScreen(View view) {
        Intent intent = new Intent(HomeScreen.this, CoursesList.class);
        startActivity(intent);
    }

    public void enterTermsScreen(View view) {
        Intent intent = new Intent(HomeScreen.this, TermsList.class);
        startActivity(intent);
    }

    public void enterAssessmentsScreen(View view) {
        Intent intent = new Intent(HomeScreen.this, AssessmentsList.class);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_assessments, menu);
        return true;
    }
}