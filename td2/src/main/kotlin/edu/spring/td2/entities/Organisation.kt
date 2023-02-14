package edu.spring.td2.entities

import jakarta.persistence.*

@Entity
open class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id:Int ?= null
    @Column(nullable = false, length = 60)
    open lateinit var name: String
    @Column(length = 45)
    open var domain:String ?= null
    @Column(length = 45)
    open var alias:String ?= null

    @OneToMany(mappedBy = "organisation",fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    open val users = mutableSetOf<Users>()

    @OneToMany(mappedBy = "organisation")
    open val groups = mutableSetOf<Groupe>()

    fun addUser(user: Users) {

    }
}