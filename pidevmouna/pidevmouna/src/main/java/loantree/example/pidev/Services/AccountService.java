package loantree.example.pidev.Services;
import loantree.example.pidev.Entities.Account;
import loantree.example.pidev.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountService implements IAccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }
    @Override
    public Account getAccountById(Integer idAcc) {
        return accountRepository.findById(idAcc).orElse(null);
    }
    @Override
    public Account createAccount(Account a) {
        return accountRepository.save(a);
    }
    @Override
    public Account updateAccount(Integer idAcc, Account a) {
        Account existingAccount = accountRepository.findById(idAcc).orElse(null);
        if (existingAccount != null) {
            existingAccount.setNumAccount(a.getNumAccount());
            existingAccount.setRib(a.getRib());
            return accountRepository.save(existingAccount);
        } else {
            return null;
        }
    }
    @Override
    public void deleteAccount(Integer  idAcc) {
        accountRepository.deleteById(idAcc);
    }
}
