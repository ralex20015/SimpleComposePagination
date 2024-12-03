package com.earl.composepagination

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    suspend fun reset()
}