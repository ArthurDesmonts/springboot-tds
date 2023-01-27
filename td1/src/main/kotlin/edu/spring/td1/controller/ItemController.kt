package edu.spring.td1.controller

import edu.spring.td1.models.Item

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes


@Controller
@SessionAttributes("items")
class ItemController {



    @RequestMapping("/")
    fun indexAction() : String {
        return "index"
    }

    @get:ModelAttribute("items")
    val items: Set<Item>
        get(){
            var elements=HashSet<Item>()
            elements.add(Item("Foo"))
            return elements
        }

}