package C196.mainactivity.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import C196.mainactivity.R;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enterCoursesScreen(View view){
        Intent intent = new Intent(HomeScreen.this, Courses.class);
        startActivity(intent);
    }

    public void enterMentorsScreen(View view){
        Intent intent = new Intent(HomeScreen.this, Mentors.class);
        startActivity(intent);
    }
/**
    public void enterProfessorsScreen(View view){
        Intent intent = new Intent(HomeScreen.this, Professors.class);
        startActivity(intent);
    }
*/

    public void enterTermsScreen(View view){
        Intent intent = new Intent(HomeScreen.this, Terms.class);
        startActivity(intent);
    }

    public void enterAssessmentsScreen(View view){
        Intent intent = new Intent(HomeScreen.this, Terms.class);
        startActivity(intent);
    }
}