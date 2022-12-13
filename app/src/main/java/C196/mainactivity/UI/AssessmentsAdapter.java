package C196.mainactivity.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import C196.mainactivity.Entity.Assessment;
import C196.mainactivity.R;

public class AssessmentsAdapter extends RecyclerView.Adapter {
    private final TextView assessmentsItemView;

    private AssessmentViewHolder(View view){
        super(view);
        assessmentsItemView = view.findViewById(R.id.assessmentsTextView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
            }
        });
    }

    private final Context context;
    private final LayoutInflater mInflater;

    public AssessmentsAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
