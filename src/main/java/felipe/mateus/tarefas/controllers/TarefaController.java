package felipe.mateus.tarefas.controllers;

import felipe.mateus.tarefas.entities.Tarefa;
import felipe.mateus.tarefas.requests.CreateTarefaRequest;
import felipe.mateus.tarefas.requests.UpdateTarefaRequest;
import felipe.mateus.tarefas.services.TarefaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping("/tarefas")
    public Iterable<Tarefa> buscarTodas() {
        return tarefaService.buscarTodos();
    }

    @PostMapping("/tarefas")
    public ResponseEntity<Tarefa> salvar(@RequestBody @Valid CreateTarefaRequest request) {
        var tarefa = tarefaService.salvar(request.titulo(), request.descricao());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tarefa);
    }

   @PostMapping("/tarefas/{id}/concluir")
    public ResponseEntity<Tarefa> concluir(@PathVariable Long id) {
        try {
            var tarefa =  tarefaService.concluir(id);
            return ResponseEntity.ok(tarefa);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/tarefas/{id}/arquivar")
    public ResponseEntity<Tarefa> arquivar(@PathVariable Long id) {
        try {
            var tarefa =  tarefaService.arquivar(id);
            return ResponseEntity.ok(tarefa);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/tarefas/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        tarefaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tarefas/{id}")
    public ResponseEntity<Tarefa> buscarPorId(@PathVariable Long id) {
        var tarefa = tarefaService.buscarPorId(id);
        if (tarefa == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tarefa);
    }

    @PutMapping("/tarefas/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @RequestBody @Valid UpdateTarefaRequest request) {
        var tarefa = tarefaService.atualizar(id, request.titulo(), request.descricao());
        if (tarefa == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tarefa);
    }


}
