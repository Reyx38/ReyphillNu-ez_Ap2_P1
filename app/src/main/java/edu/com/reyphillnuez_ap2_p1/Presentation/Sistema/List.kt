package edu.com.reyphillnuez_ap2_p1.Presentation.Sistema

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.com.reyphillnuez_ap2_p1.Data.Local.entities.TareasEntity


@Composable
fun TareasList(
    viewModel: ViewModel = hiltViewModel(),
    onEdit: (Int?) -> Unit,
    createTarea: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    TareasListBodyScreen(
        uiState,
        createTarea,
        onEdit,
        onDelete = {
            viewModel.onEvent(Event.onChangeTareasId(it))
            viewModel.onEvent(Event.Delete)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TareasListBodyScreen(
    uiState: UiState,
    createSistema: () -> Unit,
    onEdit: (Int?) -> Unit,
    onDelete: (Int) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var tareaId by remember { mutableStateOf<Int?>(null) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Lista de tareas",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = createSistema) {
                Icon(Icons.Filled.Add, contentDescription = "Agregar Nuevo")
            }
        },

        ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .padding(12.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(uiState.tareas) {
                    SistemaRow(
                        it,
                        onEdit,
                        onDelete = {
                            tareaId = it
                            showDialog = true
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
    if (showDialog) {
        val tareas = uiState.tareas.firstOrNull { it.tareaId == tareaId }
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Confirmar eliminación") },
            text = { Text("¿Estás seguro de que deseas eliminar a ${tareas?.descripcion}?") },
            confirmButton = {
                TextButton(onClick = {
                    tareas?.let { onDelete(it.tareaId!!) }
                    showDialog = false
                }) {
                    Text("Sí")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}



@Composable
fun SistemaRow(
    tareas: TareasEntity?,
    onEdit: (Int?) -> Unit,
    onDelete: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(2f)) {
                Text(
                    text = "ID: ${tareas?.tareaId}",
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = tareas?.descripcion ?: "",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = tareas?.tiempo.toString(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onEdit(tareas?.tareaId) }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(onClick = { onDelete(tareas?.tareaId!!) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Eliminar",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}