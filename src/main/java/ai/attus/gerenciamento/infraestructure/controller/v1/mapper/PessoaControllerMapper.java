package ai.attus.gerenciamento.infraestructure.controller.v1.mapper;

import ai.attus.gerenciamento.domain.entity.Pessoa;
import ai.attus.gerenciamento.domain.entity.PessoaResumida;
import ai.attus.gerenciamento.infraestructure.controller.v1.request.PessoaRequest;
import ai.attus.gerenciamento.infraestructure.controller.v1.response.PessoaResponse;
import ai.attus.gerenciamento.infraestructure.controller.v1.response.PessoaResumidaResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static ai.attus.gerenciamento.infraestructure.controller.v1.mapper.EnderecoControllerMapper.enderecoResponse;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PessoaControllerMapper {


    public static Pessoa requestToEntity(PessoaRequest pessoaRequest) {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(pessoaRequest.getId());
        pessoa.setNome(pessoaRequest.getNome());
        pessoa.setDataNascimento(pessoaRequest.getDataNascimento());
        if (pessoaRequest.getEnderecos() != null) {
            pessoa.setEnderecos(pessoaRequest.getEnderecos().stream().map(EnderecoControllerMapper::requestToEndereco).toList());
        }
        return pessoa;
    }

    public static PessoaResumidaResponse entityToResumidaResponse(PessoaResumida pessoa) {
        PessoaResumidaResponse pessoaResponse = new PessoaResumidaResponse();
        pessoaResponse.setId(pessoa.getId());
        pessoaResponse.setNome(pessoa.getNome());
        pessoaResponse.setDataNascimento(pessoa.getDataNascimento());
        pessoaResponse.setEnderecoPrincipal(enderecoResponse(pessoa.getEnderecoPrincipal()));
        return pessoaResponse;
    }

    public static PessoaResponse entityToResponse(Pessoa pessoa) {
        PessoaResponse pessoaResponse = new PessoaResponse();
        pessoaResponse.setId(pessoa.getId());
        pessoaResponse.setNome(pessoa.getNome());
        pessoaResponse.setDataNascimento(pessoa.getDataNascimento());
        if (pessoa.getEnderecos() != null && !pessoa.getEnderecos().isEmpty()) {
            pessoaResponse.setEnderecos(pessoa.getEnderecos()
                    .stream()
                    .map(EnderecoControllerMapper::enderecoResponse)
                    .toList());
        }
        return pessoaResponse;
    }

}
