package C196.mainactivity.UI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import C196.mainactivity.R;

public class Courses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
        public boolean onCreateOptionsMenu(Menu menu){
            getMenuInflater().inflate(R.menu.menu_courseslist, menu);
            return true;
        }

        public boolean onOptionsItemSelected(MenuItem menuItem) {
            switch  (menuItem.getItemId()){
                case android.R.id.home:
                    this.finish();
                    return true;
            }
            return super.onOptionsItemSelected(menuItem);
    }

    public void goToCoursesList(View view){
    }
}