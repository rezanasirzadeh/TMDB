package ir.digireza.tmdb.app.base.usecase

import ir.digireza.tmdb.app.base.datastate.DataState

abstract class UseCase<Type, Param> {

    abstract suspend fun invoke(param: Param ): DataState<Type>


}


class NoParam
