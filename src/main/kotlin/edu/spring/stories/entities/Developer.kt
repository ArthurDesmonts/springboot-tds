package edu.spring.stories.entities

import jakarta.persistence.*


@Entity
open class Developer (){

    constructor(firstname: String, lastname: String) : this(){
        this.firstname = firstname
        this.lastname = lastname
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id : Int = 0

    @Column
    open var firstname : String? = null

    @Column
    open var lastname : String? = null

    @OneToMany(mappedBy = "developer", fetch = FetchType.EAGER , cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    open var stories = mutableListOf<Story>()

    fun addStory(story: Story){
        this.stories.add(story)
    }

    fun giveUpStory(story: Story){
        stories.remove(story)
        story.developer=null
    }
    fun preRemove(){
        stories.forEach { it.developer=null }
    }
}