package com.sergeev.datesauction.model.dao.user;

import com.sergeev.datesauction.model.dao.AbstractDao;
import com.sergeev.datesauction.model.user.User;

/**
 * Created by deepnekro on 22.07.15.
 */
public interface UserDAO extends AbstractDao<User, Long> {

    User findByUsername(String username);

}
