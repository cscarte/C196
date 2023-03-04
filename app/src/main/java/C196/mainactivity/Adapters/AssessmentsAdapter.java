package C196.mainactivity.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import C196.mainactivity.Entity.Assessment;
import C196.mainactivity.R;
import C196.mainactivity.UI.AssessmentDetails;

public class AssessmentsAdapter extends RecyclerView.Adapter<AssessmentsAdapter.AssessmentViewHolder> {
    private List<Assessment> assessmentArrayList = new ArrayList<>();
    private final Context context;
    private final LayoutInflater mInflater;
    private long lastTimeClicked;
    private static final long clickTimeInterval = 300;
    public static boolean clickedEnabled = true;

    public AssessmentsAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    class AssessmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView assessmentsItemView;

        private AssessmentViewHolder(View view) {
            super(view);
            assessmentsItemView = view.findViewById(R.id.assessmentsDetailsTextView1);
            view.setOnClickListener(view1 -> {
                long now = System.currentTimeMillis();
                if (clickedEnabled) {
                    clickedEnabled = false;
                    int position = getAdapterPosition();
                    final Assessment current = assessmentArrayList.get(position);
                    Intent intent = new Intent(context, AssessmentDetails.class);
                    intent.putExtra("assessmentID", current.getAssessmentID());
                    intent.putExtra("assessmentTitle", current.getAssessmentTitle());
                    intent.putExtra("assessmentDueDate", current.getAssessmentDueDate());
                    intent.putExtra("assessmentGoalDate", current.getAssessmentGoalDate());
                    intent.putExtra("assessmentGoalDateAlert", current.isAssessmentGoalDateAlert());
                    intent.putExtra("assessmentObjective", current.isAssessmentObjective());
                    intent.putExtra("assessmentCourseID", current.getAssessmentCourseID());
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View view) {
        }
    }


    @NonNull
    @Override
    public AssessmentsAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_assessments_row, parent, false);
        return new AssessmentViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AssessmentsAdapter.AssessmentViewHolder holder, int position) {
        if (assessmentArrayList != null) {
            Assessment current = assessmentArrayList.get(position);
            String name = current.getAssessmentTitle();
            holder.assessmentsItemView.setText(name);
        } else {
            holder.assessmentsItemView.setText("No assessment name set");
        }
    }

    @Override
    public int getItemCount() {
        if (assessmentArrayList != null) {
            return assessmentArrayList.size();
        } else return 0;
    }

    public void setAssessmentList(List<Assessment> assessmentList) {
        assessmentArrayList = assessmentList;
    }
}