package felipe.mateus.tarefas.services;

import felipe.mateus.tarefas.entities.Tarefa;
import felipe.mateus.tarefas.repositories.TarefaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TarefaServiceTest {

    @Mock
    private TarefaRepository tarefaRepository;

    @InjectMocks
    private TarefaService tarefaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSalvarTarefa() {
        Tarefa tarefa = new Tarefa(1L, "Tarefa teste", "Descrição da tarefa", false, false, null);
        when(tarefaRepository.save(any(Tarefa.class))).thenReturn(tarefa);

        Tarefa novaTarefa = tarefaService.salvar("Tarefa teste", "Descrição da tarefa");

        assertNotNull(novaTarefa);
        assertEquals("Tarefa teste", novaTarefa.getTitulo());
        assertEquals("Descrição da tarefa", novaTarefa.getDescricao());
        assertFalse(novaTarefa.isConcluida());
        assertFalse(novaTarefa.isArquivada());
    }

    @Test
    void testExcluirTarefa() {
        Long id = 1L;
        doNothing().when(tarefaRepository).deleteById(id);

        tarefaService.excluir(id);

        verify(tarefaRepository, times(1)).deleteById(id);
    }

    @Test
    void testBuscarTarefaPorIdExistente() {
        Long id = 1L;
        Tarefa tarefa = new Tarefa(id, "Tarefa teste", "Descrição da tarefa", false, false, null);
        when(tarefaRepository.findById(id)).thenReturn(Optional.of(tarefa));

        Tarefa tarefaEncontrada = tarefaService.buscarPorId(id);

        assertNotNull(tarefaEncontrada);
        assertEquals(id, tarefaEncontrada.getId());
    }

    @Test
    void testBuscarTarefaPorIdInexistente() {
        Long id = 1L;
        when(tarefaRepository.findById(id)).thenReturn(Optional.empty());

        Tarefa tarefaEncontrada = tarefaService.buscarPorId(id);

        assertNull(tarefaEncontrada);
    }

    @Test
    void testBuscarTodasTarefas() {
        List<Tarefa> tarefas = Arrays.asList(
                new Tarefa(1L, "Tarefa 1", "Descrição 1", false, false, null),
                new Tarefa(2L, "Tarefa 2", "Descrição 2", true, false, null)
        );
        when(tarefaRepository.findAll()).thenReturn(tarefas);

        Iterable<Tarefa> tarefasEncontradas = tarefaService.buscarTodos();

        assertEquals(tarefas, tarefasEncontradas);
    }

    @Test
    void testConcluirTarefaExistente() {
        Long id = 1L;
        Tarefa tarefa = new Tarefa(id, "Tarefa teste", "Descrição da tarefa", false, false, null);
        when(tarefaRepository.findById(id)).thenReturn(Optional.of(tarefa));
        when(tarefaRepository.save(tarefa)).thenReturn(tarefa);

        Tarefa tarefaConcluida = tarefaService.concluir(id);

        assertNotNull(tarefaConcluida);
        assertTrue(tarefaConcluida.isConcluida());
    }

    @Test
    void testConcluirTarefaInexistente() {
        Long id = 1L;
        when(tarefaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> tarefaService.concluir(id));
    }

    @Test
    void testArquivarTarefaExistente() {
        Long id = 1L;
        Tarefa tarefa = new Tarefa(id, "Tarefa teste", "Descrição da tarefa", false, false, null);
        when(tarefaRepository.findById(id)).thenReturn(Optional.of(tarefa));
        when(tarefaRepository.save(tarefa)).thenReturn(tarefa);

        Tarefa tarefaArquivada = tarefaService.arquivar(id);

        assertNotNull(tarefaArquivada);
        assertTrue(tarefaArquivada.isArquivada());
    }

    @Test
    void testArquivarTarefaInexistente() {
        Long id = 1L;
        when(tarefaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> tarefaService.arquivar(id));
    }

    @Test
    void testDesarquivarTarefaExistente() {
        Long id = 1L;
        Tarefa tarefa = new Tarefa(id, "Tarefa teste", "Descrição da tarefa", false, true, null);
        when(tarefaRepository.findById(id)).thenReturn(Optional.of(tarefa));
        when(tarefaRepository.save(tarefa)).thenReturn(tarefa);

        Tarefa tarefaDesarquivada = tarefaService.desarquivar(id);

        assertNotNull(tarefaDesarquivada);
        assertFalse(tarefaDesarquivada.isArquivada());
    }

    @Test
    void testDesarquivarTarefaInexistente() {
        Long id = 1L;
        when(tarefaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> tarefaService.desarquivar(id));
    }

    @Test
    void testAtualizarTarefaExistente() {
        Long id = 1L;
        Tarefa tarefa = new Tarefa(id, "Tarefa teste", "Descrição da tarefa", false, true, null);
        when(tarefaRepository.findById(id)).thenReturn(Optional.of(tarefa));
        when(tarefaRepository.save(tarefa)).thenReturn(tarefa);

        Tarefa tarefaAtualizada = tarefaService.atualizar(id, "Tarefa atualizada", "Nova descrição");

        assertNotNull(tarefaAtualizada);
        assertEquals("Tarefa atualizada", tarefaAtualizada.getTitulo());
        assertEquals("Nova descrição", tarefaAtualizada.getDescricao());
    }

    @Test
    void testAtualizarTarefaInexistente() {
        Long id = 1L;
        when(tarefaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> tarefaService.atualizar(id, "Tarefa atualizada", "Nova descrição"));
    }
}
