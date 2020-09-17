package com.example.criminalintent.controller.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.criminalintent.controller.fragment.CrimeListFragment;

public class CrimeListActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context){
        return new Intent(context , CrimeListActivity.class);
    }

    @Override
    public Fragment createFragment() {
        return CrimeListFragment.newInstance();
    }
}