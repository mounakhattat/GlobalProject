package loantree.example.pidev.services;

import java.util.List;

import javax.mail.MessagingException;

import loantree.example.pidev.Entities.Account;
import loantree.example.pidev.Entities.Transaction;
import loantree.example.pidev.Entities.Typetransaction;
import loantree.example.pidev.repository.AccountRepository;
import loantree.example.pidev.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TransactionServiceIMP implements TransactionService{
    
    @Autowired
    private TransactionRepository transactionrepository;
    @Autowired
    private AccountRepository compteRepository;
    @Autowired
    private SentVerifEmail sentVerifEmail;

    @Override
    public List<Transaction>  GetAll(){
       List<Transaction> tl= transactionrepository.findAll();
    return tl;
    }

    @Override
    public Transaction Add(Transaction transaction) {
        Transaction returnedTransaction=new Transaction();
        System.out.println("transaction"+transaction.getType_transaction());
        if(transaction.getType_transaction()== Typetransaction.TRANSFER){
            Account Reciver=compteRepository.findById(transaction.getIdCompteReciver()).get();
            Account Sender=compteRepository.findById(transaction.getIdCompteSender()).get();
            if(Reciver.equals(null)&&Sender.equals(null)){

                return returnedTransaction;

            }
            else if(Reciver!=null&&Sender!=null) {

                if(Reciver.getBalance() >=transaction.getAmount()){
                    Reciver.setBalance(Reciver.getBalance()+transaction.getAmount()); 
                    Sender.setBalance(Sender.getBalance()-transaction.getAmount()); 
                   try {
                    sentVerifEmail.sendHtmlEmail(Reciver.getUser().getEmail());
                } catch (MessagingException e) {

                    e.printStackTrace();
                }
                    compteRepository.save(Reciver);
                    compteRepository.save(Sender);
                    compteRepository.flush();
                    transactionrepository.save(transaction);
                    transactionrepository.flush();
                    returnedTransaction= transaction;
                }
                else {
                    return returnedTransaction; 
                }
               
            }
        }
        else if(transaction.getType_transaction()==Typetransaction.DEPOSIT){
            Account Reciver=compteRepository.findById(transaction.getIdCompteReciver()).get();
            if(Reciver.equals(null)){
                return returnedTransaction;
            }
            else if (Reciver!=null){
                Reciver.setBalance(Reciver.getBalance()+transaction.getAmount());
                try {
                    sentVerifEmail.sendHtmlEmail(Reciver.getUser().getEmail());
                } catch (MessagingException e) {

                    e.printStackTrace();
                }
                compteRepository.save(Reciver);
                compteRepository.flush();;
                transactionrepository.save(transaction);
                transactionrepository.flush();
                returnedTransaction= transaction;
            }

        }
        else if(transaction.getType_transaction()==Typetransaction.WITHDRAWAL){
            Account Reciver=compteRepository.findById(transaction.getIdCompteReciver()).get();
            if(Reciver.equals(null)){
                return returnedTransaction;
            }
            else if (Reciver!=null){
                if(Reciver.getBalance()>= transaction.getAmount() ){
                    Reciver.setBalance(Reciver.getBalance()-transaction.getAmount());
                    try {
                        sentVerifEmail.sendHtmlEmail(Reciver.getUser().getEmail());
                    } catch (MessagingException e) {

                        e.printStackTrace();
                    }
                    compteRepository.save(Reciver);
                    compteRepository.flush();;
                    transactionrepository.save(transaction);
                    transactionrepository.flush();
                    returnedTransaction= transaction;
                }
                else {
                    return returnedTransaction;
                }
               
            }

        }

        return returnedTransaction;
        
        

    }

    @Override
    public Transaction Update(Transaction transaction) {

        
        
        transactionrepository.save(transaction);
        transactionrepository.flush();
        return transaction;
    }

    @Override
    public void Delete(int idTransaction) {
        transactionrepository.deleteById(idTransaction);
         
    }

    @Override
    public Transaction GetById(int idTransaction) {
       Transaction tr=transactionrepository.findById(idTransaction).get();
       return tr;
    }

    @Override
    public void undoTransaction(int idTransaction) {
       Transaction transaction=transactionrepository.findById(idTransaction).get();
       if(transaction.getType_transaction()==Typetransaction.TRANSFER){

        Account Reciver=compteRepository.findById(transaction.getIdCompteReciver()).get();
        Account Sender=compteRepository.findById(transaction.getIdCompteSender()).get();

        Reciver.setBalance( Reciver.getBalance()- transaction.getAmount());
        Sender.setBalance(Sender.getBalance()+transaction.getAmount()); 
                   
        compteRepository.save(Reciver);
        compteRepository.save(Sender);
        compteRepository.flush();
        transactionrepository.delete(transaction);
       }
       if(transaction.getType_transaction()==Typetransaction.WITHDRAWAL){
        Account Reciver=compteRepository.findById(transaction.getIdCompteReciver()).get();
        Reciver.setBalance( Reciver.getBalance()+ transaction.getAmount());
        compteRepository.save(Reciver);
        compteRepository.flush();
        transactionrepository.delete(transaction);
       }
       if(transaction.getType_transaction()==Typetransaction.DEPOSIT){
        Account Reciver=compteRepository.findById(transaction.getIdCompteReciver()).get();
        Reciver.setBalance( Reciver.getBalance()- transaction.getAmount());
        compteRepository.save(Reciver);
        compteRepository.flush();
        transactionrepository.delete(transaction);
       }

    }

    @Override
    public int TypetransactionStat(String typetransaction) {
        


        List<Transaction> tr=transactionrepository.findTransactionByTypetransaction(typetransaction);

         int AlltransactionSize=transactionrepository.findAll().size();
         


       return (tr.size()*100)/AlltransactionSize;
    }

}
