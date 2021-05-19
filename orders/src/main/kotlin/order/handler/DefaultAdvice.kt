package order.handler

import order.exception.ClientNotFoundException
import order.exception.OrderNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception

@ControllerAdvice
class DefaultAdvice {

    @ExceptionHandler(value = [(OrderNotFoundException::class), (ClientNotFoundException::class)])
    fun handleException(e: Exception): ResponseEntity<Response> {
        return ResponseEntity(Response(e.message), HttpStatus.NOT_FOUND)
    }

    class Response(val message: String?)
}