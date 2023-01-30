package edu.spring.td1.controller

import edu.spring.td1.models.Item
import models.UIMessage

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView


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

    @GetMapping("/items/new")
    fun newAction() : String {
        return "newForm"
    }

    @PostMapping("/addNew")
    fun addNew(@ModelAttribute item : Item,
               @SessionAttribute("items") items:HashSet<Item>,
               attrs:RedirectAttributes)
        : RedirectView {
        if(item.nom == null || item.nom == "") {
            return RedirectView("/items/new")
        }
        items.add(item)
        attrs.addFlashAttribute("msg", UIMessage.message("Ajout", "${item.nom} a été ajouté avec succés"))
        return RedirectView("/")
    }

    @GetMapping("items/inc/{nom}")
    fun incAction(@PathVariable("nom") nom:String,
                  @SessionAttribute("items") items:HashSet<Item>,
                  attrs:RedirectAttributes)
        : RedirectView {
        var item = items.find { it.nom == nom }
        if (item != null) {
            item.evaluation++
            attrs.addFlashAttribute("msg", UIMessage.message("modification", "${item.nom} a bien été modifié"))
        }
        return RedirectView("/")
    }

    @GetMapping("items/dec/{nom}")
    fun decAction(@PathVariable("nom") nom:String, @SessionAttribute("items") item:HashSet<Item>, attrs:RedirectAttributes)
        : RedirectView{
        var item = item.find{it.nom == nom}
        if (item != null) {
            item.evaluation--
            attrs.addFlashAttribute("msg", UIMessage.message("modification", "${item.nom} a bien été modifié"))
        }
        return RedirectView("/")
    }

    @GetMapping("items/delete/{nom}")
    fun delItem(@PathVariable("nom") nom:String, @SessionAttribute("items") items:HashSet<Item>, attrs:RedirectAttributes)
            : RedirectView{
        var item = items.find { it.nom == nom }
        if(item != null){
            items.remove(item);
            attrs.addFlashAttribute("msg", UIMessage.message("suppresion", "${item.nom} a bien été supprimé"))
        }
        return RedirectView("/")
    }

}