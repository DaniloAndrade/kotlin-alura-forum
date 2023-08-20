package d.andrade.forum.service

import d.andrade.forum.model.Curso
import org.springframework.stereotype.Service
import java.util.Arrays

@Service
class CursoService(var cursos: List<Curso>) {

    init {
        cursos = Arrays.asList(Curso(
            id = 1L,
            nome = "Kotlin",
            categoria = "Programação"
        ))

    }

    fun buscaPorId(id: Long): Curso {
        return cursos
            .filter { it.id == id }
            .first()
    }

}
