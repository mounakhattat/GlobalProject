package loantree.example.pidev.Controller;
import loantree.example.pidev.Entities.Account;
import loantree.example.pidev.Services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/Account")
public class AccountController {

    @Autowired
    private IAccountService accountService;


    //http://localhost:8083/Account/retrieve-Account
    @GetMapping("/retrieve-Account")
    public List<Account> getAllUsers() {
        return accountService.getAllAccount();
    }
    //http://localhost:8083/Account/get-Account/{id}
    @GetMapping("/get-Account/{account-id}")
    public Account getUserById(@PathVariable("account-id") Integer idAcc) {
        return accountService.getAccountById(idAcc);
    }
    //http://localhost:8083/Account/create-Account
    @PostMapping("/createAccount")
    public Account createAccount(@RequestBody Account a) {
        return accountService.createAccount(a);
    }
    //http://localhost:8083/Account/update/{account-id}
    @PutMapping("/update/{account-id}")
    public Account updateAccount(@PathVariable ("account-id") Integer idAcc, @RequestBody Account a) {
        return accountService.updateAccount(idAcc, a);
    }
    //http://localhost:8083/Account/delete/{AccountId}
    @DeleteMapping("/delete/{id}")
    public void deleteAccount(@PathVariable ("id") Integer idAcc) {
        accountService.deleteAccount(idAcc);
    }
}


