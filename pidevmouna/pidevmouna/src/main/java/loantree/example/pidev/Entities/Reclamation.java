package loantree.example.pidev.Entities;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor

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
    @OneToOne
    private Chat chat;


    public Reclamation() {

    }
}