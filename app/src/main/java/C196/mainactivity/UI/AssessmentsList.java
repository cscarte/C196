package C196.mainactivity.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import C196.mainactivity.Adapters.AssessmentsAdapter;
import C196.mainactivity.Database.Repository;
import C196.mainactivity.Entity.Assessment;
import C196.mainactivity.R;

public class AssessmentsList extends AppCompatActivity {
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessments_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.assessmentsRecyclerView);
        repository = new Repository(getApplication());

        List<Assessment> assessmentList = repository.getmAllAssessments();

        final AssessmentsAdapter adapter = new AssessmentsAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setAssessmentArrayList(assessmentList);
    }

    public void enterAssessmentDetails(View view) {
        Intent intent = new Intent(AssessmentsList.this, AssessmentDetails.class);
        startActivity(intent);
    }
}