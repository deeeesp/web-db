package ru.stazaev.agency.service;

import org.springframework.stereotype.Service;
import ru.stazaev.agency.entity.Deal;
import ru.stazaev.agency.repository.DealRepository;

import java.util.List;

@Service
public class DealService {
    private final DealRepository dealRepository;

    public DealService(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }

    public List<Deal> getAll(){
        return dealRepository.findAll();
    }

    public Deal getById(long id){
        return dealRepository.findById(id);
    }

    public void save(Deal deal){
        dealRepository.save(deal);
    }

    public void deleteById(long id){
        dealRepository.deleteById(id);
    }

    public List<Deal> getByWorkerIdOrClientIdOrFlatId(long id, long c,long f){
        return dealRepository.getByWorkerIdAndClientIdAndFlatId(id,c,f);
    }
}
