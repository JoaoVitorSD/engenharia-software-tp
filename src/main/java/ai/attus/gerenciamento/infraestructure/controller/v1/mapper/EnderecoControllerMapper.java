package ai.attus.gerenciamento.infraestructure.controller.v1.mapper;

import ai.attus.gerenciamento.domain.entity.Endereco;
import ai.attus.gerenciamento.infraestructure.controller.v1.request.EnderecoRequest;
import ai.attus.gerenciamento.infraestructure.controller.v1.response.EnderecoResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnderecoControllerMapper {
    public static EnderecoResponse enderecoResponse(Endereco endereco){
        EnderecoResponse enderecoResponse = new EnderecoResponse();
        enderecoResponse.setId(endereco.getId());
        enderecoResponse.setCep(endereco.getCep());
        enderecoResponse.setCidade(endereco.getCidade());
        enderecoResponse.setEstado(endereco.getEstado());
        enderecoResponse.setLogradouro(endereco.getLogradouro());
        enderecoResponse.setNumero(endereco.getNumero());
        enderecoResponse.setEnderecoPrincipal(endereco.isEnderecoPrincipal());
        return enderecoResponse;
    }


    public static Endereco requestToEndereco(EnderecoRequest enderecoRequest){
        Endereco endereco = new Endereco();
        endereco.setId(enderecoRequest.getId());
        endereco.setCep(enderecoRequest.getCep());
        endereco.setCidade(enderecoRequest.getCidade());
        endereco.setEstado(enderecoRequest.getEstado());
        endereco.setLogradouro(enderecoRequest.getLogradouro());
        endereco.setNumero(enderecoRequest.getNumero());
        endereco.setEnderecoPrincipal(enderecoRequest.isEnderecoPrincipal());
        return endereco;
    }

}
