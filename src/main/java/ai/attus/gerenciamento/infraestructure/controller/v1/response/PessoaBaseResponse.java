package ai.attus.gerenciamento.infraestructure.controller.v1.response;

import lombok.Data;

import java.time.LocalDate;
@Data
public class PessoaBaseResponse {
    private String id;
    private String nome;
    private LocalDate dataNascimento;

}
