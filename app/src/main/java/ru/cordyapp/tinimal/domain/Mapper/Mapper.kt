package ru.cordyapp.tinimal.domain.Mapper

interface Mapper<I,O> {
    fun map(t: I): O
}