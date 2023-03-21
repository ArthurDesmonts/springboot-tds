package edu.spring.stories.config

import edu.spring.stories.entities.Developer
import edu.spring.stories.entities.Story
import edu.spring.stories.repositories.RepositoryRestConfigurer
import org.springframework.context.annotation.Configuration

@Configuration
class RestConfig: RepositoryRestConfigurer {
    override fun configureRepositoryRestConfiguration(config: RepositoryRestConfiguration?, cors: CorsRegistry?) {
        config?.exposeIdsFor(Developer::class.java, Story::class.java)
    }
}


