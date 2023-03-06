package loantree.example.pidev.Services;
import loantree.example.pidev.Entities.User;

import java.util.List;

public interface IUserService { // pour definir les méthodes les cruds
    List<User> getAllUsers() ;
    User getUserById(Integer idUser) ;
    User createUser ( User user) ;
    User updateUser(Integer idUser, User user) ;
    void deleteUser(Integer  idUser);


}