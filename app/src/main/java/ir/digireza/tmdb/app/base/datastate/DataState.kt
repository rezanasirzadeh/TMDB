package ir.digireza.tmdb.app.base.datastate


sealed class DataState<T> {
    class DataSuccessState<T>(val data: T) : DataState<T>()
    class DataFailedState<T>(val errorMessage: String) : DataState<T>()
}