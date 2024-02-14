package com.xyarim.domain.models

public sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}


/**
 * @return true if result is [Result.Loading]
 */
fun <T> Result<T>.isLoading() = (this is Result.Loading)

/**
 * @return result's data if result is [Success]
 */
fun <T> Result<List<T>>.dataOrEmpty() = (this as? Result.Success)?.data ?: emptyList()

/**
 * @return [Result]'s data if result is [Success]
 * @param default, value to be returned if [Result]'s not [Success]
 */
infix fun <T> Result<T>.dataOr(default: T) = (this as? Result.Success)?.data ?: default