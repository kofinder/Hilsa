package com.finderbar.hilsa.core.common

/**
 * A generic interface for mapping data between different layers (e.g., DTO to Domain, Domain to Entity).
 *
 * @param I The input type.
 * @param O The output type.
 */
interface Mapper<I, O> {
    /**
     * Maps the input object of type [I] to the output type [O].
     *
     * @param input The object to be mapped.
     * @return The mapped object of type [O].
     */
    fun map(input: I): O
}
