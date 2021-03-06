package com.app.bookingsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class MybookingsAdapter extends FirestoreRecyclerAdapter<AccommodationModel, MybookingsAdapter.MybookingsViewHolder> {
    private OnItemClickListener listener;

    public MybookingsAdapter(@NonNull FirestoreRecyclerOptions<AccommodationModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MybookingsViewHolder holder, int position, @NonNull AccommodationModel model) {
        holder.textView_bookingid.setText(model.getBooking_id());
        holder.textView_accname.setText(model.getAcc_name());
        holder.textView_bookingtime.setText(model.getBooking_time());
        holder.textView_amountpaidbooking.setText(model.getRent().concat(" INR"));
        holder.textView_addressbooking.setText(model.getAddress());
    }



    @NonNull
    @Override
    public MybookingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_mybookings_holder, parent, false);
        return new MybookingsViewHolder(view);
    }

    public class MybookingsViewHolder extends RecyclerView.ViewHolder {

        private TextView textView_bookingid, textView_accname, textView_bookingtime, textView_addressbooking, textView_amountpaidbooking;
        public MybookingsViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_bookingid = itemView.findViewById(R.id.textView_bookingid);
            textView_accname = itemView.findViewById(R.id.textView_accname);
            textView_bookingtime = itemView.findViewById(R.id.textView_bookingtime);
            textView_addressbooking = itemView.findViewById(R.id.textView_addressbooking);
            textView_amountpaidbooking = itemView.findViewById(R.id.textView_amountpaidbooking);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener (OnItemClickListener listener) {
        this.listener = listener;
    }
}
