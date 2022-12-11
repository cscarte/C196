package C196.mainactivity.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import C196.mainactivity.Entity.Term;
import C196.mainactivity.R;

public class TermsAdapter extends RecyclerView.Adapter<TermsAdapter.TermViewHolder> {
    class TermViewHolder extends RecyclerView.ViewHolder {
        private final TextView termsItemView;

        private TermViewHolder(View view) {
            super(view);
            termsItemView = view.findViewById(R.id.textView2);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Term current = mTerms.get(position);
                    Intent intent = new Intent(context, TermDetails.class);
                    intent.putExtra("termID", current.getTermID());
                    intent.putExtra("termTitle", current.getTermTitle());
                    intent.putExtra("termStartDate", current.getTermStartDate());
                    intent.putExtra("termEndDate", current.getTermEndDate());
                    context.startActivity(intent);
                }
            });
        }
    }

    private List<Term> mTerms;
    private final Context context;
    private final LayoutInflater mInflater;

    public TermsAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public TermsAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_terms_list_item, parent, false);
        return new TermViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TermsAdapter.TermViewHolder holder, int position) {
        if(mTerms != null){
            Term current = mTerms.get(position);
            String name = current.getTermTitle();
            holder.termsItemView.setText(name);
        }
        else{
            holder.termsItemView.setText("No term name set");
        }
    }

    public void setTerms(List<Term> terms){
        mTerms = terms;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mTerms != null) {
            return mTerms.size();
        } else return 0;
    }
}