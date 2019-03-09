package com.example.banking.bank_app.service;

import com.example.banking.bank_app.model.User;
import com.example.banking.bank_app.respository.UserRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getUserByUserId(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public void saveOrUpdate(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Page<User> getPaginated(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Long findUserByColumn(@NotNull final String email, String type) {
        Transaction transaction = null;
        User user = new User();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> root = query.from(User.class);
            if (type.equals("email")){
                query.select(root).where(builder.equal(root.get("email_id"), email));
            }
            else{
                query.select(root).where(builder.equal(root.get("phone"), email));
            }
            Query<User> q=session.createQuery(query);
            user=q.getSingleResult();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return user.getUserId();
    }

}