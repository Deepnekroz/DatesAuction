package model.dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by deepnekro on 22.07.15.
 */
public interface UserDAO {
    void addUser(User user) throws SQLException;
    User getUser(long id) throws SQLException;
    void deleteUser(User user) throws SQLException;
    List<User> getUsers() throws SQLException;

}
