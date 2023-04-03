package loantree.example.pidev.services;
import loantree.example.pidev.Entities.User;
import loantree.example.pidev.repository.AccountRepository;
import loantree.example.pidev.repository.RoleRepository;
import loantree.example.pidev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;


@Service
public class UserService implements IUserService {
        private static final String SUBJECT = "Bienvenue chez Loan tree ";
    private static final String TEXT = "To confirm your register click here: ";
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    EmailService emailService;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public User getUserById(Integer idUser) {
        return userRepository.findById(idUser).orElse(null);
    }


    @Override
    public User createUser(User user) throws MessagingException {

        user.setActived(false);

         return userRepository.save(user);
        }
    /*@Override
    public boolean activateAccount(String confirmationCode) {
        User user = userRepository.findByEmail(confirmationCode);
        if (user != null) {
            user.setActived(true);
            userRepository.save(user);
            return true;
        }
        return false;
    } */

/*  String confirmUrl = "http://localhost:8080/user/createUser" ;
        sendSimpleMessage(user.getEmail(), SUBJECT, TEXT+confirmUrl); */
       // return savedUser;
    //}
    /*    // Generate QR code for the user
        String qrCodeText = "User Id: " + savedUser.getIdUser() + ", Name: " + savedUser.getUsername();
        int size = 250;

        QRCodeGenerator generator = new QRCodeGenerator();
        String qrCodeBase64 = generator.generateQRCodeImage(Integer.valueOf(size));


        savedUser.setQrcode(qrCodeBase64);
        userRepository.save(savedUser); */




    @Override
    public User updateUser(Integer idUser, User user) {
        User existingUser = userRepository.findById(idUser).orElse(null);
        if (existingUser != null) {
            existingUser.setLastName(user.getLastName());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setBirthDate(user.getBirthDate());
            existingUser.setAge(user.getAge());
            existingUser.setHousing(user.getHousing());
            existingUser.setNumPhone(user.getNumPhone());
            existingUser.setPlaceBirth(user.getPlaceBirth());
            existingUser.setSex(user.getSex());
            existingUser.setJob(user.getJob());
            existingUser.setPostalCode(user.getPostalCode());
            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }

    @Override
    public void deleteUser(Integer idUser) {
        userRepository.deleteById(idUser);
    }



    @Override
    public User saveUser(String username, String password, String confirmedPassword,String role) {
        User  user=userRepository.findByUsername(username).get();
        if(user!=null) throw new RuntimeException("User already exists");
        if(!password.equals(confirmedPassword)) throw new RuntimeException("Please confirm your password");
        User User=new User();
        User.setUsername(username);

        User.setPassword(passwordEncoder.encode(password));

        userRepository.save(User);

        return User;
    }
    @Override
    public User UpdatePassword(User agent, String password) {
        PasswordEncoder encoder=new BCryptPasswordEncoder();
        agent.setPassword(encoder.encode(password));
        userRepository.save(agent);
        return agent;
    }


    @Override
    public void sendSimpleMessage(String to, String subject, String text) {         // mailing
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    } }

  //  @Override
  //  public User findByConfirmationCode(String code) {
   //     return userRepository.findByConfirmationCode(code);
   // }
  /*@Override
  public Integer scoreCredit(Integer id) {
      Integer score = 0;
      User user = userRepository.findById(user.getIdUser().orElse(null));
      //System.out.println(user.getEmail());

      Integer ancienneteEmploi = user.getDateCreation();
      Float montantPret = credit.getAmount();
      Integer dureePret = credit.getDue_date();
      Integer ratioEndettement = credit.getInterest_rate();
      Date date_now = new Date();
      SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
      String yearString = yearFormat.format(date_now);
      Integer year1 = Integer.parseInt(yearString);
      //System.out.println(year1);

      SimpleDateFormat yearFormat2 = new SimpleDateFormat("yyyy");
      String yearString2 = yearFormat.format(user.getBirthDate());
      Integer year2 = Integer.parseInt(yearString2);
      Integer age = year1 - year2;
      //System.out.println(age);
      //Boolean garanties =loan.isGaranties();
      // Boolean historiqueCredit = user.isHistoriqueCredit();
      //  System.out.println(garanties);
      // System.out.println(historiqueCredit);
      //System.out.println("TEST");


      //Critère 1: Revenu mensuel
      if (revenuMensuel >= 3000 && revenuMensuel <= 5000) {
          score += 20;
          //System.out.println(score);
      } else if (revenuMensuel > 5000) {
          score += 40;
      }

      //Critère 2: Ancienneté de l'emploi
      if (da >= 2 && ancienneteEmploi <= 5) {
          score += 10;
      } else if (ancienneteEmploi > 5) {
          score += 20;
      }
      //System.out.println(score);

      //Critère 3: Montant et durée du prêt
      if (montantPret >= 5000 && montantPret <= 10000 && dureePret <= 24) {
          score += 10;
      } else if (montantPret > 10000 && dureePret > 24) {
          score += 20;
      }
      //System.out.println(score);

      //Critère 4: Ratio d'endettement
      if (ratioEndettement < 0.3) {
          score += 20;
      } else if (ratioEndettement >= 0.3 && ratioEndettement <= 0.4) {
          score += 10;
      }
      //System.out.println(score);


      //Critère 6: Age
      if (age >= 25 && age <= 40) {
          score += 10;
      } else if (age > 40 && age <= 50) {
          score += 5;
      }
      //System.out.println(score);

      //Critère 7: Garanties
      //  if (loan.isGaranties()) {
      //score += 10;
      //}
      //  System.out.println(score);

      ////Critère 8: Historique de crédit
      //  if (user.isHistoriqueCredit()) {
      //score += 10;
      //}
      System.out.println(score);

      return score;
  }
    }



  /* @Override
    public void addRoleToUser(String username, String name) {
        User User=userRepository.findByUsername(username);
        Role Role= roleRepository.findByRoleName(name);
        User.getRoles().add(Role);
        userRepository.save(User);
    }*/

