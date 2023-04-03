package com.example.pidev_rs.Entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reclamation implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer idReclamation;

    String title;
    String content;
    @Enumerated(EnumType.STRING)
    com.example.pidev_rs.Entities.enums.TypeReclamation type;
    Instant dateReclamation;
    @Enumerated(EnumType.STRING)
    com.example.pidev_rs.Entities.enums.StateReclamation state;
}