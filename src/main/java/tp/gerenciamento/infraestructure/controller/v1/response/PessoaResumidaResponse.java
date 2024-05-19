package tp.gerenciamento.infraestructure.controller.v1.response;

public class PessoaResumidaResponse extends PessoaBaseResponse{
    private EnderecoResponse enderecoPrincipal;


    public EnderecoResponse getEnderecoPrincipal() {
        return enderecoPrincipal;
    }

    public void setEnderecoPrincipal(EnderecoResponse enderecoPrincipal) {
        this.enderecoPrincipal = enderecoPrincipal;
    }
}
