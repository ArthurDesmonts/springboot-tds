package edu.spring.td2.Controller

import edu.spring.td2.entities.Organisation
import edu.spring.td2.repositories.OrganisationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController {

    @Autowired
    lateinit var orgaRepo: OrganisationRepository
    @GetMapping("/")
    fun index(model:ModelMap): String {
        model["organisation"] = orgaRepo.findAll()
        return "index"
    }

}