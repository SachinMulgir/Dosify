package com.example.Dosify.model;

import com.example.Dosify.Enum.VaccineType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table( name = "dose_2" )
@Builder
public class Dose2 {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column( name = "dose_id")
    String doseId;

    @Column( name = "vaccine_type")
    @Enumerated(EnumType.STRING)
    VaccineType vaccineType;

    @Column( name = "vaccine_date")
    @CreationTimestamp
    Date vaccinationDate;

    @OneToOne
    @JoinColumn
    User user;

}
