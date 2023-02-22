package com.memorize.everything.common.model

import org.springframework.http.HttpStatus

class MemorizeException: Exception {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(cause: Throwable) : super(cause)
}