package loantree.example.pidev.Services;
import loantree.example.pidev.Configuration.UserPrincipal;
import loantree.example.pidev.Entities.User;
import loantree.example.pidev.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service

public class UserService  implements IUserService{
@Autowired
    PasswordEncoder passwordEncoder;

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
    @Override
    public User saveUser(String username, String password, String confirmedPassword,String role) {
        User  user=userRepository.findByUsername(username);
        if(user!=null) throw new RuntimeException("User already exists");
        if(!password.equals(confirmedPassword)) throw new RuntimeException("Please confirm your password");
        User User=new User();
        User.setUsername(username);
        User.setActived(true);
        User.setPassword(passwordEncoder.encode(password));

        userRepository.save(User);

        return User;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username);
        if(user==null) throw new UsernameNotFoundException("invalid user");
        Collection<GrantedAuthority> authorities=new ArrayList<>();
        user.getRoles().forEach(r->{
            authorities.add(new SimpleGrantedAuthority(r.getName()));
        });
        return new UserPrincipal(user);
    }


}

