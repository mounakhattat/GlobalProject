package loantree.example.pidev.services;
import loantree.example.pidev.Entities.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAccountService {
    public List<Account> getAllAccount();
    Account getAccountById(Integer idAcc);
    Account createAccount(Account a);
    Account updateAccount(Integer idAcc, Account a);
    void deleteAccount(Integer  idAcc);
    Account banUser(Integer idAcc, int nbr);
   // List<Account> filterAccount(Date dateCreation, Integer amountTrans, Integer Ageuser);

}
