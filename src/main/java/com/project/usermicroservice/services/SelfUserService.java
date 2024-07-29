package com.project.usermicroservice.services;

import com.project.usermicroservice.exceptions.InCorrectPasswordException;
import com.project.usermicroservice.exceptions.InValidTokenException;
import com.project.usermicroservice.exceptions.SignUpException;
import com.project.usermicroservice.models.Token;
import com.project.usermicroservice.models.User;
import com.project.usermicroservice.repositories.TokenRepository;
import com.project.usermicroservice.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;
import java.util.Optional;

@Component
public class SelfUserService implements UserService{
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;
    private TokenRepository tokenRepository;
    public SelfUserService(BCryptPasswordEncoder bCryptPasswordEncoder,
                           UserRepository userRepository,
                           TokenRepository tokenRepository){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }
    public User signUp(String name, String email, String password) throws SignUpException{
        String hashedPassword = bCryptPasswordEncoder.encode(password);
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()) throw new SignUpException("Email ID: "+email+" already exists");
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setHashedPassword(hashedPassword);
        user.setEmailVerified(true);
        user.setRoles(null);
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public Token login(String email, String password) throws Exception {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()) throw new SignUpException("Email ID:"+ email+" does not exist");
        User user = optionalUser.get();
        if(bCryptPasswordEncoder.matches(password,user.getHashedPassword())){
            Token token = new Token();
            token.setUser(user);
            token.setValue(getAlphaNumericString(16));
            Timestamp timestamp = Timestamp.from(Instant.now());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(timestamp);
            calendar.add(Calendar.DATE,30);
            timestamp = new Timestamp(calendar.getTime().getTime());
            token.setExpiryDate(timestamp);
            token.setActive(true);
            Token savedToken = tokenRepository.save(token);
            return token;
        }
        else{
            throw new InCorrectPasswordException("Password or UserId invalid");
        }
    }

    public User validateToken(String value) throws InValidTokenException{
        Optional<Token> optionalToken = tokenRepository.findByValue(value);
        if(optionalToken.isEmpty()){
            throw new InValidTokenException("Invalid Token");
        }
        Token token = optionalToken.get();
        Timestamp currentTimestamp = Timestamp.from(Instant.now());
        if(currentTimestamp.compareTo(token.getExpiryDate())<0 && token.isActive()==true){
            return token.getUser();
        }
        else throw new InValidTokenException("Invalid Token");
    }

    public String getAlphaNumericString(int n){
        String all = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<n;i++){
            int index = (int)(all.length()*Math.random());
            stringBuilder.append(all.charAt(index));
        }
        return stringBuilder.toString();
    }
}
