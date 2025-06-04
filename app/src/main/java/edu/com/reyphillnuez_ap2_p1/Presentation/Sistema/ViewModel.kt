package edu.com.reyphillnuez_ap2_p1.Presentation.Sistema

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.com.reyphillnuez_ap2_p1.Data.Local.entities.TareasEntity
import edu.com.reyphillnuez_ap2_p1.Data.Repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val tareasRepository: Repository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        getSistemas()
    }

    fun onEvent(event: Event) {
        when (event) {
            Event.Delete -> deleteTareas()
            Event.New -> new()
            is Event.onChangeDescripcion -> onChangeDescripcion(event.descripcion)
            is Event.onChangeTareasId -> onChangeId(event.id)
            is Event.onChangeTiempo -> onChangeTiempo(event.tiempo)
            Event.Save -> saveTareas()
        }
    }



    fun find(tareaId: Int?){
        viewModelScope.launch {
            if (tareaId != null){
                val tarea = tareasRepository.find(tareaId)
                _uiState.update {
                    it.copy(
                        tareasId = tarea?.tareaId,
                        descripcion = tarea?.descripcion,
                        tiempo = tarea?.tiempo

                    )
                }
            }
        }

    }

    private fun new(){
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    descripcion = "",
                    tiempo = 0,
                    errorTiempo = "",
                    errorDescripcion = "",
                )
            }
        }
    }

    private fun deleteTareas() {
        viewModelScope.launch {
            tareasRepository.delete(_uiState.value.toEntity())
        }
    }

    private fun saveTareas() {
        viewModelScope.launch {
            if (_uiState.value.descripcion.isNullOrBlank()) {
                _uiState.update {
                    it.copy(
                        errorDescripcion = "El nombre no puede estar vacio"
                    )
                }
            } else {
                if (_uiState.value.tiempo!! <= 0) {
                    _uiState.update {
                        it.copy(
                            errorTiempo = "debe asignar un tiempo"
                        )
                    }
                } else {
                    tareasRepository.save(_uiState.value.toEntity())
                    new()
                }
            }
        }
    }

    private fun onChangeTiempo(tiempo: Int?) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    tiempo = tiempo
                )
            }
        }
    }


    private fun onChangeDescripcion(descripcion: String?) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    descripcion = descripcion
                )
            }
        }
    }

    fun onChangeId(id: Int?) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    tareasId = id
                )
            }
        }
    }

    private fun getSistemas() {
        viewModelScope.launch {
            tareasRepository.getAll().collect { tareas ->
                _uiState.update {
                    it.copy(
                        tareas = tareas
                    )
                }
            }
        }
    }

    fun UiState.toEntity() = TareasEntity(
        tareaId = tareasId,
        descripcion = descripcion,
        tiempo = tiempo
    )
}