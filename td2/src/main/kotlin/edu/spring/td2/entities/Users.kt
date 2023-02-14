package edu.spring.td2.entities

import jakarta.persistence.*

@Entity
open class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id:Int ?= null

    @Column(nullable = false, length = 60)
    open lateinit var firstname: String

    @Column(nullable = false, length = 60)
    open lateinit var lastname: String

    @Column(nullable = false, length = 255, unique = true)
    open lateinit var email : String

    @ManyToOne
    @JoinColumn(name = "idOrganisation", nullable = false)
    open lateinit var organisation:Organisation

    @ManyToMany
    @JoinTable(name = "users_groupe")
    open val groupe: Set<Groupe> = HashSet()

}