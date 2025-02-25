package com.example.mobileirp.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mobileirp.APIConstants
import com.example.mobileirp.model.MyRowItem
import kotlinx.coroutines.delay
import kotlin.math.max

// The initial key used for loading.
// This is the item id of the first item that will be loaded
private const val STARTING_KEY = 0
private const val LOAD_DELAY_MILLIS = 3_000L

/**
 * A [PagingSource] that loads items for paging. The [Int] is the paging key or query that is used to fetch more
 * data, and the [MyRowItem] specifies that the [PagingSource] fetches an [MyRowItem] [List].
 */
class MyPagingSource : PagingSource<Int, MyRowItem>() {
    private var pageNo: Int = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MyRowItem> {
        // If params.key is null, it is the first load, so we start loading with STARTING_KEY
        val startKey = params.key ?: STARTING_KEY
        pageNo = params.key?.div(params.loadSize) ?: 0
        pageNo++
        Log.i("SizePage", pageNo.toString())
        // We fetch as many items as hinted to by params.loadSize
        val range = startKey.until(startKey + params.loadSize)

        Log.i("SizePage : ", "loadSize ${params.loadSize }:Range:$range PageNo: $pageNo: ParamsKey: ${params.key} ")

        // NOTE: We fetch data from repository/api here.
        // For demonstration purpose only, we are generating hard coded data.
        val response = range.map { number ->
            MyRowItem(
                pageId = pageNo,
                id = number ,
                name = "Item $number: ********************",
            )
//
        }


        // Simulate a delay for loads after the initial load
        if (startKey != STARTING_KEY) delay(LOAD_DELAY_MILLIS)

        return LoadResult.Page(
            data = response,
            prevKey = when (startKey) {
                STARTING_KEY -> null
                else -> when (val prevKey = ensureValidKey(key = range.first - params.loadSize)) {
                    // We're at the start, there's nothing more to load
                    STARTING_KEY -> null
                    else -> prevKey
                }
            },

            nextKey = range.last + 1
        )
    }

    // The refresh key is used for the initial load of the next PagingSource, after invalidation
    override fun getRefreshKey(state: PagingState<Int, MyRowItem>): Int? {
        // In our case we grab the item closest to the anchor position
        // then return its id - (state.config.pageSize / 2) as a buffer
        val anchorPosition = state.anchorPosition ?: return null
        val item = state.closestItemToPosition(anchorPosition) ?: return null
        Log.i("SizePage anchor: ", state.anchorPosition.toString())
        //pageNo = state.config.pageSize
        return ensureValidKey(key = item.id - (state.config.pageSize / 2))
    }

    /**
     * Makes sure the paging key is never less than [STARTING_KEY]
     */
    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)
}