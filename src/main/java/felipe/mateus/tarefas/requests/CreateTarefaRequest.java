package felipe.mateus.tarefas.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTarefaRequest(
        @NotBlank(message = "O título é obrigatório")
        @Size(min = 3, max = 50, message = "O título deve ter no máximo 50 caracteres")
        String titulo,

        @NotBlank(message = "A descrição é obrigatória")
        @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres")
        String descricao
) {
}
