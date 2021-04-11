package com.app.bookingsystem.ui.mybookings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.bookingsystem.AccommodationModel;

import com.app.bookingsystem.MybookingsAdapter;
import com.app.bookingsystem.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MybookingsFragment extends Fragment {


    private RecyclerView recyclerView_mybookings;
    private MybookingsAdapter mybookingsAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference reference = db.collection("bookings");

        View root = inflater.inflate(R.layout.fragment_mybookings, container, false);




        reference.orderBy("booking_time", Query.Direction.DESCENDING);
        Query query = reference.whereEqualTo("user_id", ""+FirebaseAuth.getInstance().getCurrentUser().getUid());
        FirestoreRecyclerOptions<AccommodationModel> options = new FirestoreRecyclerOptions.Builder<AccommodationModel>()
                .setQuery(query, AccommodationModel.class)
                .build();

        mybookingsAdapter = new MybookingsAdapter(options);

        recyclerView_mybookings = root.findViewById(R.id.recyclerView_mybookings);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView_mybookings.setLayoutManager(linearLayoutManager);
        recyclerView_mybookings.setHasFixedSize(true);
        recyclerView_mybookings.setAdapter(mybookingsAdapter);
        //mybookingsAdapter.notifyDataSetChanged();


       /* mybookingsAdapter.setOnItemClickListener(new MybookingsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                AccommodationModel accommodationModel = documentSnapshot.toObject(AccommodationModel.class);
                String booking_id = documentSnapshot.get("booking_id").toString();
                String acc_name = documentSnapshot.get("acc_name").toString();
                String booking_time = documentSnapshot.get("booking_time").toString();
                String address = documentSnapshot.get("address").toString();
                String amount_paid = documentSnapshot.get("rent").toString();
                accommodationModel.setBooking_id(booking_id);
                accommodationModel.setAcc_name(acc_name);
                accommodationModel.setBooking_time(booking_time);
                accommodationModel.setRent(amount_paid.concat(" INR"));
                accommodationModel.setAddress(address);
                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();

            }
        });*/



        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        mybookingsAdapter.startListening();

    }
    @Override
    public void onStop() {
        super.onStop();
        mybookingsAdapter.stopListening();
    }
}