package com.example.criminalintent.controller.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.criminalintent.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment {


    public static final String ARGS_CRIME_DATE = "crimeDate";
    public static final String USER_SELECTED_DATE = "userSelectedDate";

    private Date mCrimeDate;
    private DatePicker mDatePicker;

    public DatePickerFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DatePickerFragment newInstance(Date crimeDate) {
        DatePickerFragment fragment = new DatePickerFragment();

        Bundle args = new Bundle();
        args.putSerializable(ARGS_CRIME_DATE, crimeDate);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCrimeDate = (Date) getArguments().getSerializable(ARGS_CRIME_DATE);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_date_picker, null);

        findViews(view);
        initViews();

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.date_picker_title)
                .setIcon(R.mipmap.ic_launcher)
                .setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       Date date = extractDateFromDatePicker();
                       sendResult(date);
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();
    }

    private void findViews(View view) {
        mDatePicker = view.findViewById(R.id.date_picker_crime);
    }

    private void initViews() {
        Calendar calender = Calendar.getInstance();
        calender.setTime(mCrimeDate);
        int year = calender.get(Calendar.YEAR);
        int monthOfYear = calender.get(Calendar.MONTH);
        int dayOfMonth = calender.get(Calendar.DAY_OF_MONTH);
        mDatePicker.init(year, monthOfYear, dayOfMonth, null);
    }

    private Date extractDateFromDatePicker(){
        int year = mDatePicker.getYear();
        int month = mDatePicker.getMonth();
        int day = mDatePicker.getDayOfMonth();

        return new GregorianCalendar(year, month, day).getTime();
    }

    private void sendResult(Date userSelectedDate){
        Fragment fragment = getTargetFragment();
        Intent intent = new Intent();
        intent.putExtra(USER_SELECTED_DATE, userSelectedDate);
        fragment.onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
    }
}