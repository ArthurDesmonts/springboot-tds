package edu.spring.stories.controllers

import edu.spring.stories.entities.Developer
import edu.spring.stories.entities.Story
import edu.spring.stories.repositories.DeveloperRepository
import edu.spring.stories.repositories.StoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.view.RedirectView


@Controller
@RequestMapping("/")
class MainController {

    @Autowired
    lateinit var developerRepository: DeveloperRepository

    @Autowired
    lateinit var storyRepository: StoryRepository

    @GetMapping("/")
    fun indexAction( modelMap: ModelMap):String{
        modelMap["developer"] = developerRepository.findAll()
        modelMap["story"] = storyRepository.findAll()
        return "main/index"
    }

    @PostMapping("developer/add")
    fun addDeveloperAction(developer: Developer): RedirectView{
        developerRepository.save(developer)
        return RedirectView("/")
    }

    @PostMapping("/developer/{id}/story")
    fun addStoryAction(@ModelAttribute story: Story, @PathVariable id: Int): RedirectView{
        val developer = developerRepository.findById(id).get()
        var story = storyRepository.findById(story.id!!).get()
        if(story != null){
            developer.addStory(story)
            developerRepository.save(developer)
        }else{
            story = Story(story.name!!)
            story.developer = developer
            storyRepository.save(story)
        }
        return RedirectView("/")
    }

    @GetMapping("/developer/{id}/delete")
    fun deleteDeveloperAction(@PathVariable id: Int): RedirectView{
        val developer = developerRepository.findById(id).get()
        developer.preRemove()
        developerRepository.delete(developer)
        return RedirectView("/")
    }

    @GetMapping("/story/{id}/action/remove")
    fun actionStoryAction(@PathVariable id: Int): RedirectView{
        val story = storyRepository.findById(id).get()
        story.developer?.giveUpStory(story)
        storyRepository.delete(story)
        return RedirectView("/")
    }


}