package com.example.criminalintent.controller.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.criminalintent.R;
import com.example.criminalintent.controller.fragment.CrimeDetailFragment;
import com.example.criminalintent.model.Crime;
import com.example.criminalintent.repositoy.CrimeRepository;
import com.example.criminalintent.repositoy.IRepository;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity {

    public static final String EXTRA_CRIME_ID = "com.example.criminalintent.crimeId";

    private ViewPager2 mViewPagerCrime;
    private IRepository mRepository;
    private UUID mCrimeId;

    public static void start(Context context, UUID crimeId) {
        Intent starter = new Intent(context, CrimePagerActivity.class);
        starter.putExtra(EXTRA_CRIME_ID, crimeId);
        context.startActivity(starter);
    }

    public static Intent newIntent(Context context, UUID crimeId){
        Intent intent = new Intent(context, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        mRepository = CrimeRepository.getInstance();
        mCrimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        findViews();
        initViews();
    }

    private void findViews() {
        mViewPagerCrime = findViewById(R.id.view_pager_crimes);
    }

    private void initViews() {
        CrimePagerAdapter adapter =
                new CrimePagerAdapter(this,mRepository.getCrimes());

        mViewPagerCrime.setAdapter(adapter);

        mViewPagerCrime.setCurrentItem(mRepository.getPosition(mCrimeId));
    }

    private class CrimePagerAdapter extends FragmentStateAdapter{
        List<Crime> mCrimes;

        public List<Crime> getCrimes() {
            return mCrimes;
        }

        public void setCrimes(List<Crime> crimes) {
            this.mCrimes = crimes;
        }

        public CrimePagerAdapter(@NonNull FragmentActivity fragmentActivity, List<Crime> crimes) {
            super(fragmentActivity);
            mCrimes = crimes;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Crime crime = mCrimes.get(position);
            CrimeDetailFragment crimeDetailFragment =
                    CrimeDetailFragment.newInstance(crime.getId());

            return crimeDetailFragment;
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }
}