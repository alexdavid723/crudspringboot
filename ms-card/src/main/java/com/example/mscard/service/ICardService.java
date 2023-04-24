package com.example.mscard.service;
import com.example.mscard.model.Card;
import java.util.List;

public interface ICardService {
    public List<Card>findAll();
    public int save(Card card);
    public int update(Card card);
    public int deleteById(int id);
}