package com.example.Dosify.model;

import com.example.Dosify.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table( name = "doctor" )
@Builder
public class Doctor {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    int id;

    @Column( name = "name")
    String name;

    @Column( name = "age" )
    int age;

    @Column( name = "email", unique = true, nullable = false)
    String email;

    @Column( name = "contact_no")
    String contactNo;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    List<Appointment> appointments = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    VaccinationCenter vaccinationCenter;

}
