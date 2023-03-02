package edu.spring.stories.entities

import jakarta.persistence.*

class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id : Int? = null

    @Column(nullable = false, length = 50)
    open var name : String? = null

    @OneToMany(mappedBy = "developer", fetch = FetchType.EAGER , cascade = [CascadeType.ALL])
    @JoinColumn(name = "developer_id")
    open var developer : MutableList<Developer>? = null

    @ManyToMany(mappedBy = "stories", fetch = FetchType.EAGER , cascade = [CascadeType.ALL])
    open var tags = mutableSetOf<Tag>()
}