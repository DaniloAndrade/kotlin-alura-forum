package d.andrade.forum.config

import d.andrade.forum.repository.UsuarioRepository
import d.andrade.forum.security.JWTFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
class SecurityConfiguration(
    val repository: UsuarioRepository,
    val jwtUtil: JWTUtil
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {

        return http.csrf{it.disable()}
            .authorizeHttpRequests{
                it.requestMatchers("/login").permitAll()
                it.requestMatchers("/topicos/**").hasAuthority("LEITURA_ESCRITA")
            }
            .sessionManagement{
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
//            .authenticated().and()
//            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(JWTFilter(users(), jwtUtil ),
                UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }

    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        val authenticationProvider = DaoAuthenticationProvider()
        authenticationProvider.setUserDetailsService(users())
        authenticationProvider.setPasswordEncoder(bcryptPasswordEncoder())
        return authenticationProvider
    }


    @Bean
    @Throws(Exception::class)
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager {
        return config.getAuthenticationManager()
    }


    @Bean
    fun users(): UserDetailsManager {
        return UserDetailManagerImp(repository)
    }

    @Bean
    fun bcryptPasswordEncoder() = BCryptPasswordEncoder(16)


}