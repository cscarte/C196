package C196.mainactivity.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import C196.mainactivity.Entity.Assessment;
import C196.mainactivity.R;

public class CourseDetailsAssessmentsAdapter extends RecyclerView.Adapter<CourseDetailsAssessmentsAdapter.CourseDetailsAssessmentViewHolder> {
    private List<Assessment> courseDetailsAssessmentArray = new ArrayList<>();
    private static List<Integer> selectedAsssessmentIDs = new ArrayList<>();
    private final Context context;
    private final LayoutInflater cInflater;

    class CourseDetailsAssessmentViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox checkBox;

        public CourseDetailsAssessmentViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.courseAssessmentItemView);
        }
    }

    public CourseDetailsAssessmentsAdapter(Context context) {
        this.context = context;
        cInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CourseDetailsAssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = cInflater.inflate(R.layout.activity_courses_details_assessmentlist_item, parent, false);
        return new CourseDetailsAssessmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseDetailsAssessmentViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (courseDetailsAssessmentArray != null && courseDetailsAssessmentArray.size() > 0) {
            Assessment current = courseDetailsAssessmentArray.get(position);
            String name = current.getAssessmentTitle();
            holder.checkBox.setText(name);

            if (holder.checkBox.isChecked()) {
                selectedAsssessmentIDs.add(position);
            } else {
                if (selectedAsssessmentIDs.contains(position)) {
                    selectedAsssessmentIDs.remove(position);
                }
            }
        } else {
            holder.checkBox.setText("No assessments in database");
        }
    }

    @Override
    public int getItemCount() {
        if (courseDetailsAssessmentArray != null) {
            return courseDetailsAssessmentArray.size();
        } else return 0;
    }

    public void setCourseDetailsAssessmentArray(List<Assessment> courseDetailsAssessmentList) {
        courseDetailsAssessmentArray = courseDetailsAssessmentList;
        notifyDataSetChanged();
    }

    public static List<Integer> getSelectedAsssessmentIDs() {
        return selectedAsssessmentIDs;
    }
}
