package d.andrade.forum.repository

import d.andrade.forum.model.Usuario
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository: JpaRepository<Usuario, Long> {

     @EntityGraph("Usuario.roles")
     fun findByEmail(username: String?): Usuario?
     fun existsByEmail(email: String?): Boolean
}