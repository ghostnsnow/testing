package com.chintanu.testing.service;

import com.chintanu.testing.domain.Person;

/**
 * Dummy service for testing
 */
public interface PersonService {

    public void testingMockVerify();
    public int getId();
    public int getSquare(int num);
    public Person savePerson(Person person);
}
