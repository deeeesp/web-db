package ru.stazaev.agency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stazaev.agency.entity.Test;

import java.util.List;

public interface TestRep extends JpaRepository<Test,Long> {
    @Override
    List<Test> findAll();
}
