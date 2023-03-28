package edu.spring.btp.controllers

import edu.spring.btp.entities.User
import edu.spring.btp.repositories.ComplaintRepository
import edu.spring.btp.repositories.DomainRepository
import edu.spring.btp.repositories.ProviderRepository
import edu.spring.btp.repositories.UserRepository
import edu.spring.btp.service.DbUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/")
class MainController {
    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var domainRepository: DomainRepository

    @Autowired
    lateinit var complaintRepository: ComplaintRepository

    @Autowired
    lateinit var providerRepository: ProviderRepository

    @Autowired
    lateinit var dbUserService: DbUserService

    @RequestMapping(path=["","/","/index"])
    fun indexAction(model: ModelMap,auth: Authentication?): String{
        model["root"] = "Root"
        model["domains"] = domainRepository.findByParentName("Root")
        model["childrenSize"] = domainRepository.findByParentName("Root").size
        model["username"] = auth?.name
        model["back"] = null
        return "index"
    }
    @GetMapping("/domain/{name}")
    fun domainAction(@PathVariable name:String, model: ModelMap,auth: Authentication?): String{
        model["root"] = name
        model["domains"] = domainRepository.findByParentName(name ?: "Root")
        model["childrenSize"] = domainRepository.findByParentName(name).size
        model["username"] = auth?.name
        model["back"] = name
        return "index"
    }

    @GetMapping("/complaint/{name}")
    fun complaintAction(@PathVariable name:String, model: ModelMap): String{
        model["complaints"] = complaintRepository.findByDomainName(name)
        model["domainName"] = name
        return "complaint"
    }

    @GetMapping("/register")
    fun registerAction(): String{
        return "register"
    }
    @PostMapping("/register/newUser")
    fun registerAction(@RequestParam("username") username:String,
                       @RequestParam("password") password:String,
                       @RequestParam("passwordConf") passwordConf:String,
                       @RequestParam("mail") email:String): String{
        val user = User(username)
        user.email = email
        if(password == passwordConf){
            user.password = password
            dbUserService.encodePassword(user)
        }
        user.role = "ROLE_USER"
        userRepository.save(user)
        return "redirect:/login"
    }

}