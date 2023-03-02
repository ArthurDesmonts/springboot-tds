package edu.spring.stories.entities

import jakarta.persistence.*

@Entity
open class Tag () {

    constructor(color: String, label: String) : this() {
        this.color = color
        this.label = label
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id : Int? = null

    @Column(nullable = false,length = 30)
    open var color : String? = null

    @Column(nullable = false,length = 30)
    open var label : String? = null

    @ManyToMany
    open var stories = mutableSetOf<Story>()

}