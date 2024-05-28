package felipe.mateus.tarefas.controllers;

import felipe.mateus.tarefas.entities.Tarefa;
import felipe.mateus.tarefas.requests.CreateTarefaRequest;
import felipe.mateus.tarefas.requests.UpdateTarefaRequest;
import felipe.mateus.tarefas.services.TarefaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TarefaControllerTest {

    @Mock
    private TarefaService tarefaService;

    @InjectMocks
    private TarefaController tarefaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarTodasTarefas() {
        List<Tarefa> tarefas = Arrays.asList(
                new Tarefa(1L, "Tarefa 1", "Descrição 1", false, false, null),
                new Tarefa(2L, "Tarefa 2", "Descrição 2", true, false, null)
        );
        when(tarefaService.buscarTodos()).thenReturn(tarefas);

        Iterable<Tarefa> tarefasEncontradas = tarefaController.buscarTodas();

        assertEquals(tarefas, tarefasEncontradas);
    }

    @Test
    void testSalvarTarefa() {
        CreateTarefaRequest request = new CreateTarefaRequest("Nova tarefa", "Descrição da nova tarefa");
        Tarefa tarefaSalva = new Tarefa(1L, "Nova tarefa", "Descrição da nova tarefa", false, false, null);
        when(tarefaService.salvar(request.titulo(), request.descricao())).thenReturn(tarefaSalva);

        ResponseEntity<Tarefa> response = tarefaController.salvar(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(tarefaSalva, response.getBody());
    }

    @Test
    void testConcluirTarefaExistente() {
        Long id = 1L;
        Tarefa tarefaConcluida = new Tarefa(id, "Tarefa concluída", "Descrição", true, false, null);
        when(tarefaService.concluir(id)).thenReturn(tarefaConcluida);

        ResponseEntity<Tarefa> response = tarefaController.concluir(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tarefaConcluida, response.getBody());
    }

    @Test
    void testConcluirTarefaInexistente() {
        Long id = 1L;
        when(tarefaService.concluir(id)).thenThrow(new RuntimeException("Tarefa não encontrada"));

        ResponseEntity<Tarefa> response = tarefaController.concluir(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testArquivarTarefaExistente() {
        Long id = 1L;
        Tarefa tarefaArquivada = new Tarefa(id, "Tarefa arquivada", "Descrição", false, true, null);
        when(tarefaService.arquivar(id)).thenReturn(tarefaArquivada);

        ResponseEntity<Tarefa> response = tarefaController.arquivar(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tarefaArquivada, response.getBody());
    }

    @Test
    void testArquivarTarefaInexistente() {
        Long id = 1L;
        when(tarefaService.arquivar(id)).thenThrow(new RuntimeException("Tarefa não encontrada"));

        ResponseEntity<Tarefa> response = tarefaController.arquivar(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testExcluirTarefa() {
        Long id = 1L;
        doNothing().when(tarefaService).excluir(id);

        ResponseEntity<Void> response = tarefaController.excluir(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testBuscarTarefaPorIdExistente() {
        Long id = 1L;
        Tarefa tarefaEncontrada = new Tarefa(id, "Tarefa encontrada", "Descrição", false, false, null);
        when(tarefaService.buscarPorId(id)).thenReturn(tarefaEncontrada);

        ResponseEntity<Tarefa> response = tarefaController.buscarPorId(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tarefaEncontrada, response.getBody());
    }

    @Test
    void testBuscarTarefaPorIdInexistente() {
        Long id = 1L;
        when(tarefaService.buscarPorId(id)).thenReturn(null);

        ResponseEntity<Tarefa> response = tarefaController.buscarPorId(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testAtualizarTarefaExistente() {
        Long id = 1L;
        UpdateTarefaRequest request = new UpdateTarefaRequest("Tarefa atualizada", "Descrição atualizada");
        Tarefa tarefaAtualizada = new Tarefa(id, "Tarefa atualizada", "Descrição atualizada", false, false, null);
        when(tarefaService.atualizar(id, request.titulo(), request.descricao())).thenReturn(tarefaAtualizada);

        ResponseEntity<Tarefa> response = tarefaController.atualizar(id, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tarefaAtualizada, response.getBody());
    }

    @Test
    void testAtualizarTarefaInexistente() {
        Long id = 1L;
        UpdateTarefaRequest request = new UpdateTarefaRequest("Tarefa atualizada", "Descrição atualizada");
        when(tarefaService.atualizar(id, request.titulo(), request.descricao())).thenReturn(null);

        ResponseEntity<Tarefa> response = tarefaController.atualizar(id, request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
