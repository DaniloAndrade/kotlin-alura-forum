package d.andrade.forum.input

import d.andrade.forum.model.Curso
import d.andrade.forum.model.Topico
import d.andrade.forum.model.Usuario
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class NovoTopicoInput(
    @field:NotEmpty @field:Size(min = 5, max = 100 ) val titulo: String,
    @field:NotEmpty val mensagem: String,
    @field:NotNull val idCurso: Long,
    @field:NotNull val idAutor: Long
) {

    fun toTopic(findCurso: (Long) -> Curso, findAutor: (Long) -> Usuario): Topico {
        val _curso = findCurso(this.idCurso)
        val _autor = findAutor(this.idAutor)
        return Topico(
            id = null,
            titulo = this.titulo,
            mensagem = this.titulo,
            curso = _curso,
            autor = _autor
        )
    }
}


