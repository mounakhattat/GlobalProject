package loantree.example.pidev.Services;
import loantree.example.pidev.Entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IUserService { // pour definir les méthodes les cruds
    List<User> getAllUsers() ;
    User getUserById(Integer idUser) ;
    User createUser ( User user) ;
    User updateUser(Integer idUser, User user) ;
    void deleteUser(Integer  idUser);
    public User saveUser(String username, String password, String confirmedPassword,String role);

    public UserDetails loadUserByUsername(String username);

    // public void addRoleToUser(String username,String name);




}