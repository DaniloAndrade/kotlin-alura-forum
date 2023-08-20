package d.andrade.forum.service

import d.andrade.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*

@Service
class AutorService(var autores: List<Usuario>) {

    init {
        autores = Arrays.asList(
            Usuario(
                id = 1,
                nome = "Test",
                email = "test@test.com"
            )
        )
    }

    fun buscaPorId(id: Long): Usuario {
        return autores
            .filter { it.id == id }
            .first()
    }

}
