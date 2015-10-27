package tk.softwareal.datesauction.model.dao.user.impl;

import tk.softwareal.datesauction.model.user.User;
import tk.softwareal.datesauction.model.dao.user.UserDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by deepnekro on 22.07.15.
 */


public class UserDAOImpl implements UserDAO {

    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(UserDAOImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    public UserDAOImpl() {
    }

    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override @Transactional
    public void persist(User entity) {
        getCurrentSession().save(entity);
    }

    @Override @Transactional
    public void update(User entity) {
        getCurrentSession().update(entity);
    }

    @Override @Transactional
    public User findById(Long aLong) {
        return getCurrentSession().get(User.class, aLong);
    }

    @Override @Transactional
    public User findByUsername(String username) {
        return (User)getCurrentSession().createQuery("from User where name=?").setParameter(0, username).uniqueResult();
    }

    @Override @Transactional
    public void delete(User entity) {
        getCurrentSession().delete(entity);
    }

    @Override @Transactional @SuppressWarnings("unchecked")
    public List<User> getAll() {
        return (List<User>) getCurrentSession().createQuery("from User").list();
    }

    @Override @Transactional
    public void deleteAll() {
        getAll().forEach(u -> delete(u));
    }
}
