package d.andrade.forum.controller

import d.andrade.forum.input.AtualizaTopicoInput
import d.andrade.forum.input.NovoTopicoInput
import d.andrade.forum.output.TopicoView
import d.andrade.forum.service.TopicoService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {

    @GetMapping
    fun lista(): List<TopicoView> {
        return service.listar()
    }

    @GetMapping("/{id}")
    fun buscaPorId(@PathVariable id: Long): TopicoView? {
        return service.buscaPorId(id)
    }

    @PostMapping()
    fun cadastro(
        @RequestBody @Valid input: NovoTopicoInput,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicoView> {
        val topicoView = service.cadastrar(input)
        val uri = uriBuilder.path("/topicos/${topicoView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicoView)
    }

    @PutMapping("/{id}")
    fun cadastro(
        @PathVariable id: Long,
        @RequestBody @Valid input: AtualizaTopicoInput
    ): TopicoView {

        return service.atualizar(id, input)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleta(@PathVariable id: Long) {
        service.deletar(id)
    }
}