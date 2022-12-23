package ru.stazaev.agency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stazaev.agency.entity.Wish;

import java.util.List;

public interface WishRepository extends JpaRepository<Wish,Long> {
    @Override
    List<Wish> findAll();

    Wish findById(long id);

    List<Wish> findByClientId(long id);




}
