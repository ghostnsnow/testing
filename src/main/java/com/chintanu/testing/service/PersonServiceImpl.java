package com.chintanu.testing.service;

import com.chintanu.testing.domain.Person;
import com.chintanu.testing.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Dummy service for testing
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository repository;

    @Override
    public void testingMockVerify() {

        repository.testingMockVerify();
    }

    @Override
    public int getId() {
        return repository.getId();
    }

    @Override
    public int getSquare(int num) {
        return repository.getSquare(num);
    }

    @Override
    public Person savePerson(Person person) {
        return Person.builder()
                .id(3)
                .age(45)
                .name("John")
                .build();
    }
}
