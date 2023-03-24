package edu.spring.btp.repositories

import org.springframework.data.jpa.repository.JpaRepository

interface ComplaintRepository:JpaRepository<edu.spring.btp.entities.Complaint, Int> {

    fun findByDomainName(name:String):List<edu.spring.btp.entities.Complaint>
}