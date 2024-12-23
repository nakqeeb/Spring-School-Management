package com.nakqeeb.sms.bootstrap;

import com.nakqeeb.sms.dao.UserRepository;
import com.nakqeeb.sms.dto.RegisterUserDto;
import com.nakqeeb.sms.entity.RoleEnum;
import com.nakqeeb.sms.entity.User;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Component
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public AdminSeeder(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        try {
//            this.createSuperAdministrator();
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
    }

    private void createSuperAdministrator() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = dateFormat.parse("1990-07-23");
        RegisterUserDto userDto = new RegisterUserDto();
        userDto.setName("Admin");
        userDto.setEmail("admin@email.com");
        userDto.setPassword("123456");
        userDto.setContactNumber("+966555555555");
        userDto.setAddress("Jeddah");
        userDto.setDateOfBirth(dob);
        userDto.setProfilePicture("https://png.pngtree.com/png-vector/20220719/ourmid/pngtree-color-icon---businessman-service-chief-intern-vector-png-image_37961988.png");

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if (optionalUser.isPresent()) {
            return;
        }

        var user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(RoleEnum.ADMIN);
        user.setContactNumber(userDto.getContactNumber());
        user.setAddress(userDto.getAddress());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setProfilePicture(userDto.getProfilePicture());
        user.setActivated(true);

        userRepository.save(user);
    }
}