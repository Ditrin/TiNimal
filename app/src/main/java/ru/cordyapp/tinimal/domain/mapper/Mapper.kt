package ru.cordyapp.tinimal.domain.mapper

interface Mapper<I,O> {
    fun map(t: I): O
}