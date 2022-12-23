package ru.stazaev.agency.service;

import org.springframework.stereotype.Service;
import ru.stazaev.agency.entity.Test;
import ru.stazaev.agency.repository.TestRep;

import java.util.List;

@Service
public class TestServ {
    private final TestRep testRep;

    public TestServ(TestRep testRep) {
        this.testRep = testRep;
    }

    public List<Test> getAll(){
        return testRep.findAll();
    }

    public void save(Test test){
        testRep.save(test);
    }
}
