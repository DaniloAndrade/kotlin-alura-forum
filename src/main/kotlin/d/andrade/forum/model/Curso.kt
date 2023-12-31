package d.andrade.forum.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Curso(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val nome: String,
    val categoria: String
)
