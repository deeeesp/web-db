package ru.stazaev.agency.service;

import org.springframework.stereotype.Service;
import ru.stazaev.agency.entity.Wish;
import ru.stazaev.agency.repository.WishRepository;

import java.util.List;

@Service
public class WishService {
    private final WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public List<Wish> getAll(){
        return wishRepository.findAll();
    }

    public Wish getById(long id){
        return wishRepository.findById(id);
    }

    public void save(Wish wish){
        wishRepository.save(wish);
    }

    public void deleteById(long id){
        wishRepository.deleteById(id);
    }

    public List<Wish> getByClientId(long id){
        return wishRepository.findByClientId(id);
    }
}
