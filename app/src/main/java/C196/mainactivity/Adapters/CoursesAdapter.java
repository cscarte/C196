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

import java.util.ArrayList;
import java.util.List;

import C196.mainactivity.Entity.Course;
import C196.mainactivity.R;
import C196.mainactivity.UI.CourseDetails;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CoursesViewHolder> {
    private List<Course> courseArrayList = new ArrayList<>();
    private AdapterView.OnItemClickListener listener;
    private final Context context;
    private final LayoutInflater mInflater;

    class CoursesViewHolder extends RecyclerView.ViewHolder {
        private final TextView coursesItemView;

        private CoursesViewHolder(View view) {
            super(view);
            coursesItemView = view.findViewById(R.id.coursesListTextView);
            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Course current = courseArrayList.get(position);
                    Intent intent = new Intent(context, CourseDetails.class);
                    intent.putExtra("courseID", current.getCourseID());
                    intent.putExtra("courseName", current.getCourseName());
                    intent.putExtra("courseStatus", current.getCourseStatus());
                    intent.putExtra("courseStartDate", current.getCourseStartDate());
                    intent.putExtra("courseEndDate", current.getCourseEndDate());
                    intent.putExtra("courseShareNotes", current.getCourseShareNotes());
                    intent.putExtra("courseInstructorName", current.getCourseInstructorName());
                    intent.putExtra("courseInstructorPhone", current.getCourseInstructorPhone());
                    intent.putExtra("courseInstructorEmail", current.getCourseInstructorEmail());
                    context.startActivity(intent);
                }
            });
        }
    }

    public CoursesAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public CoursesAdapter.CoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_courses_row, parent, false);
        return new CoursesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesAdapter.CoursesViewHolder holder, int position) {
        if (courseArrayList != null){
            Course current = courseArrayList.get(position);
            String name = current.getCourseName();
            holder.coursesItemView.setText(name);
        } else {
            holder.coursesItemView.setText("No course name set");
        }
    }

    @Override
    public int getItemCount() {
        if (courseArrayList != null){
            return courseArrayList.size();
        } else return 0;
    }

    public void setCourseList(List<Course> courses){
        courseArrayList = courses;
        notifyDataSetChanged();
    }
}
