package model.dao.impl;

import model.User;
import model.dao.UserDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by deepnekro on 22.07.15.
 */
public class UserDAOImpl implements UserDAO {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addUser(User user) throws SQLException {
        Session session = null;
        try{
            session = getSessionFactory().openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(session != null && session.isOpen() )
                session.close();
        }
    }

    public User getUser(long id) throws SQLException {
        return null;
    }

    public void deleteUser(User user) throws SQLException {

    }

    public List<User> getUsers() throws SQLException {
        return null;
    }
}
