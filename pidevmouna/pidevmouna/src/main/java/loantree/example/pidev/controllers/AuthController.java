package loantree.example.pidev.controllers;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import loantree.example.pidev.Entities.*;
import loantree.example.pidev.Exception.UserNotFoundException;
import loantree.example.pidev.Jwt.JwtUtils;
import loantree.example.pidev.repository.RoleRepository;
import loantree.example.pidev.repository.UserRepository;
import loantree.example.pidev.Request.LogIn;
import loantree.example.pidev.Request.SignUp;
import loantree.example.pidev.Responses.JwtResponse;
import loantree.example.pidev.Responses.MessageResponse;
import loantree.example.pidev.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;
    @Autowired
    IUserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    IResetPasswordService resetPasswordService;
    @Autowired
    ResetPasswordTokenService resetPasswordTokenService;

    @Autowired
    CodeConfirmationService codeConfirmationService ;
    @Autowired
    private HttpSession session;
    @Autowired
    TwilioService twilioService;
   // @Autowired
   // EmailVerificationTokenService emailVerificationTokenService;



    @Autowired
    JwtUtils jwtUtils;                                                        // generer et valider le info de token

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LogIn loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getIdUser(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));


    }
    @Value("${app.twillio.toPhoneNo}")
    private String To;
    @Value("${app.twillio.fromPhoneNo}")
    private String From;
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUp signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {

            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        System.out.println(signUpRequest.getRole());
        if (strRoles == null) {
            Role visitorRole = roleRepository.findByName(ERole.ROLE_VISITOR)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(visitorRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "agent":
                        Role agentRole = roleRepository.findByName(ERole.ROLE_AGENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(agentRole);

                        break;
                    case "customer":
                        Role custumorRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(custumorRole);

                        break;
                    default:
                        Role visitorRole = roleRepository.findByName(ERole.ROLE_VISITOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(visitorRole);
                }
            });
        }

        user.setEmail(signUpRequest.getEmail());
        String s = encoder.encode(signUpRequest.getPassword());
        user.setPassword(s);
        user.setUsername(signUpRequest.getUsername());
        user.setRoles(roles);
        String code = codeConfirmationService.generateCode();
        twilioService.sendSms(To, From, code);
        session.setAttribute("expectedCode", code);
        session.setAttribute("user", user);
        return ResponseEntity.ok(new MessageResponse("Merci de verifier votre code"));
    }

    @PostMapping("/confirm-code/{code}")
    public String confirmCode(@PathVariable String code, HttpSession session, Model model) {
        String expectedCode = (String) session.getAttribute("expectedCode");
        if (codeConfirmationService.verifyCode(code, expectedCode) ){
            User user = (User) session.getAttribute("user");
            user.setActived(true);
            userRepository.save(user);
            return "Votre inscription a été confirmée avec succès !";
        } else {
            model.addAttribute("error", "Le code de confirmation est invalide !");
            return "Erreur,Merci de verifier votre code svp";
        }
    }



    @PutMapping("/reset-password/{email}/{newPassword}")
    public ResponseEntity<String> resetPassword(@PathVariable("email") String email, @PathVariable String newPassword) {   // objet body  de type user
        try {
            resetPasswordService.resetPassword(email, newPassword);
            return ResponseEntity.ok("Password reset successfully");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping("/resetPasswordRequest/{email}")

    public PasswordResetToken generatePassToken(@PathVariable("email") String email) {
        try {
            return resetPasswordTokenService.CreatePasswordToken(email);
        } catch (UnsupportedEncodingException | MessagingException e) {
            System.out.print(e.getMessage());
        } catch (UserNotFoundException ex) {
            System.out.print(ex.getMessage());
        }
        return null;
    }


    @PutMapping("/resetPassword/{token}/{pass}")
    public void resetPasswod(@PathVariable("token") String token, @PathVariable("pass") String pass) {
        resetPasswordTokenService.ConfirmPasswordReset(token, pass);
    }

    /*@GetMapping("/ConsultDeviceActivity/{idUser}/{username}")
    @ResponseBody
   public List<String> getDeviceActivity(@PathVariable("idUser") Integer idUser, @PathVariable("username") String username) {
       return activityService.getUserDeviceActivity(idUser, username);
   } */
}


