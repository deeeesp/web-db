package ru.stazaev.agency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stazaev.agency.entity.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {
    @Override
    List<Client> findAll();

    Client findById(long id);




}
