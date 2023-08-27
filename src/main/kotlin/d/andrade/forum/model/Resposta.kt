package d.andrade.forum.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Resposta(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val mensagem: String,
    val dataCriacao: LocalDateTime,
    @field:ManyToOne
    val autor: Usuario,
    @field:ManyToOne
    val topico: Topico,
    val solucao: Boolean
)
