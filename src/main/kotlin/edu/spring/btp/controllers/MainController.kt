package edu.spring.btp.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class MainController {
    @Autowired
    lateinit var userRepository: edu.spring.btp.repositories.UserRepository

    @Autowired
    lateinit var domainRepository: edu.spring.btp.repositories.DomainRepository

    @Autowired
    lateinit var complaintRepository: edu.spring.btp.repositories.ComplaintRepository

    @Autowired
    lateinit var providerRepository: edu.spring.btp.repositories.ProviderRepository

    @RequestMapping(path=["","/","/index"])
    fun indexAction(model: ModelMap): String{
        model["root"] = "Root"
        model["domains"] = domainRepository.findByParentName("Root")
        model["childrenSize"] = domainRepository.findByParentName("Root").size
        return "index"
    }
    @GetMapping("/domain/{name}")
    fun domainAction(@PathVariable name:String, model: ModelMap): String{
        model["domain"] = domainRepository.findByName(name)
        model["children"] = domainRepository.getDomainByParentId(domainRepository.findByName(name).id)
        model["childrenSize"] = domainRepository.findByParentName(name).size
        return "domain"
    }
}