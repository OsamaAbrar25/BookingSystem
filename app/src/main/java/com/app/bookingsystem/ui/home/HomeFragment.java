package com.app.bookingsystem.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.bookingsystem.AccDetailActivity;
import com.app.bookingsystem.AccommodationModel;
import com.app.bookingsystem.HomeRecyclerViewAdapter;
import com.app.bookingsystem.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.List;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private HomeRecyclerViewAdapter homeRecyclerViewAdapter;
    private Button button_search;
    private String locationSelected;
    //private Spinner locationSpinner;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference reference = db.collection("accommodations");

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        button_search = root.findViewById(R.id.button_search);
        Spinner locationSpinner = root.findViewById(R.id.spinner_location);
        populateSpinner(db, locationSpinner);


        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (locationSelected.equals("All locations")) {
                    Query query = reference.orderBy("acc_name", Query.Direction.ASCENDING);
                    FirestoreRecyclerOptions<AccommodationModel> options = new FirestoreRecyclerOptions.Builder<AccommodationModel>()
                            .setQuery(query, AccommodationModel.class)
                            .build();
                    homeRecyclerViewAdapter.updateOptions(options);
                } else {

                    Query query = reference.whereEqualTo("location", locationSelected).orderBy("acc_name", Query.Direction.ASCENDING);
                    FirestoreRecyclerOptions<AccommodationModel> options = new FirestoreRecyclerOptions.Builder<AccommodationModel>()
                            .setQuery(query, AccommodationModel.class)
                            .build();
                    homeRecyclerViewAdapter.updateOptions(options);
                }
            }
        });

        Query query = reference.orderBy("acc_name", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<AccommodationModel> options = new FirestoreRecyclerOptions.Builder<AccommodationModel>()
                .setQuery(query, AccommodationModel.class)
                .build();

        homeRecyclerViewAdapter = new HomeRecyclerViewAdapter(options);

        recyclerView = root.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(homeRecyclerViewAdapter);

        homeRecyclerViewAdapter.setOnItemClickListener(new HomeRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                AccommodationModel accommodationModel = documentSnapshot.toObject(AccommodationModel.class);
                String id = documentSnapshot.getId();
                String name = documentSnapshot.get("acc_name").toString();
                String address = documentSnapshot.get("address").toString();
                String rent = documentSnapshot.get("rent").toString();
                String room = documentSnapshot.get("room").toString();
                String image = documentSnapshot.get("image").toString();
                String notice = documentSnapshot.get("notice_period").toString();
                String parking = documentSnapshot.get("parking").toString();
                Uri image2 = Uri.parse(image);


                Intent intent = new Intent(getActivity(), AccDetailActivity.class);
                intent.putExtra("acc_name", name);
                intent.putExtra("address", address);
                intent.putExtra("rent", rent);
                intent.putExtra("room", room);
                intent.putExtra("image", image);
                intent.putExtra("notice_period", notice);
                intent.putExtra("parking", parking);
                startActivity(intent);
            }
        });

        return root;
    }



    public void populateSpinner(FirebaseFirestore db, Spinner locationSpinner) {
        List<String> locations = new ArrayList<>();



        CollectionReference reference = db.collection("locations");
        reference.orderBy("id", Query.Direction.ASCENDING)
            .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String location = document.getString("location_name");
                        locations.add(location);
                    }
                        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            parent.getItemAtPosition(position);
                            locationSelected = parent.getSelectedItem().toString();
                            Toast.makeText(getContext(), ""+locationSelected, Toast.LENGTH_SHORT).show();


                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            Toast.makeText(getContext(), "Nothing Selected", Toast.LENGTH_SHORT).show();

                        }
                    });
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, locations);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    locationSpinner.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }
            }

        });


    }


    @Override
    public void onStart() {
        super.onStart();
        homeRecyclerViewAdapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        homeRecyclerViewAdapter.stopListening();
    }


}