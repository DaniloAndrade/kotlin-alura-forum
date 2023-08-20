package d.andrade.forum.output

data class FieldError(
    val field: String,
    val value: Any?,
    val error: String?
)
