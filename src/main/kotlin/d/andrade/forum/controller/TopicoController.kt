package d.andrade.forum.controller

import d.andrade.forum.input.AtualizaTopicoInput
import d.andrade.forum.input.NovoTopicoInput
import d.andrade.forum.output.TopicoPorCategoria
import d.andrade.forum.output.TopicoView
import d.andrade.forum.service.TopicoService
import jakarta.validation.Valid
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort.Direction
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {

    @GetMapping
    @Cacheable(value = ["topicos"])
    fun lista(
        @RequestParam(name = "curso", required = false)
        curso: String?,
        @PageableDefault(size = 5,
            sort = ["dataCriacao"],
            direction = Direction.DESC
        )
        pagina: Pageable
    ): Page<TopicoView> {
        val topicos = if (curso == null) {
            service.listar(pagina)
        } else {
            service.listarPorCurso(curso, pagina)
        }
        return topicos
    }

    @GetMapping("/{id}")
    fun buscaPorId(@PathVariable id: Long): TopicoView? {
        return service.buscaPorId(id)
    }

    @PostMapping()
    @CacheEvict(cacheNames = ["topicos"] , allEntries = true)
    fun cadastro(
        @RequestBody @Valid input: NovoTopicoInput,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicoView> {
        val topicoView = service.cadastrar(input)
        val uri = uriBuilder.path("/topicos/${topicoView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicoView)
    }

    @PutMapping("/{id}")
    @CacheEvict(cacheNames = ["topicos"] , allEntries = true)
    fun cadastro(
        @PathVariable id: Long,
        @RequestBody @Valid input: AtualizaTopicoInput
    ): TopicoView {

        return service.atualizar(id, input)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(cacheNames = ["topicos"] , allEntries = true)
    fun deleta(@PathVariable id: Long) {
        service.deletar(id)
    }


    @GetMapping("/relatorio")
    fun relatorio(): List<TopicoPorCategoria> {
        return service.relatorio();
    }
}