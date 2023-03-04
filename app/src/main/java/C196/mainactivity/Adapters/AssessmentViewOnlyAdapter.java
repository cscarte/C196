package C196.mainactivity.Adapters;

import android.content.Context;
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

public class AssessmentViewOnlyAdapter extends RecyclerView.Adapter<AssessmentViewOnlyAdapter.AssessmentViewHolder> {
    List<Assessment> assessmentList = new ArrayList<>();
    private final Context context;
    private final LayoutInflater mInflater;

    public AssessmentViewOnlyAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    class AssessmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView assessmentsItemView;

        public AssessmentViewHolder(@NonNull View itemView) {
            super(itemView);
            assessmentsItemView = itemView.findViewById(R.id.assessmentsDetailsTextView1);
        }
    }

    @NonNull
    @Override
    public AssessmentViewOnlyAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = mInflater.inflate(R.layout.activity_assessments_row, parent, false);
        return new AssessmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentViewOnlyAdapter.AssessmentViewHolder holder, int position) {
        if (assessmentList != null){
            Assessment current = assessmentList.get(position);
            String name = current.getAssessmentTitle();
            holder.assessmentsItemView.setText(name);
        } else {
            holder.assessmentsItemView.setText("No assessment name set");
        }
    }

    @Override
    public int getItemCount() {
        if(assessmentList != null){
            return assessmentList.size();
        } else return 0;
    }

    public void setAssessmentList(List<Assessment> assessmentList){
        this.assessmentList = assessmentList;
    }
}
