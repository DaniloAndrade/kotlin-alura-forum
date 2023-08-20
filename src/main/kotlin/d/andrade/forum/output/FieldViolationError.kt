package d.andrade.forum.output

import java.time.LocalDateTime

data class FieldViolationError(
    val status: Int,
    val error: String,
    val fieldErrors: List<FieldError>,
    val path: String,
    val timestamp: LocalDateTime = LocalDateTime.now()
)
