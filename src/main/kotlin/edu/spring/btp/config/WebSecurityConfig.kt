package edu.spring.btp.config

import edu.spring.btp.entities.User
import edu.spring.btp.service.DbUserService
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler

@Configuration
@EnableWebSecurity
class WebSecurityConfig {
    @Bean
    @Throws(Exception::class)
    fun configure(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { authorizeHttpRequests ->
            authorizeHttpRequests.requestMatchers("/css/**", "/images/**", "/login/**", "/register/**").permitAll() // (1)
            authorizeHttpRequests.requestMatchers(PathRequest.toH2Console()).permitAll()

            authorizeHttpRequests.anyRequest().authenticated() // (4)

        }
        http
            .formLogin().loginPage("/login").successForwardUrl("/").and()
            .headers().frameOptions().sameOrigin()
            .and()
            .csrf().disable()

        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService? {
        return DbUserService()
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder? {
        return BCryptPasswordEncoder()
    }
    @Bean
    fun roleHierarchy(): RoleHierarchyImpl? {
        val roleHierarchy = RoleHierarchyImpl()
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_STAFF > ROLE_USER > ROLE_GUEST")
        return roleHierarchy
    }

    @Bean
    fun expressionHandler(): DefaultWebSecurityExpressionHandler? {
        val expressionHandler = DefaultWebSecurityExpressionHandler()
        expressionHandler.setRoleHierarchy(roleHierarchy())
        return expressionHandler
    }


}