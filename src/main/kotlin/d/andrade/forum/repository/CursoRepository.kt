package d.andrade.forum.repository

import d.andrade.forum.model.Curso
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepository: JpaRepository<Curso, Long> {
}