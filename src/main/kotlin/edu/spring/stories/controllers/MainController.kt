package edu.spring.stories.controllers

import edu.spring.stories.entities.Developer
import edu.spring.stories.repositories.DeveloperRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.view.RedirectView


@Controller
@RequestMapping("/")
class MainController {

    @Autowired
    lateinit var developerRepository: DeveloperRepository
    @GetMapping("/")
    fun indexAction( modelMap: ModelMap):String{
        modelMap["developer"] = developerRepository.findAll()
        return "main/index"
    }

    @PostMapping("developer/add")
    fun addDeveloperAction(developer: Developer): RedirectView{
        developerRepository.save(developer)
        return RedirectView("/")
    }

}