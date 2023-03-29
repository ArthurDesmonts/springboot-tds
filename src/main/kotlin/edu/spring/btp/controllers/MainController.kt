package edu.spring.btp.controllers

import edu.spring.btp.entities.Complaint
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

    @GetMapping("/complaints/{name}")
    fun complaintAction(@PathVariable name:String,auth: Authentication, model: ModelMap): String{
        model["username"] = auth.name
        model["complaints"] = complaintRepository.findByDomainName(name)
        model["domainName"] = name
        model["complaintsSize"] = complaintRepository.findByDomainName(name).size
        return "complaint"
    }

    @GetMapping("/complaints/{name}/new")
    fun newComplaintAction(@PathVariable name:String,auth: Authentication, model: ModelMap): String{
        model["username"] = auth.name
        model["domainName"] = name
        return "newComplaint"
    }

    @PostMapping("/complaints/{name}/new")
    fun newComplaintAction(@PathVariable name:String,auth: Authentication, model: ModelMap,
                           @RequestParam("title") title:String,
                           @RequestParam("description") description:String): String{
        val user = userRepository.findByUsernameOrEmail(auth.name)
        val complaint = Complaint()
        val domain = domainRepository.findByName(name)
        complaint.title = title
        complaint.description = description
        complaint.domain = domain
        complaint.provider = domain.providers[0]
        if (user != null) {
            complaint.user = user
            complaint.claimants.add(user)
        }
        complaintRepository.save(complaint)

        return "redirect:/complaint/$name"
    }

    @GetMapping("/signup")
    fun registerAction(): String{
        return "register"
    }
    @PostMapping("/signup/newUser")
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