package com.example.pokeapp.network.TypeApi

import com.example.pokeapp.model.Type.Type

sealed interface ApiTypeState {

    object Error: ApiTypeState
    object Loading: ApiTypeState
    object Success: ApiTypeState
}

data class TypeListState(val typeList: List<Type> = listOf())