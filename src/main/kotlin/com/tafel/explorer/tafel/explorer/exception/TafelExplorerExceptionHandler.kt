package com.tafel.explorer.tafel.explorer.exception

import com.tafel.explorer.tafel.explorer.model.Problem
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class TafelExplorerExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(value=[(Exception::class)])
    fun handleExceptin(exception: Exception, request: WebRequest): ResponseEntity<Any>{
        return ResponseEntity(Problem("500", exception.message), HttpStatus.INTERNAL_SERVER_ERROR)
    }
}