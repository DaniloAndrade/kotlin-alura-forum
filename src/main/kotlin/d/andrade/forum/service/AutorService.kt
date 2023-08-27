package d.andrade.forum.service

import d.andrade.forum.model.Usuario
import d.andrade.forum.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class AutorService(private val usuarioRepository: UsuarioRepository) {


    fun buscaPorId(id: Long): Usuario {
        return usuarioRepository.getReferenceById(id)
    }

}
