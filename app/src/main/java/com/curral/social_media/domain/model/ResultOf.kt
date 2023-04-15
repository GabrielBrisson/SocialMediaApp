package com.curral.social_media.domain.model

sealed class ResultOf<T> {
    data class Success<T>(val data: T): ResultOf<T>()
    data class Failure<T>(val message: String?, val throwable: Throwable?): ResultOf<T>()
}