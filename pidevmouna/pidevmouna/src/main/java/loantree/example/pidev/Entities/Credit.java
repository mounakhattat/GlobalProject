package loantree.example.pidev.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
@Table( name = "Credit")
public class Credit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCredit")
    private Integer idCredit;


    @Enumerated(EnumType.STRING)
    private Type_Credit type_credit;


    private LocalDate start_date;


    private LocalDate end_date;


    private float interest_rate;

    @Enumerated(EnumType.STRING)
    private Status_Credit status_credit;


    private Integer Duration;


    private Float Amount;

    private Integer score;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "credit")
    private List<Refund> refund;


    @OneToOne(cascade = CascadeType.REMOVE)
    @JsonIgnore
    private User user;


    public Credit() {

    }

}