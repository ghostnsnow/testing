/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chintanu.testing.service.pet;

import java.util.Collection;

import com.chintanu.testing.model.pet.*;
import org.springframework.dao.DataAccessException;


/**
 * Mostly used as a facade so all controllers have a single point of entry
 *
 * @author Michael Isvy
 */
public interface ClinicService {

    Collection<PetType> findPetTypes() throws DataAccessException;

    Owner findOwnerById(int id) throws DataAccessException;

    Pet findPetById(int id) throws DataAccessException;

    void savePet(Pet pet) throws DataAccessException;

    void saveVisit(Visit visit) throws DataAccessException;

    Collection<Vet> findVets() throws DataAccessException;

    void saveOwner(Owner owner) throws DataAccessException;

    Collection<Owner> findOwnerByLastName(String lastName) throws DataAccessException;

	Collection<Visit> findVisitsByPetId(int petId);

}
