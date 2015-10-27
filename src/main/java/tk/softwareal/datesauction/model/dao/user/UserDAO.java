package tk.softwareal.datesauction.model.dao.user;

import tk.softwareal.datesauction.model.dao.AbstractDao;
import tk.softwareal.datesauction.model.user.User;

/**
 * Created by deepnekro on 22.07.15.
 */
public interface UserDAO extends AbstractDao<User, Long> {

    User findByUsername(String username);

}
