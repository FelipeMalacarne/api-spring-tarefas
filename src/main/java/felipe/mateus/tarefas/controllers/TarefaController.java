package felipe.mateus.tarefas.controllers;

import felipe.mateus.tarefas.entities.Tarefa;
import felipe.mateus.tarefas.requests.CreateTarefaRequest;
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

}