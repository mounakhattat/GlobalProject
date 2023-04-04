package loantree.example.pidev.services;

import java.util.List;

//import javax.mail.MessagingException;
import javax.swing.TransferHandler.TransferSupport;

import loantree.example.pidev.Entities.Transaction;

public interface TransactionService {
    public List<Transaction>  GetAll();
    public Transaction Add(Transaction transaction);
    public Transaction Update(Transaction transaction);
    public void Delete(int idTransaction);
    public void undoTransaction(int idTransaction);
    public Transaction GetById(int idTransaction);
    public int TypetransactionStat(String typetransaction);
}
