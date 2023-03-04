package C196.mainactivity.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import C196.mainactivity.Adapters.TermsAdapter;
import C196.mainactivity.Database.Repository;
import C196.mainactivity.Entity.Term;
import C196.mainactivity.R;

public class TermsList extends AppCompatActivity {
    static TermsAdapter termsAdapter;
    static List<Term> termList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_list);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.termsListRecyclerView);
        Repository repository = new Repository(getApplication());

        termList = repository.getmAllTerms();

        termsAdapter = new TermsAdapter(this);
        recyclerView.setAdapter(termsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        termsAdapter.setTermsArrayList(termList);
    }

    public void enterTermDetails(View view){
        Intent intent = new Intent(TermsList.this, TermDetails.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        TermsAdapter.termsListClickEnabled = true;
    }
}