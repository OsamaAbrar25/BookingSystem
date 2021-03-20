/*
package com.app.bookingsystem;

import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class AccommodationRepository {
    private static final String TAG = "tag";
    FirebaseFirestore db = FirebaseFirestore.getInstance();



    public MutableLiveData<ArrayList<AccommodationModel>> getData() {
        ArrayList<AccommodationModel> arrayList = new ArrayList<>();
        MutableLiveData<ArrayList<AccommodationModel>> mutableLiveData = new MutableLiveData<>();
        db.collection("accommodations")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        AccommodationModel accommodationModel = new AccommodationModel();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                accommodationModel.setAcc_name(document.get("acc_name").toString());
                                accommodationModel.setAddress(document.get("address").toString());
                                accommodationModel.setImage(document.get("image").toString());
                                accommodationModel.setLocation(document.get("location").toString());
                                accommodationModel.setMin_duration(document.get("min_duration").toString());
                                accommodationModel.setRent(document.get("rent").toString());
                                accommodationModel.setRoom(document.get("room").toString());
                                Collection<Object> values = document.getData().values();

                                arrayList.add(values);
                            }

                            mutableLiveData.postValue(arrayList);

                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }

                    }
                })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "fail", e);

            }
        });

      */
/*  AccommodationModel accommodationModel = new AccommodationModel();
        accommodationModel.setAcc_name("document.get.toString()");
        accommodationModel.setAddress("document.get.toString()");
        accommodationModel.setImage("document.get.toString()");
        accommodationModel.setLocation("document.get.toString()");
        accommodationModel.setMin_duration("document.get.toString()");
        accommodationModel.setRent("document.get.toString()");
        accommodationModel.setRoom("document.get.toString()");
        arrayList.add(accommodationModel);
        mutableLiveData.postValue(arrayList);*//*

        return mutableLiveData;
    }
}
*/
