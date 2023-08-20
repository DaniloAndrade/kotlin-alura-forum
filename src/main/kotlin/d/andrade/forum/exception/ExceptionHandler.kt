package d.andrade.forum.exception

import d.andrade.forum.output.ErrorView
import d.andrade.forum.output.FieldError
import d.andrade.forum.output.FieldViolationError
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFoundException(ex: NotFoundException, request: HttpServletRequest): ErrorView {

        return ErrorView(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            message = ex.message,
            path = request.servletPath
        )
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(ex: Exception, request: HttpServletRequest): ErrorView {

        return ErrorView(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = HttpStatus.INTERNAL_SERVER_ERROR.name,
            message = ex.message,
            path = request.servletPath
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleException(
        ex: MethodArgumentNotValidException,
        request: HttpServletRequest): FieldViolationError {

        val fieldErrors = ex.bindingResult.fieldErrors.map {
            FieldError(
                field = it.field,
                error = it.defaultMessage,
                value = it.rejectedValue
            )
        }

        return FieldViolationError(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            fieldErrors = fieldErrors,
            path = request.servletPath
        )
    }
}