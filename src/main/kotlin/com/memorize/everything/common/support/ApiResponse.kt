package com.memorize.everything.common.support

import org.springframework.http.HttpStatus

data class ApiResponse<T> (val statusCode: Int, val data: T) {

    companion object {
        fun <T>ok(data: T) : ApiResponse<T> {
            return ApiResponse(HttpStatus.OK.value(), data)
        }
    }
}