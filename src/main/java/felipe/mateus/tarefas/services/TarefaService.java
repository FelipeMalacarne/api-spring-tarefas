package felipe.mateus.tarefas.services;

import felipe.mateus.tarefas.entities.Tarefa;
import felipe.mateus.tarefas.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void concluir(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id).orElse(null);
        if (tarefa != null) {
            tarefa.setConcluida(true);
            tarefaRepository.save(tarefa);
        }
    }

    public void arquivar(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id).orElse(null);
        if (tarefa != null) {
            tarefa.setArquivada(true);
            tarefaRepository.save(tarefa);
        }
    }

    public void desarquivar(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id).orElse(null);
        if (tarefa != null) {
            tarefa.setArquivada(false);
            tarefaRepository.save(tarefa);
        }
    }

    public void atualizar(Tarefa tarefa) {
        tarefaRepository.save(tarefa);
    }
}
