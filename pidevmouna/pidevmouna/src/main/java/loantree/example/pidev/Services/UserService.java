package loantree.example.pidev.Services;
import loantree.example.pidev.Entities.User;
import loantree.example.pidev.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService  implements IUserService{
    @Autowired // pour dire que UserRepository ent une dependence de la classe UserServices
    private UserRepository userRepository;
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public User getUserById(Integer idUser) {
        return userRepository.findById(idUser).get();
    }
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
    @Override
    public User updateUser(Integer idUser, User user) {
        User existingUser = userRepository.findById(idUser).orElse(null);
        if (existingUser != null) {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setLastName(user.getLastName());
            existingUser.setJob(user.getJob());
            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }
    @Override
    public void deleteUser(Integer  idUser) {
        userRepository.deleteById(idUser);
    }
}

