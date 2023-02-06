package edu.spring.td2.repositories

import edu.spring.td2.entities.Organisation
import org.springframework.data.repository.CrudRepository

interface OrganisationRepository:CrudRepository<Organisation,Int> {
}