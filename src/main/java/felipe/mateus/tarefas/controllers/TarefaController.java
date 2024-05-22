package felipe.mateus.tarefas.controllers;

import felipe.mateus.tarefas.entities.Tarefa;
import felipe.mateus.tarefas.requests.CreateTarefaRequest;
import felipe.mateus.tarefas.services.TarefaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

   @PostMapping("/tarefas/concluir")
    public void concluir(Long id) {
        tarefaService.concluir(id);
    }


}
