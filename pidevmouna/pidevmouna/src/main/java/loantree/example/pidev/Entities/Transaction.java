package loantree.example.pidev.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdTransaction; 
    @Enumerated(EnumType.STRING)
    private Typetransaction type_transaction;
    private int amount;
    
    private int IdCompteReciver;
    @Column(nullable = true)
    private int IdCompteSender;
    
}
