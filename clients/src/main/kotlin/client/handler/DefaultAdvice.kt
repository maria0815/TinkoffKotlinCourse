package client.handler

import client.exception.ClientNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class DefaultAdvice {
    @ExceptionHandler(value = [(ClientNotFoundException::class)])
    fun handleException(e: Exception): ResponseEntity<Response> {
        return ResponseEntity(Response(e.message), (HttpStatus.NOT_FOUND))
    }

    class Response(val message: String?)
}