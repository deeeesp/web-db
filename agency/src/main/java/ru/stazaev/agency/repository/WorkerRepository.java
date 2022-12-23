package ru.stazaev.agency.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.stazaev.agency.entity.Worker;

import java.util.List;

public interface WorkerRepository extends JpaRepository<Worker,Long> {
    @Override
    List<Worker> findAll();

    Worker findById(long id);


    @Transactional
    @Modifying
    @Query(value = "UPDATE Worker SET name = :name, telephone = :telephone WHERE id = :id")
    void update(@Param("id") long id, @Param("name") String name, @Param("telephone") Integer telephone);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Worker SET name = :name, telephone = :telephone WHERE id = :id")
    void update(@Param("id") long id, @Param("name") String name);

}
