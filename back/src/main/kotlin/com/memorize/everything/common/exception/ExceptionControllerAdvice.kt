package com.memorize.everything.common.exception

import com.memorize.everything.common.model.Error
import com.memorize.everything.common.model.MemorizeException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler
    fun handleException(ex: Exception): ResponseEntity<Error> {
        return if (ex is MemorizeException) {
            val errorMessage = Error(HttpStatus.BAD_REQUEST.value(), ex.message)
            ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
        } else {
            val unknownError = Error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.message)
            ResponseEntity(unknownError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}