package ru.stazaev.agency.service;

import org.springframework.stereotype.Service;
import ru.stazaev.agency.entity.Client;
import ru.stazaev.agency.repository.ClientRepository;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAll(){
        return clientRepository.findAll();
    }

    public Client getById(long id){
        return clientRepository.findById(id);
    }

    public void save(Client client){
        clientRepository.save(client);
    }

    public void deleteById(long id){
        clientRepository.deleteById(id);
    }
}
