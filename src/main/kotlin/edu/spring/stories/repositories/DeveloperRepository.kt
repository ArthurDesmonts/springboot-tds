package edu.spring.stories.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import edu.spring.stories.entities.Developer

@Repository
interface DeveloperRepository : CrudRepository<Developer, Int> {
}