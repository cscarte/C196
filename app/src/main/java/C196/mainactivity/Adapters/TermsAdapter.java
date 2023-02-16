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

import C196.mainactivity.Entity.Term;
import C196.mainactivity.R;
import C196.mainactivity.UI.TermDetails;

public class TermsAdapter extends RecyclerView.Adapter<TermsAdapter.TermViewHolder> {
    private List<Term> termArrayList = new ArrayList<>();
    private AdapterView.OnItemClickListener listener;
    private final Context context;
    private final LayoutInflater mInflater;

    class TermViewHolder extends RecyclerView.ViewHolder {
        private final TextView termsItemView;

        private TermViewHolder(View view) {
            super(view);
            termsItemView = view.findViewById(R.id.termsRow);
            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Term current = termArrayList.get(position);
                    Intent intent = new Intent(context, TermDetails.class);
                    intent.putExtra("termID", current.getTermID());
                    intent.putExtra("termTitle", current.getTermTitle());
                    intent.putExtra("termStartDate", current.getTermStartDate());
                    intent.putExtra("termEndDate", current.getTermEndDate());
                    intent.putExtra("")
                    context.startActivity(intent);
                }
            });
        }
    }

    public TermsAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public TermsAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_terms_row, parent, false);
        return new TermViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TermsAdapter.TermViewHolder holder, int position) {
        if (termArrayList != null) {
            Term current = termArrayList.get(position);
            String name = current.getTermTitle();
            holder.termsItemView.setText(name);
        } else {
            holder.termsItemView.setText("No term name set");
        }
    }

    @Override
    public int getItemCount() {
        if (termArrayList != null) {
            return termArrayList.size();
        } else return 0;
    }

    public void setTermsArrayList(List<Term> terms) {
        termArrayList = terms;
        notifyDataSetChanged();
    }
}