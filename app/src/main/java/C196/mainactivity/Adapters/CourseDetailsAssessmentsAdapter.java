package C196.mainactivity.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import C196.mainactivity.Entity.Assessment;
import C196.mainactivity.R;
import C196.mainactivity.UI.AssessmentDetails;

public class CourseDetailsAssessmentsAdapter extends RecyclerView.Adapter<CourseDetailsAssessmentsAdapter.CourseDetailsAssessmentViewHolder> {
    Context context;
    ArrayList<Assessment> assessmentArrayList;

    public CourseDetailsAssessmentsAdapter(Context context, ArrayList<Assessment> assessmentArrayList){
        this.context = context;
        this.assessmentArrayList = assessmentArrayList;
    }

    @NonNull
    @Override
    public CourseDetailsAssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseDetailsAssessmentViewHolder holder, int position) {
        holder.
    }

    @Override
    public int getItemCount() {
        return assessmentArrayList.size();
    }

    public static class CourseDetailsAssessmentViewHolder extends RecyclerView.ViewHolder {
        private List<Assessment> assessmentArrayList = new ArrayList<>();
        private final TextView courseDetailsAssessmentItemView;
        private final CheckBox courseDetailsAssessmentCheckbox;

        
    }


/**
 * class AssessmentCourseViewHolder extends RecyclerView.ViewHolder{
 * private final CheckBox assessmentCourseCheckboxes;
 * <p>
 * public AssessmentCourseViewHolder(@NonNull View itemView) {
 * super(itemView);
 * assessmentCourseCheckboxes = itemView.findViewById(R.id.courseDetailsAssessmentsList);
 * itemView.setOnClickListener(new View.OnClickListener() {
 *
 * @Override public void onClick(View view) {
 * Intent intent = new Intent(context, AssessmentDetails.class);
 * int position = getAdapterPosition();
 * final Assessment current = assessmentArrayList.get(position);
 * intent.putExtra("assessmentID", current.getAssessmentID());
 * intent.putExtra("assessmentTitle", current.getAssessmentTitle());
 * intent.putExtra("assessmentDueDate", current.getAssessmentDueDate());
 * intent.putExtra("assessmentGoalDate", current.getAssessmentGoalDate());
 * intent.putExtra("assessmentGoalDateAlert", current.isAssessmentGoalDateAlert());
 * intent.putExtra("assessmentObjective", current.isAssessmentObjective());
 * intent.putExtra("courseID", current.getCourseID());
 * }
 * });
 * }
 * }
 */
}
