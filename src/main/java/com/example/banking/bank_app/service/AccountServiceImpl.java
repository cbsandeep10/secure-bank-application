package com.example.banking.bank_app.service;

import com.example.banking.bank_app.model.Account;
import com.example.banking.bank_app.respository.AccountRepository;
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
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Account> getAllAccounts() {
        return (List<Account>) accountRepository.findAll();
    }

    @Override
    public Account getAccountByAccountNo(Long accountNo) {
        return accountRepository.findById(accountNo).get();
    }

    @Override
    public void saveOrUpdate(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void deleteAccount(Long accountNo) {
        accountRepository.deleteById(accountNo);
    }


    @Override
    public Page<Account> getPaginated(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Override
    public List<Account> findAccountByUserId(@NotNull final Long userId){
        Transaction transaction = null;
        List<Account> accounts = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Account> query = builder.createQuery(Account.class);
            Root<Account> root = query.from(Account.class);
            query.select(root).where(builder.equal(root.get("user_id"), userId));
            Query<Account> q=session.createQuery(query);
            accounts=q.getResultList();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return accounts;
    }

}