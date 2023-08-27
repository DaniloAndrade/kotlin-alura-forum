package d.andrade.forum.service

import d.andrade.forum.exception.NotFoundException
import d.andrade.forum.input.AtualizaTopicoInput
import d.andrade.forum.input.NovoTopicoInput
import d.andrade.forum.output.TopicoPorCategoria
import d.andrade.forum.output.TopicoView
import d.andrade.forum.output.of
import d.andrade.forum.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TopicoService(
    private val topicoRepository: TopicoRepository,
    private val cursoService: CursoService,
    private val autorService: AutorService

) {

    private val NOT_FOUND_MESSAGE = "Topico não foi encontrado"

    fun listar(pagina: Pageable): Page<TopicoView> = topicoRepository.findAll(pagina).map {
        TopicoView.of(it)
    }

    fun buscaPorId(id: Long): TopicoView = topicoRepository.findById(id)
        .map {
            TopicoView.of(it)
        }.orElseThrow { NotFoundException(NOT_FOUND_MESSAGE) }

    @Transactional
    fun cadastrar(input: NovoTopicoInput): TopicoView {
        val topico = input.toTopic(
            cursoService::buscaPorId,
            autorService::buscaPorId
        )


        val newTopico = topicoRepository.save(topico)
        return TopicoView.of(newTopico)
    }

    @Transactional
    fun atualizar(id: Long, input: AtualizaTopicoInput): TopicoView {
        val topico = topicoRepository
            .findById(id)
            .orElseThrow { NotFoundException(NOT_FOUND_MESSAGE) }

        val topicoAtualizado = topico.copy(
            titulo = input.titulo,
            mensagem = input.mensagem
        )
        topicoRepository.saveAndFlush(topicoAtualizado)
        return TopicoView.of(topicoAtualizado)
    }

    @Transactional
    fun deletar(id: Long) {
        val topico = topicoRepository
            .findById(id)
            .orElseThrow { NotFoundException(NOT_FOUND_MESSAGE) }
        topicoRepository.delete(topico)
    }

    fun listarPorCurso(curso: String, pagina: Pageable): Page<TopicoView> =
        topicoRepository.findByCursoNome(curso, pagina).map {
            TopicoView.of(it)
        }

    fun relatorio(): List<TopicoPorCategoria> = topicoRepository.relatorio()

}
