package edu.spring.stories.entities

import jakarta.persistence.*


@Entity
open class Story (){

    constructor(name: String) : this(){
        this.name = name
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id : Int? = null

    @Column(nullable = false, length = 50)
    open var name : String? = null

    @ManyToOne
    @JoinColumn(name = "developer_id")
    open var developer : Developer ? = null

    @ManyToMany(mappedBy = "stories", fetch = FetchType.LAZY)
    open var tags = mutableSetOf<Tag>()
}