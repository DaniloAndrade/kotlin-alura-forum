package d.andrade.forum.controller

import d.andrade.forum.config.JWTUtil
import d.andrade.forum.input.AuthRequest
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class LoginController(
    private val authenticationManager: AuthenticationManager,
    private val jwtUtil: JWTUtil
) {

    @PostMapping("/login")
    fun authenticateAndGetToken(@RequestBody authRequest: AuthRequest): String {
        val authentication: Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.username,
                authRequest.password
            )
        )
        return if (authentication.isAuthenticated()) {
            jwtUtil.generateToken(authRequest.username)
        } else {
            throw UsernameNotFoundException("invalid user request !")
        }
    }
}