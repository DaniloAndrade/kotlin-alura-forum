package d.andrade.forum.service

import d.andrade.forum.exception.NotFoundException
import d.andrade.forum.input.AtualizaTopicoInput
import d.andrade.forum.input.NovoTopicoInput
import d.andrade.forum.model.Topico
import d.andrade.forum.output.TopicoView
import d.andrade.forum.output.of
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private val cursoService: CursoService,
    private val autorService: AutorService

) {

    private val NOT_FOUND_MESSAGE = "Topico não foi encontrado"

    fun listar(): List<TopicoView> = topicos.map {
        TopicoView.of(it)
    }

    fun buscaPorId(id: Long): TopicoView = topicos.find { it.id == id }
        ?.let {
            TopicoView.of(it)
        } ?: throw NotFoundException(NOT_FOUND_MESSAGE)

    fun cadastrar(input: NovoTopicoInput): TopicoView {
        val topico = input.toTopic(
            cursoService::buscaPorId,
            autorService::buscaPorId
        )

        val newTopico = topico.copy(id = topicos.size.toLong() + 1)
        topicos = topicos.plus(
            newTopico
        )
        return TopicoView.of(newTopico)
    }

    fun atualizar(id: Long, input: AtualizaTopicoInput): TopicoView {
        val topico = topicos.find { it.id == id }
            ?: throw NotFoundException(NOT_FOUND_MESSAGE)

        val topicoAtualizado = topico.copy(
            titulo = input.titulo,
            mensagem = input.mensagem
        )
        topicos = topicos.minus(topico)
            .plus(topicoAtualizado)
        return TopicoView.of(topicoAtualizado)
    }

    fun deletar(id: Long) {
        val topico = topicos.find { it.id == id }
            ?: throw NotFoundException(NOT_FOUND_MESSAGE)
        topicos = topicos.minus(topico)
    }
}
