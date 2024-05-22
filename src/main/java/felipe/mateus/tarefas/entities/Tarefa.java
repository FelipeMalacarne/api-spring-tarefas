package felipe.mateus.tarefas.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Table(name = "tarefas")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @Column(nullable = true, length = 255)
    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean concluida;

    @Column(nullable = false)
    private boolean arquivada;

    @Column(nullable = false, updatable = false)
    private Timestamp dataCriacao;
}
