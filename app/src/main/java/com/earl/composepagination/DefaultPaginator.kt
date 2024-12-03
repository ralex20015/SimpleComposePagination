package com.earl.composepagination

class DefaultPaginator<Key, Item>(
    private val initialKey: Key,
    private inline val onLoadUpdate: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> Result<List<Item>>,
    private inline val getNextKey: suspend (List<Item>) -> Key,
    private inline val onError: suspend (Throwable?) -> Unit,
    private inline val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit
): Paginator<Key,Item> {
    private var currentKey: Key = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextItems() {
        if (isMakingRequest){
            return
        }
        isMakingRequest = true
        onLoadUpdate(true)
        val result = onRequest(currentKey)
        isMakingRequest =false
        val items = result.getOrElse {
            onError(it)
            onLoadUpdate(false)
            return
        }
        currentKey = getNextKey(items)
        onSuccess(items, currentKey)
        onLoadUpdate(false)
    }

    override suspend fun reset() {
        currentKey = initialKey
    }

}