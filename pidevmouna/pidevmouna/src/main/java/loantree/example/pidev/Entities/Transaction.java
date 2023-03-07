package loantree.example.pidev.Entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity

public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id_trans; // Clé primaire
    private int id_user;
    @Enumerated(EnumType.STRING)
    private Typetransaction type_trans;
    @Temporal(TemporalType.DATE)
    private Date date_transaction;
    private int amount;

}