package edu.spring.td2.entities

import jakarta.persistence.*

@Entity
open class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id:Int ?= null
    @Column(nullable = false, length = 60)
    open lateinit var nom: String
    @Column(length = 45)
    open var domain:String ?= null
    @Column(length = 45)
    open var aliases:String ?= null
}