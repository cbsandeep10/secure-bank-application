package com.example.banking.bank_app.service;

import com.example.banking.bank_app.model.Card;

import java.util.List;

public interface CardService {
    public List<Card> getAllCards();

    public Card getCardByCardId(Long cardId);

    public void saveOrUpdate(Card card);

    public void deleteCard(Long id);
}
