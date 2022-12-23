package ru.stazaev.agency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stazaev.agency.entity.Flat;

import java.util.List;

public interface FlatRepository extends JpaRepository<Flat, Long> {
    @Override
    List<Flat> findAll();

    Flat findById(long id);

    List<Flat> getByWorkerId(long id);
}
