package loantree.example.pidev.Entities;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString


public class Chat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String message;
    private LocalDateTime createdAt;
    private LocalDateTime lastActiveAt;

    @OneToOne(mappedBy="chat")
    private Reclamation reclamation;

    public Chat() {

    }
}
