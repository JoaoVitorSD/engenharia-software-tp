package tp.gerenciamento.infraestructure.controller.v1.request;

import lombok.Data;

@Data
public class EnderecoRequest {
    private String id;
    private String pessoa;
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;
    private String estado;

    private boolean enderecoPrincipal;

}
