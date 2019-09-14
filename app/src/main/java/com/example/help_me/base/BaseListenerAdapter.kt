package com.example.help_me.base

interface BaseListenerAdapter<T> {
    fun onClick(pos: Int, item: T)
}