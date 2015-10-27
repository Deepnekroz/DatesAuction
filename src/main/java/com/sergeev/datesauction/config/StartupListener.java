package com.sergeev.datesauction.config;

import com.sergeev.datesauction.model.user.User;
import com.sergeev.datesauction.model.dao.user.UserDAO;
import com.sergeev.datesauction.model.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by dmitry-sergeev on 22.09.15.
 */
@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UserDAO userDao;


    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event){
        userDao.persist(new User("admin", null, new BCryptPasswordEncoder().encode("12345"), UserRole.ROLE_ADMIN, true, new Date(), 0l, 0l, 0l, "admin@example.com", "Kiev", (byte)1));
        userDao.persist(new User("user", null, new BCryptPasswordEncoder().encode("12345"), UserRole.ROLE_USER, true, new Date(), 0l, 0l, 0l, "user@example.com", "Kiev", (byte)1));
    }
}
