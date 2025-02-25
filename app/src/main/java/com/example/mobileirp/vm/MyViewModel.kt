package com.example.mobileirp.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.mobileirp.repository.MyPagingSource

class MyViewModel : ViewModel() {
    private val pager = Pager(PagingConfig(pageSize = 20, initialLoadSize = 20)) {
        MyPagingSource()
    }
    val pagingDataFlow = pager.flow
        .cachedIn(viewModelScope)
}