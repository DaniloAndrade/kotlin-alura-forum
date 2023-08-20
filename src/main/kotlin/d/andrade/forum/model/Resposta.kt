package d.andrade.forum.model

import java.time.LocalDateTime

data class Resposta(
    val id: Long? = null,
    val mensagem: String,
    val dataCriacao: LocalDateTime,
    val autor: Usuario,
    val topico: Topico,
    val solucao: Boolean
)
