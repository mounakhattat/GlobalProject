package loantree.example.pidev.controllers;

import java.util.List;

//import javax.mail.MessagingException;

import loantree.example.pidev.Entities.Transaction;
import loantree.example.pidev.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/Transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/getAll")
    List<Transaction> getAll(){
        return transactionService.GetAll();
    }

    @GetMapping("/getById/{idTransaction}")
    Transaction getbuid(@PathVariable int idTransaction){
        return transactionService.GetById(idTransaction);
    }

    //metier
    @PostMapping("/add")
    Transaction Add(@RequestBody Transaction transaction){
        return transactionService.Add(transaction);
    }

    @PutMapping("/update")
    Transaction Update(@RequestBody Transaction transaction){
        return transactionService.Update(transaction);
    }

    @DeleteMapping("/delete/{idTransaction}")
    void deleteTransaction(@PathVariable int idTransaction){
        transactionService.Delete(idTransaction);
    }

    //metier
    @PutMapping("/undo/{idTransaction}")
    void undo(@PathVariable int idTransaction){
         transactionService.undoTransaction(idTransaction);
    }
    //metier
    @GetMapping("/transactionStat/{typetransaction}")
    int getbuid(@PathVariable String typetransaction) {
        return transactionService.TypetransactionStat(typetransaction);
    }
 


    
}
