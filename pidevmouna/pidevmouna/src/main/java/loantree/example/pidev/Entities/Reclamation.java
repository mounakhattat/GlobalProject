package loantree.example.pidev.Entities;


import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idReclamation;

    String title;
    String content;
    @Enumerated(EnumType.STRING)
    loantree.example.pidev.Entities.Enums.TypeReclamation typeReclamation;
    Instant dateReclamation;

    @Enumerated(EnumType.STRING)
    loantree.example.pidev.Entities.Enums.StateReclamation stateReclamation;
}