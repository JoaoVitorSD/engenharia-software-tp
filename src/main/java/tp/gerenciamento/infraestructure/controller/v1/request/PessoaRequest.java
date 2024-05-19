package tp.gerenciamento.infraestructure.controller.v1.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;


@Data
public class PessoaRequest {
    private String id;
    private String nome;
    private LocalDate dataNascimento;

    private ArrayList<EnderecoRequest> enderecos;
}
