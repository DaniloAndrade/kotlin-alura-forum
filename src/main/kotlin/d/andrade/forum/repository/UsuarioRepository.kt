package d.andrade.forum.repository

import d.andrade.forum.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository: JpaRepository<Usuario, Long> {
}