package com.example.challenger.base

interface BaseListenerAdapter<T> {
    fun onClick(pos: Int, item: T)
}