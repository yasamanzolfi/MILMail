package ir.madeinlobby.milmail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by Seyyed Parsa Neshaei on 8/6/20
 * All Rights Reserved
 */
public class EmailAdapter extends RecyclerView.Adapter<EmailAdapter.ViewHolder> {

    private ArrayList<Email> data;
    private LayoutInflater inflater;

    public EmailAdapter(Context context, ArrayList<Email> data) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.email_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Email email = data.get(position);
        holder.senderTextView.setText(email.getSender());
        holder.subjectTextView.setText(email.getSubject());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView senderTextView;
        TextView subjectTextView;

        ViewHolder(View itemView) {
            super(itemView);
            senderTextView = itemView.findViewById(R.id.senderInRow);
            subjectTextView = itemView.findViewById(R.id.subjectInRow);
        }
    }

}
