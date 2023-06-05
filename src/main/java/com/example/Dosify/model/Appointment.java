package com.example.Dosify.model;

import com.example.Dosify.Enum.DoseNo;
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
@Table( name = "appointment" )
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    int id;

    @Column( name = "appointment_id")
    String appointmentNo;                              // UUID

    @Column(name = "appointment_date")
    @CreationTimestamp
    Date appointmentDate;

    @Column(name = "dose_no")
    @Enumerated(EnumType.STRING)
    DoseNo doseNo;

    @ManyToOne
    @JoinColumn
    User user;

    @ManyToOne
    @JoinColumn
    Doctor doctor;

}
