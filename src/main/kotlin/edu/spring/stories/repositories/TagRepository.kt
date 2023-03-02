package edu.spring.stories.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import edu.spring.stories.entities.Tag


@Repository
interface TagRepository : CrudRepository<Tag, Int> {
    abstract fun findByColor(color: String): List<Tag>
}