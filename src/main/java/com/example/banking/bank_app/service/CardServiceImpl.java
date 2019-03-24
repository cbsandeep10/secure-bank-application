package com.example.banking.bank_app.service;

import com.example.banking.bank_app.model.Account;
import com.example.banking.bank_app.model.Card;
import com.example.banking.bank_app.respository.AccountRepository;
import com.example.banking.bank_app.respository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CardServiceImpl implements CardService {

    @Autowired
    CardRepository cardRepository;

    @Override
    public List<Card> getAllCards(){
        return (List<Card>) cardRepository.findAll();
    }

    @Override
    public Card getCardByCardId(Long cardId){
        return cardRepository.findById(cardId).get();
    }

    @Override
    public void saveOrUpdate(Card card){
        cardRepository.save(card);
    }

    @Override
    public void deleteCard(Long id){
        cardRepository.deleteById(id);
    }

}