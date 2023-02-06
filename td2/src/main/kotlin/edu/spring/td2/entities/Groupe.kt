package edu.spring.td2.entities

import jakarta.persistence.*

@Entity
open class Groupe constructor() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id:Int ?= null
    @Column(nullable = false, length = 60)
    open lateinit var nom: String

    open var email:String ?= null
    open var aliases:String ?= null


}