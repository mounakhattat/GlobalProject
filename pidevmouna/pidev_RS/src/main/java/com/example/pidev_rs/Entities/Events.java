package com.example.pidev_rs.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.io.Serializable;
import java.util.Date;
import java.sql.Time;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Events implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id_Event;

    private String name;
    private Date date;
    private Time startTime;
    private Time endTime;
    private String venue;
    private String description;
    private String image;
    private String organizer;
    private Double ticketPrice;
    private int ticketAvailability;
    private String contactInformation;
    private String FormLink;



}
