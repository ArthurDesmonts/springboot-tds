package edu.spring.td2.Controller

import edu.spring.td2.entities.Organisation
import edu.spring.td2.repositories.OrganisationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/oragas/")
class OrgaController {

    @Autowired
    lateinit var orgaRepository: OrganisationRepository

    @GetMapping("add/{name}")
    fun testAddAction(@PathVariable name:String):String{
        val orga = Organisation()
        orga.nom = name
        orgaRepository.save(orga)
        return "Organisation $name ajoutée avec succès"
    }
}