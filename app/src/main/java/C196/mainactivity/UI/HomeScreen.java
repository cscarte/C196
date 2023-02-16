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

        if (termListSize == 0){
            Term term = new Term(1, "Intro To School", "01/01/23", "04/01/23");
            Term term1 = new Term(2, "Capstone", "05/01/23", "07/31/23");

            repository.insert(term);
            repository.insert(term1);
        }

        if (courseListSize == 0){
            Course course = new Course(1, "Course 1", "In progress", "02/15/23", "04/01/23", "This is a sample note", "Johnny Doe", "555-555-5555", "fakeemail@gmail.com", true, false, 1);
            Course course1 = new Course(2, "Course 2", "Planned to take", "04/01/23", "05/01/23", "Finish homework on time", "Jack Black", "666-777-8888", "kungfupanda@gmail.com", false, false, 2);

            repository.insert(course);
            repository.insert(course1);
        }

        if (assessmentsListSize == 0){
            Assessment assessment = new Assessment(1, "Assessment 1", "03/01/23", "03/31/23", true, true, 1);
            Assessment assessment1 = new Assessment(2, "Assessment 2", "04/01/23", "04/28/23", false, true, 1);
            Assessment assessment2 = new Assessment(3, "Assessment 3", "05/01/23", "05/15/23", false, false, 2);

            repository.insert(assessment);
            repository.insert(assessment1);
            repository.insert(assessment2);
        }
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