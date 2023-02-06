package edu.spring.td2.entities

import jakarta.persistence.*

@Entity
open class Groupe constructor() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id:Int ?= null
    @Column(nullable = false, length = 60)
    open lateinit var nom: String
    @Column(length = 200)
    open var email:String ?= null
    @Column(length = 45)
    open var aliases:String ?= null

    @ManyToOne
    @JoinColumn(name = "idOrganisation", nullable = false)
    open lateinit var organisation:Organisation

    @ManyToMany(mappedBy = "groups")
    open var users:Set<Users> ?= HashSet()
}