package d.andrade.forum.repository

import d.andrade.forum.model.Topico
import d.andrade.forum.output.TopicoPorCategoria
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicoRepository: JpaRepository<Topico, Long> {

    fun findByCursoNome(cursoNome: String, pagina: Pageable): Page<Topico>

    @Query("select new d.andrade.forum.output.TopicoPorCategoria(curso.categoria, count(t)) " +
            "from Topico t join t.curso curso group by curso.categoria")
    fun relatorio(): List<TopicoPorCategoria>
}