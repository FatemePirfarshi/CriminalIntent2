package com.example.criminalintent.repositoy;

import com.example.criminalintent.model.Crime;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeRepository implements IRepository{

    public static CrimeRepository sInstance;
    private static final int CRIME_SIZE = 100;

    public static CrimeRepository getInstance() {
        if (sInstance == null)
            sInstance = new CrimeRepository();

        return sInstance;
    }

    private List<Crime> mCrimes;

    private CrimeRepository() {
        mCrimes = new ArrayList<>();

        for (int i = 0; i < CRIME_SIZE; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime#" + (i + 1));
            crime.setSolved(i % 2 == 0);

            mCrimes.add(crime);
        }
    }
    @Override
    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public void setCrimes(List<Crime> crimes) {
        mCrimes = crimes;
    }

    @Override
    public void insertCrime(Crime crime){
        mCrimes.add(crime);
    }

    @Override
    public Crime getCrime(UUID id){

        for (Crime crime : mCrimes) {
            if(crime.getId().equals(id))
                return crime;
        }

        return null;
    }

    @Override
    public int getPosition(UUID id){
        for (int i = 0; i < mCrimes.size(); i++) {
            if(mCrimes.get(i).getId().equals(id))
                return i;
        }
        return 0;
    }

    @Override
    public void updateCrime(Crime crime){
        Crime findCrime = getCrime(crime.getId());
        findCrime.setTitle(crime.getTitle());
        findCrime.setSolved(crime.isSolved());
        findCrime.setDate(crime.getDate());
    }
    
    @Override
    public void deleteCrime(Crime crime){

        for (int i = 0; i < mCrimes.size(); i++) {
            if(mCrimes.get(i).getId().equals(crime.getId())) {
                mCrimes.remove(i);
                return;
            }
        }
    }
}
