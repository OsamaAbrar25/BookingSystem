package com.app.bookingsystem.ui.maintenance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.app.bookingsystem.R;

public class MaintenanceFragment extends Fragment {

    //private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_maintenance, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        /*slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        return root;
    }
}