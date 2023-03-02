package edu.spring.stories.repositories

import edu.spring.stories.entities.Developer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import edu.spring.stories.entities.Story


@Repository
interface StoryRepository : CrudRepository<Story, Int>{
    abstract fun findByDeveloperIsNull(): List<Story>
    abstract fun findByNameAndDeveloperId(s: String, id: Int): Story?
}