package com.joancolmenerodev.base_core.exceptions

class SomethingWentWrongException(override val cause: Exception? = null) : Exception(cause)