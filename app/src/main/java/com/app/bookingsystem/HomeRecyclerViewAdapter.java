package com.app.bookingsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.squareup.picasso.Picasso;


public class HomeRecyclerViewAdapter extends FirestoreRecyclerAdapter<AccommodationModel, HomeRecyclerViewAdapter.HomeRecyclerViewHolder> {

    private OnItemClickListener listener;


    public HomeRecyclerViewAdapter(@NonNull FirestoreRecyclerOptions<AccommodationModel> options) {
        super(options);
    }


    @NonNull
    @Override
    public HomeRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_home_holder, parent, false);
        return new HomeRecyclerViewHolder(view);
    }



    @Override
    protected void onBindViewHolder(@NonNull HomeRecyclerViewHolder holder, int position, @NonNull AccommodationModel model) {
        holder.textView_name.setText(model.getAcc_name());
        holder.textView_type.setText(model.getRoom());
        holder.textView_address.setText(model.getAddress());
        holder.textView_rent.setText(model.getRent());

        Picasso.get()
                .load(model.getImage())
                //.resize(250, 250)
                //.rotate(90)
                //.placeholder(R.drawable.user_placeholder)
                .into(holder.imageView_pic);
    }


    public class HomeRecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView textView_name, textView_type, textView_address, textView_rent;
        ImageView imageView_pic;
        public HomeRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_name = itemView.findViewById(R.id.textView_name);
            textView_type = itemView.findViewById(R.id.textView_type);
            textView_address = itemView.findViewById(R.id.textView_address);
            textView_rent = itemView.findViewById(R.id.textView_rent);
            imageView_pic = itemView.findViewById(R.id.imageView_pic);

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
