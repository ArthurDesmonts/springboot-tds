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
        modelMap["story"] = storyRepository.findAll()
        modelMap["developerWithoutStory"] = developerRepository.findAll().filter { it.stories.isEmpty()}
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

    @PostMapping("/story/{id}/action")
    fun actionStoryAction(@PathVariable id: Int, @RequestParam action: String, @RequestParam(required = false) developerId: Int?): RedirectView {
        val story = storyRepository.findById(id).get()
        when (action) {
            "remove" -> {
                story.developer?.giveUpStory(story)
                storyRepository.delete(story)
            }
            "affect" -> {
                if (developerId != null) {
                    val developer = developerRepository.findById(developerId).orElse(null)
                    if (developer != null) {
                        story.developer?.giveUpStory(story)
                        developer.addStory(story)
                        developerRepository.save(developer)
                    }
                }
            }
        }
        return RedirectView("/")
    }
}
