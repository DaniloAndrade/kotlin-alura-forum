package d.andrade.forum.service

import d.andrade.forum.model.Curso
import d.andrade.forum.repository.CursoRepository
import org.springframework.stereotype.Service

@Service
class CursoService(private val cursoRepository: CursoRepository) {


    fun buscaPorId(id: Long): Curso {
        return cursoRepository.getReferenceById(id)
    }

}
