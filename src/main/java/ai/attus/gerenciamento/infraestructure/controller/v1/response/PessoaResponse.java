package ai.attus.gerenciamento.infraestructure.controller.v1.response;

import lombok.Data;

import java.util.List;
@Data
public class PessoaResponse extends PessoaBaseResponse{

    private List<EnderecoResponse> enderecos;

    public List<EnderecoResponse> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoResponse> enderecos) {
        this.enderecos = enderecos;
    }
}
