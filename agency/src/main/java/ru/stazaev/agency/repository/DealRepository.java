package ru.stazaev.agency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stazaev.agency.entity.Deal;

import java.util.List;

public interface DealRepository extends JpaRepository<Deal, Long> {
    @Override
    List<Deal> findAll();

    Deal findById(long id);

    List<Deal> getByWorkerIdAndClientIdAndFlatId(long workerId, long clientId, long flatId);
}
