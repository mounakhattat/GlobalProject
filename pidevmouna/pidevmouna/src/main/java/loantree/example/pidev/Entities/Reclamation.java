package loantree.example.pidev.Entities;


<<<<<<< HEAD
import javax.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

=======
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
>>>>>>> 5cef2ce9968aaedb64b85496dedc62258d102de0
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
<<<<<<< HEAD
    loantree.example.pidev.Entities.Enums.StateReclamation stateReclamation;
=======
 StateReclamation state;
    @ManyToOne
    User user;
    @OneToOne
    Chat chat;
>>>>>>> 5cef2ce9968aaedb64b85496dedc62258d102de0
}