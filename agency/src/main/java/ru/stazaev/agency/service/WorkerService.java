package ru.stazaev.agency.service;

import org.springframework.stereotype.Service;
import ru.stazaev.agency.entity.Worker;
import ru.stazaev.agency.repository.WorkerRepository;

import java.util.List;

@Service
public class WorkerService {
    private final WorkerRepository workerRepository;

    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public List<Worker> getAll(){
        return workerRepository.findAll();
    }

    public Worker getById(long id){
        return workerRepository.findById(id);
    }

    public void save(Worker worker){
        workerRepository.save(worker);
    }

    public void deleteById(long id){
        workerRepository.deleteById(id);
    }

    public void update(Worker worker) {
        workerRepository.update(worker.getId(), worker.getName(), worker.getTelephone());
    }
}
