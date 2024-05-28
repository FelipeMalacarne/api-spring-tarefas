package felipe.mateus.tarefas.services;

import felipe.mateus.tarefas.entities.Tarefa;
import felipe.mateus.tarefas.repositories.TarefaRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public Tarefa salvar(String titulo, String descricao) {

        Tarefa tarefa = Tarefa.builder()
                .titulo(titulo)
                .descricao(descricao)
                .concluida(false)
                .arquivada(false)
                .dataCriacao(new Timestamp(System.currentTimeMillis()))
                .build();

        return tarefaRepository.save(tarefa);
    }

    public void excluir(Long id) {
        tarefaRepository.deleteById(id);
    }

    public Tarefa buscarPorId(Long id) {
        return tarefaRepository.findById(id).orElse(null);
    }

    public Iterable<Tarefa> buscarTodos() {
        return tarefaRepository.findAll();
    }

    public Tarefa concluir(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id).orElse(null);
        if (tarefa == null) {
            throw new ResourceNotFoundException("Tarefa não encontrada");
        }

        tarefa.setConcluida(true);
        return tarefaRepository.save(tarefa);
    }

    public Tarefa arquivar(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id).orElse(null);
        if (tarefa == null) {
            throw new ResourceNotFoundException("Tarefa não encontrada");
        }

        tarefa.setArquivada(true);
        return tarefaRepository.save(tarefa);
    }

    public Tarefa desarquivar(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id).orElse(null);
        if (tarefa == null) {
            throw new ResourceNotFoundException("Tarefa não encontrada");
        }

        tarefa.setArquivada(false);
        return tarefaRepository.save(tarefa);
    }

    public Tarefa atualizar(Long id, String titulo, String descricao ) {
        Tarefa tarefa = tarefaRepository.findById(id).orElse(null);
        if (tarefa == null) {
            throw new ResourceNotFoundException("Tarefa não encontrada");
        }

        tarefa.setTitulo(titulo);
        tarefa.setDescricao(descricao);
        return tarefaRepository.save(tarefa);
    }
}
