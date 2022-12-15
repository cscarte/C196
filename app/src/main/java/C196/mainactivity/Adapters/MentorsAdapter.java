package C196.mainactivity.Adapters;

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

import C196.mainactivity.Entity.Mentor;
import C196.mainactivity.R;
import C196.mainactivity.UI.MentorDetails;

public class MentorsAdapter extends RecyclerView.Adapter<MentorsAdapter.MentorViewHolder> {
    private List<Mentor> mentorArrayList = new ArrayList<>();
    private final Context context;
    private final LayoutInflater mInflater;

    class MentorViewHolder extends RecyclerView.ViewHolder {
        private final TextView mentorsItemView;

        private MentorViewHolder(View view) {
            super(view);
            mentorsItemView = view.findViewById(R.id.mentorsRecyclerView);
            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Mentor current = mentorArrayList.get(position);
                    Intent intent = new Intent(context, MentorDetails.class);
                    intent.putExtra("mentorID", current.getMentorID());
                    intent.putExtra("mentorName", current.getMentorName());
                    intent.putExtra("mentorPhoneNumber", current.getMentorPhoneNumber());
                    intent.putExtra("mentorEmail", current.getMentorEmail());
                    context.startActivity(intent);
                }
            });
        }
    }

    public MentorsAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public MentorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_mentors_list, parent, false);
        return new MentorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MentorViewHolder holder, int position) {
        if (mentorArrayList != null) {
            Mentor current = mentorArrayList.get(position);
            String name = current.getMentorName();
            holder.mentorsItemView.setText(name);
        } else {
            holder.mentorsItemView.setText("No mentor name set");
        }
    }

    @Override
    public int getItemCount() {
        if (mentorArrayList != null) {
            return mentorArrayList.size();
        } else return 0;
    }

    public void setMentorArrayList(List<Mentor> mentors) {
        mentorArrayList = mentors;
        notifyDataSetChanged();
    }
}
