package com.example.Dosify.repository;

import com.example.Dosify.Enum.Gender;
import com.example.Dosify.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

//    @Query(value = "Select * from doctor d where d.gender = :gender and d.age >= :age")
//    List<Doctor> findOfGenderAboveAge(Gender gender, int age);
}
