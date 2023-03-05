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
    fun indexAction(modelMap: ModelMap): String {
        modelMap["developer"] = developerRepository.findAll()
        modelMap["story"] = storyRepository.findAll().filter { it.developer == null }
        modelMap["AllDevelopers"] = developerRepository.findAll()
        return "main/index"
    }

    @PostMapping("developer/add")
    fun addDeveloperAction(developer: Developer): RedirectView {
        developerRepository.save(developer)
        return RedirectView("/")
    }

    @PostMapping("/developer/{id}/story")
    fun addStoryAction(@PathVariable id: Int, story: Story): RedirectView {
        val developer = developerRepository.findById(id).get()
        developer.addStory(story)
        developerRepository.save(developer)
        return RedirectView("/")
    }

    @GetMapping("/developer/{id}/delete")
    fun deleteDeveloperAction(@PathVariable id: Int): RedirectView {
        val developer = developerRepository.findById(id).get()
        developer.preRemove()
        developerRepository.delete(developer)
        return RedirectView("/")
    }

    @PostMapping("/story/{id}/action/affect")
    fun actionStoryAffect(@PathVariable id: Int, @RequestParam developerID: Int): RedirectView {
        val story = storyRepository.findById(id).get()
        val developer = developerRepository.findById(developerID).get()
        developer.addStory(story)
        developerRepository.save(developer)
        storyRepository.delete(story)
        return RedirectView("/")
    }

    @PostMapping("/story/{id}/action/remove")
    fun actionStoryRemove(@PathVariable id: Int): RedirectView {
        val story = storyRepository.findById(id).get()
        story.developer?.giveUpStory(story)
        storyRepository.delete(story)
        return RedirectView("/")
    }
}
