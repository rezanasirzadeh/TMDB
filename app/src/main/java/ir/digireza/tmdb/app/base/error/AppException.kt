package ir.digireza.tmdb.app.base.error

sealed class AppException: Exception()

class ServerException(val errorMessage: String): AppException()