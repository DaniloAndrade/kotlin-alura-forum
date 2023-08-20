package d.andrade.forum.input

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class AtualizaTopicoInput(

    @field:NotEmpty
    @field:Size(min = 5, max = 100)
    val titulo: String,

    @field:NotEmpty
    val mensagem: String
)


