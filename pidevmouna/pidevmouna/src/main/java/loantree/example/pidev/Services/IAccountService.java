package loantree.example.pidev.Services;
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
}
