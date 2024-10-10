package com.chintanu.testing.repository;

import org.springframework.stereotype.Repository;

import java.util.Random;

/**
 * Dummy repository for testing
 */
@Repository
public class PersonRepositoryImpl implements PersonRepository {
    @Override
    public void testingMockVerify() {

        System.out.println("Yesssssssss");
    }

    @Override
    public int getId() {
        return 58;
    }

    @Override
    public int getSquare(int num) {
        return num*num;
    }
}
