package tp.gerenciamento.infraestructure.controller.v1.mapper;

import tp.gerenciamento.domain.entity.Pessoa;
import tp.gerenciamento.domain.entity.PessoaResumida;
import tp.gerenciamento.infraestructure.controller.v1.request.PessoaRequest;
import tp.gerenciamento.infraestructure.controller.v1.response.PessoaResponse;
import tp.gerenciamento.infraestructure.controller.v1.response.PessoaResumidaResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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
        pessoaResponse.setEnderecoPrincipal(EnderecoControllerMapper.enderecoResponse(pessoa.getEnderecoPrincipal()));
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
