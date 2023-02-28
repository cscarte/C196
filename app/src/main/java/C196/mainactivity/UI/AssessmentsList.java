package C196.mainactivity.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import C196.mainactivity.Adapters.AssessmentsAdapter;
import C196.mainactivity.Database.Repository;
import C196.mainactivity.Entity.Assessment;
import C196.mainactivity.R;

public class AssessmentsList extends AppCompatActivity {
    static RecyclerView recyclerView;
    static AssessmentsAdapter adapter;
    static Repository repository;
    static List<Assessment> assessmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessments_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.assessmentsRecyclerView);
        repository = new Repository(getApplication());

        assessmentList = repository.getmAllAssessments();

        adapter = new AssessmentsAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setAssessmentList(assessmentList);
    }

    public void enterAssessmentDetails(View view) {
        Intent intent = new Intent(AssessmentsList.this, AssessmentDetails.class);
        startActivity(intent);
    }


}