package tk.softwareal.datesauction.model.dao.user;


import tk.softwareal.datesauction.model.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry-sergeev on 22.09.15.
 */
@Service("userDetailsService") @Transactional(readOnly=true)
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserDAO userDao;


    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        tk.softwareal.datesauction.model.user.User user = userDao.findByUsername(username);
        if(user==null)
            return null;

        List<GrantedAuthority> authorities =
                buildUserAuthority(user.getRole());

        return buildUserForAuthentication(user, authorities);

    }

    // Converts com.sergeev.controlpanel.model.user.User user to
    // org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(tk.softwareal.datesauction.model.user.User user,
                                                   List<GrantedAuthority> authorities) {
        return new User(user.getName(), user.getPassword(),
                user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(UserRole userRole) {

        List<GrantedAuthority> result = new ArrayList<>();
        result.add(new SimpleGrantedAuthority(userRole.name()));

        return result;
    }
}
