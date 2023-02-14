package edu.spring.td2.exception

class ElementNotFoundException : Exception{

    override val message: String?

    constructor(message: String?){
        this.message = message
    }
}