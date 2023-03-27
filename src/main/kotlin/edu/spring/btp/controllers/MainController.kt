package edu.spring.btp.controllers

import edu.spring.btp.entities.User
import edu.spring.btp.repositories.ComplaintRepository
import edu.spring.btp.repositories.DomainRepository
import edu.spring.btp.repositories.ProviderRepository
import edu.spring.btp.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
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

    @RequestMapping(path=["","/","/index"])
    fun indexAction(model: ModelMap): String{
        model["root"] = "Root"
        model["domains"] = domainRepository.findByParentName("Root")
        model["childrenSize"] = domainRepository.findByParentName("Root").size
        return "index"
    }
    @GetMapping("/domain/{name}")
    fun domainAction(@PathVariable name:String, model: ModelMap): String{
        model["root"] = name
        model["domains"] = domainRepository.findByParentName(name)
        model["childrenSize"] = domainRepository.findByParentName(name).size
        return "index"
    }

    @GetMapping("/complaint/{name}")
    fun complaintAction(@PathVariable name:String, model: ModelMap): String{
        model["complaints"] = complaintRepository.findByDomainName(name)
        model["domainName"] = name
        return "complaint"
    }

    @RequestMapping("/login")
    fun loginAction(): String{
        return "formLogin"
    }

    @RequestMapping("/register")
    fun registerAction(): String{
        return "register"
    }

    @PostMapping("/login")
    fun loginPostAction(@RequestParam username : String): String{
        val user = userRepository.findByUsernameOrEmail(username)
        if(user?.role == "ROLE_ADMIN"){
            return "redirect:/admin"
        }
        return "redirect:/"
    }

    @PostMapping("/register")
    fun registerPostAction(user: User): String{
        user.role = "ROLE_USER"
        userRepository.save(user)
        return "redirect:/login"
    }
}