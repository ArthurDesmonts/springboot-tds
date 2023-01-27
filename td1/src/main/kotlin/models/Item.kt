package models

class Item {
    var nom : String = ""
    var evaluation : Int = 0;

    constructor(nom : String, evaluation : Int) {
        this.nom = nom
        this.evaluation = evaluation
    }

    fun getNom() : String {
        return this.nom
    }

    fun getEvaluation() : Int {
        return this.evaluation
    }

    fun setNom(nom : String) {
        this.nom = nom
    }

    fun setEvaluation(evaluation : Int) {
        this.evaluation = evaluation
    }

    
}