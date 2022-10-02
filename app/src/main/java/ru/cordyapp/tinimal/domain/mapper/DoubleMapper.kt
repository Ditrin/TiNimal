package ru.cordyapp.tinimal.domain.mapper

interface DoubleMapper<I, J, O> {
    fun map(t: I, p: J): O
}