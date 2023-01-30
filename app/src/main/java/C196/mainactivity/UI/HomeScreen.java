package C196.mainactivity.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import C196.mainactivity.Database.Repository;
import C196.mainactivity.Entity.Assessment;
import C196.mainactivity.Entity.Course;
import C196.mainactivity.Entity.Term;
import C196.mainactivity.R;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void EnterHere(View view){
        Repository repository = new Repository(getApplication());
        Term term = new Term(0, "Intro To School", "12/01/22", "12/31/22");
        //Course course = new Course(0, "Sample course", "In-progress", "12/01/2022", "12/30/2022", "Sample course notes");
        Assessment assessment = new Assessment(0, "Sample Assessment", "12/23/2022", "12/30/2022", true, false, 1);
        repository.insert(term);
        repository.insert(assessment);
        //repository.insert(course);
    }

    public void SampleAssessmentData(View view){
        Repository repository = new Repository(getApplication());
        Assessment assessment = new Assessment(0, "Test Assessment", "11/01/22", "11/15/22", true, false , 1);
        repository.insert(assessment);
    }

    public void enterCoursesScreen(View view){
        Intent intent = new Intent(HomeScreen.this, CoursesList.class);
        startActivity(intent);
    }
/**
    public void enterMentorsScreen(View view){
        Intent intent = new Intent(HomeScreen.this, MentorsList.class);
        startActivity(intent);
    }
/**
    public void enterProfessorsScreen(View view){
        Intent intent = new Intent(HomeScreen.this, Professors.class);
        startActivity(intent);
    }
*/

    public void enterTermsScreen(View view){
        Intent intent = new Intent(HomeScreen.this, TermsList.class);
        startActivity(intent);
    }

    public void enterAssessmentsScreen(View view){
        Intent intent = new Intent(HomeScreen.this, AssessmentsList.class);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_assessments, menu);
        return true;
    }
}