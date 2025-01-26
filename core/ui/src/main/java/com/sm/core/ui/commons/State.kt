package com.sm.core.ui.commons

sealed class State<T>(val message: String? = null, val data: T? = null) {
    class Initial<T>(data: T? = null) : State<T>(data = data)

    class Active<T>(data: T? = null) : State<T>(data = data)

    class Loading<T>(data: T? = null) : State<T>(data = data)

    class Success<T>(data: T? = null) : State<T>(data = data)

    class Error<T>(message: String? = null, data: T? = null) :
        State<T>(message = message, data = data)

    class Empty<T>(message: String? = null, data: T? = null) :
        State<T>(message = message, data = data)
}

abstract class ViewState<T>(val state: State<T>) {
    val isLoading get() = this.state is State.Loading

    val isSuccess get() = this.state is State.Success

    val isActive get() = this.state is State.Active

    val isEmpty get() = this.state is State.Empty

    val isInitial get() = this.state is State.Initial

    val isNotEmpty get() = !this.isEmpty

    val isError get() = this.state is State.Error

    val isNoInternetConnectionError get() = this.state is State.Error && this.state.message == null
}