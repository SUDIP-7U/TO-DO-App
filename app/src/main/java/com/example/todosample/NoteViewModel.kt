package com.example.todosample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repo: NoteRepository
    val allNotes: LiveData<List<Note>>

    init {
        val dao = NoteDatabase.getDatabase(application).noteDao()
        repo = NoteRepository(dao)
        allNotes = repo.allNotes
    }

    fun insert(note: Note) = viewModelScope.launch { repo.insert(note) }

    fun delete(note: Note) = viewModelScope.launch { repo.delete(note) }
}
