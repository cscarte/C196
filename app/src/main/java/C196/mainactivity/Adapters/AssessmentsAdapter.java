package C196.mainactivity.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import C196.mainactivity.Entity.Assessment;
import C196.mainactivity.R;
import C196.mainactivity.UI.AssessmentDetails;

public class AssessmentsAdapter extends RecyclerView.Adapter<AssessmentsAdapter.AssessmentViewHolder> {
    private List<Assessment> assessmentArrayList;
    private List<Assessment> mAssessments;
    private final Context context;
    private final LayoutInflater mInflater;
    private AdapterView.OnItemClickListener listener;

    class AssessmentViewHolder extends RecyclerView.ViewHolder{
        private final TextView assessmentsItemView;


        private AssessmentViewHolder(View view){
            super(view);
            assessmentsItemView = view.findViewById(R.id.assessmentsDetailsTextView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Assessment current = assessmentArrayList.get(position);
                    Intent intent = new Intent(context, AssessmentDetails.class);
                    intent.putExtra("assessmentID", current.getAssessmentID());
                    intent.putExtra("assessmentTitle", current.getAssessmentTitle());
                    intent.putExtra("assessmentDueDate", current.getAssessmentDueDate());
                    intent.putExtra("assessmentGoalDate", current.getAssessmentGoalDate());
                    intent.putExtra("assessmentObjective", current.isAssessmentObjective());
                    intent.putExtra("courseID", current.getCourseID());
                    context.startActivity(intent);
                }
            });
        }
    }



    public AssessmentsAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public AssessmentsAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = mInflater.inflate(R.layout.activity_assessments_list, parent, false);
        return new AssessmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentViewHolder holder, int position) {
        if (assessmentArrayList != null){
            Assessment current = assessmentArrayList.get(position);
            String name = current.getAssessmentTitle();
            holder.assessmentsItemView.setText(name);
        } else {
            holder.assessmentsItemView.setText("No assessment name set");
        };
    }

    @Override
    public int getItemCount() {
        if(assessmentArrayList != null){
            return assessmentArrayList.size();
        } else return 0;
    }

    public void setAssessmentArrayList(List<Assessment> assessmentList){
        assessmentArrayList = assessmentList;
        notifyDataSetChanged();
    }
}
