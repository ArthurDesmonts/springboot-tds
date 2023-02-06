package edu.spring.td2.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
open class Users {
    @Id
    open var id:Int ?= null
    @Column(nullable = false, length = 60)
    open lateinit var nom: String
    @Column(length = 200)
    open var email:String ?= null

}