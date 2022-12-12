package ir.digireza.tmdb.app.base.error


sealed interface AppError

sealed class IOError: AppError

class NetworkError(val errorMessage: String) : IOError()
object SererError: IOError()

