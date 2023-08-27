package d.andrade.forum.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Topico(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val titulo: String,
    val mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    @field:ManyToOne
    val curso: Curso,
    @field:ManyToOne
    val autor: Usuario,
    @Enumerated(EnumType.STRING)
    val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,
    @field:OneToMany(mappedBy = "topico")
    val respostas: List<Resposta> = ArrayList()
)
