package edu.spring.btp.repositories

import edu.spring.btp.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository: JpaRepository<User, Int> {
    @Query(nativeQuery = true,value="SELECT * FROM \"user\" ORDER BY rand() LIMIT 1")
    fun getRandomUser(): User

    @Query(nativeQuery = true,value="SELECT u FROM \"user\" u WHERE u.username=:nameOrMail OR u.email=:nameOrMail")
    fun findByUsernameOrEmail(nameOrMail : String ): User?
}