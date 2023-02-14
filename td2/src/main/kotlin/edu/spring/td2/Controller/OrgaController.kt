package edu.spring.td2.Controller

import edu.spring.td2.entities.Organisation
import edu.spring.td2.repositories.OrganisationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
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
        orga.name = name
        orgaRepository.save(orga)
        return RedirectView("/")
    }

    @GetMapping("addOrgas")
    fun addNewAction(model: ModelMap): String {
        model["orga"]=Organisation()
        return "/main/orgas/add_form"
    }

    @PostMapping("new")
    fun submitAddNewAction(orga:Organisation): RedirectView {
        orgaRepository.save(orga)
        return RedirectView("/")
    }
}