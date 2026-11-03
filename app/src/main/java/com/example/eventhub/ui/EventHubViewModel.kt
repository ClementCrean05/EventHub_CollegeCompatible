package com.example.eventhub.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventhub.data.EventRepository
import com.example.eventhub.data.local.EventHubDatabase
import com.example.eventhub.model.Category
import com.example.eventhub.model.Event
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class EventHubUiState(
    val events: List<Event> = emptyList(),
    val categories: List<Category> = emptyList(),
    val isLoading: Boolean = true,
    val loadError: String? = null,
    val actionMessage: String? = null,
    val isAdding: Boolean = false
)

class EventHubViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = EventRepository(
        EventHubDatabase.getInstance(application)
    )
    private val mutableUiState = MutableStateFlow(EventHubUiState())
    val uiState = mutableUiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                repository.seedIfEmpty()
                combine(repository.events, repository.categories) { events, categories ->
                    events to categories
                }.collect { (events, categories) ->
                    mutableUiState.update {
                        it.copy(events = events, categories = categories,
                            isLoading = false, loadError = null)
                    }
                }
            } catch (cancelled: CancellationException) {
                throw cancelled
            } catch (error: Exception) {
                mutableUiState.update {
                    it.copy(isLoading = false,
                        loadError = error.message ?: "Unable to load events")
                }
            }
        }
    }

    fun addTestEvent() {
        val current = mutableUiState.value
        val categoryId = current.categories.firstOrNull()?.id ?: return
        if (current.isLoading || current.isAdding || current.loadError != null) return
        mutableUiState.update { it.copy(isAdding = true, actionMessage = null) }
        viewModelScope.launch {
            try {
                val id = repository.addEvent(categoryId)
                mutableUiState.update {
                    it.copy(actionMessage = "Saved event with ID $id")
                }
            } catch (cancelled: CancellationException) {
                throw cancelled
            } catch (error: Exception) {
                mutableUiState.update {
                    it.copy(actionMessage = error.message ?: "Unable to save event")
                }
            } finally {
                mutableUiState.update { it.copy(isAdding = false) }
            }
        }
    }
}