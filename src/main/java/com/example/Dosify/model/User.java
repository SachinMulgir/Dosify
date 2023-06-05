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
@Table( name = "user" )
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column( name = "name")
    String name;

    @Column( name = "age" )
    int age;

    @Column( name = "email", unique = true, nullable = false)
    String email;

    @Column( name = "contact_no", unique = true, nullable = false)
    String contactNo;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    Gender gender;

    @Column( name = "is_Dose_1")
    boolean isDose1Taken;

    @Column( name = "is_Dose_2")
    boolean isDose2Taken;

    @OneToMany( mappedBy = "user" , cascade = CascadeType.ALL)
    List<Appointment> appointments = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    Dose1 dose1;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    Dose2 dose2;
}
