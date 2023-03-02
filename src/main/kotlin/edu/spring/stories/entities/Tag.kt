package edu.spring.stories.entities

import jakarta.persistence.*

class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id : Int? = null

    @Column(nullable = false,length = 30)
    open var color : String? = null

    @Column(nullable = false,length = 30)
    open var label : String? = null
}