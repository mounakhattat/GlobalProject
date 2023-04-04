package loantree.example.pidev.Entities;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Refund")
public class Refund implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRefund")
    private Integer idRefund;


    private LocalDate due_Date;
    private Double monthly_Payment;
    private Float interest;
    private Float amortization;
    private Float remaining_Balence;

    @ManyToOne
    private Credit credit;
}