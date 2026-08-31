package com.example.dvdlibrary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.dvdlibrary.data.DvdAppDatabase
import com.example.dvdlibrary.data.Film
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest

class IntroViewModel(database: DvdAppDatabase) : ViewModel() {

    var currentSortItemState = MutableStateFlow(0)
    var sortOrder = MutableStateFlow(0)

    var currentFilterItem = MutableStateFlow(0)
    var searchTerm = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val pagedFilms: Flow<PagingData<Film>> =
        combine(
            currentSortItemState,
            sortOrder,
            searchTerm, currentFilterItem
        ) { sortItem, sortOrder, searchTerm, filterItem ->
            Triple(sortItem to sortOrder, searchTerm, filterItem)
        }.flatMapLatest { (sortAndOrder, searchTerm, filterItem) ->
            val (sortItem, sortOrder) = sortAndOrder
            Pager(config = PagingConfig(pageSize = 20, enablePlaceholders = false))
            {
                if (searchTerm.isNotBlank()) {
                    when (filterItem) {
                        0 -> database.filmsDao()
                            .filterByTitle(searchTerm)

                        1 -> database.filmsDao().filterByYear(searchTerm)
                        2 -> database.filmsDao().filterByStarring(searchTerm)
                        3 -> database.filmsDao().filterByGenre(searchTerm)

                        else -> database.filmsDao().filterByTitle(searchTerm)
                    }
                } else {

                    when (sortItem) {
                        0 -> if (sortOrder == 0) {
                            database.filmsDao().filmsByTitleAsc()
                        } else {
                            database.filmsDao().filmsByTitleDesc()
                        }

                        1 ->
                            if (sortOrder == 0) {
                                database.filmsDao().filmsByGenreAsc()
                            } else {
                                database.filmsDao().filmsByGenreDesc()
                            }

                        2 ->
                            if (sortOrder == 0) {
                                database.filmsDao().filmsByYearAsc()
                            } else {
                                database.filmsDao().filmsByYearDesc()
                            }

                        3 ->
                            if (sortOrder == 0) {
                                database.filmsDao().filmsByRuntimeAsc()
                            } else {
                                database.filmsDao().filmsByRuntimeDesc()
                            }

                        4 ->
                            if (sortOrder == 0) {
                                database.filmsDao().filmsByIdAsc()
                            } else {
                                database.filmsDao().filmsByIdDesc()
                            }

                        else -> database.filmsDao().filmsByTitleAsc()
                    }
                }
            }.flow
        }.cachedIn(viewModelScope)
}

class IntroViewModelFactory(private val database: DvdAppDatabase) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return IntroViewModel(database = database) as T
    }
}