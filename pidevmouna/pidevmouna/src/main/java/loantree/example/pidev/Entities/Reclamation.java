package loantree.example.pidev.Entities;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
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
    TypeReclamation type;
    Instant dateReclamation;
    @Enumerated(EnumType.STRING)
 StateReclamation state;
    @ManyToOne
    User user;
    @OneToOne
    Chat chat;
}