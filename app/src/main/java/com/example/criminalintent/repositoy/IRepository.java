package com.example.criminalintent.repositoy;

import com.example.criminalintent.model.Crime;

import java.util.List;
import java.util.UUID;

public interface IRepository {
    List<Crime> getCrimes();
    Crime getCrime(UUID id);
    void insertCrime(Crime crime);
    void updateCrime(Crime crime);
    void deleteCrime(Crime crime);
    int getPosition(UUID id);
}
