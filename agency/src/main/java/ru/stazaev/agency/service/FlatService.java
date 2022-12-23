package ru.stazaev.agency.service;

import org.springframework.stereotype.Service;
import ru.stazaev.agency.entity.Flat;
import ru.stazaev.agency.repository.FlatRepository;

import java.util.List;

@Service
public class FlatService {

    private final FlatRepository flatRepository;


    public FlatService(FlatRepository flatRepository) {
        this.flatRepository = flatRepository;
    }

    public List<Flat> getAll(){
        return flatRepository.findAll();
    }

    public Flat getById(long id){
        return flatRepository.findById(id);
    }

    public void save(Flat flat){
        flatRepository.save(flat);
    }

    public void deleteById(long id){
        flatRepository.deleteById(id);
    }

    public List<Flat> getByWorkerId(long id){
        return flatRepository.getByWorkerId(id);
    }
}
