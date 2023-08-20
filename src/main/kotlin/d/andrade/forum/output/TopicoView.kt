package d.andrade.forum.output

import d.andrade.forum.model.StatusTopico
import d.andrade.forum.model.Topico
import java.time.LocalDateTime

data class TopicoView(
    val id: Long?,
    val titulo: String,
    val mensagem: String,
    val dataCriacao: LocalDateTime,
    val status: StatusTopico
) {
    companion object {}
}

fun TopicoView.Companion.of(topico: Topico): TopicoView {
    return TopicoView(
        id = topico.id,
        titulo = topico.titulo,
        mensagem = topico.mensagem,
        dataCriacao = topico.dataCriacao,
        status = topico.status
    )
}
