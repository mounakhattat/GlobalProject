package loantree.example.pidev.Entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Payment")
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPayment")
    private Integer idPayment;

    private LocalDate date_payment;
    private Boolean status_ref;
    private Float penalty;
    private Double amount_pay;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval=true)
    private Refund refund;


}
