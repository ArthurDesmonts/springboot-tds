package edu.spring.btp.repositories

import edu.spring.btp.entities.Domain
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface DomainRepository:JpaRepository<Domain, Int> {
    @Query(nativeQuery = true,value="SELECT * FROM \"domain\" ORDER BY rand() LIMIT 1")
    fun getRandomDomain(): Domain

    @Query(nativeQuery = true,value="SELECT * FROM \"domain\" INNER JOIN \"domain_providers\" ON \"providers_id\"=:providerId ORDER BY rand() LIMIT 1")
    fun getRandomDomainFromProvider(providerId:Int): Domain

    @Query(nativeQuery = true, value = "SELECT * FROM \"domain\" WHERE \"providersName\" =:name")
    fun findByParentName(name:String):List<Domain>
    @Query(nativeQuery = true,value="SELECT * FROM \"domain\" WHERE \"name\"=:name")
    fun findByName(name:String):Domain
    @Query(nativeQuery = true,value="SELECT * FROM \"domain\" WHERE \"parent_id\" IS NULL")
    fun getAllNoParentsDomains():List<Domain>
    @Query(nativeQuery = true,value="SELECT * FROM \"domain\" WHERE \"parent_id\"=:id")
    fun getDomainByParentId(id:Int):List<Domain>

    @Query(nativeQuery = true,value="SELECT * FROM \"domain\" WHERE \"parent_id\"=:id ORDER BY rand() LIMIT 1")
    fun getDomainByParentIdRandomAnd(id:Int):Domain
}