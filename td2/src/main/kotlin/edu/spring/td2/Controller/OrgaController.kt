package edu.spring.td2.Controller

import edu.spring.td2.entities.Organisation
import edu.spring.td2.repositories.OrganisationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.view.RedirectView

@Controller
@RequestMapping("/orgas/")
class OrgaController {

    @Autowired
    lateinit var orgaRepository: OrganisationRepository

    @GetMapping("add/{name}")
    fun testAddAction(@PathVariable name:String): RedirectView {
        val orga = Organisation()
        orga.nom = name
        orgaRepository.save(orga)
        return RedirectView("/")
    }
}