package com.example.criminalintent.controller.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.criminalintent.R;

import java.util.Date;


public class TimePickerFragment extends DialogFragment {


    public static final String ARGS_CRIME_TIME = "crimeTime";

    private Date mPickerDate;
    private TimePicker mTimePicker;

    public TimePickerFragment() {
        // Required empty public constructor
    }

    public static TimePickerFragment newInstance(Date crimeDate) {
        TimePickerFragment fragment = new TimePickerFragment();

        Bundle args = new Bundle();
        args.putSerializable(ARGS_CRIME_TIME, crimeDate);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPickerDate = (Date) getArguments().getSerializable(ARGS_CRIME_TIME);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_time_picker, container, false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_time_picker, null);

        findViews(view);

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.time_picker_title)
                .setIcon(R.mipmap.ic_launcher)
                .setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();
    }

    private void findViews(View view) {
        mTimePicker = view.findViewById(R.id.time_picker_crime);
    }
//
//    private Calendar extractTimeFromTimePicker(){
//    }
}