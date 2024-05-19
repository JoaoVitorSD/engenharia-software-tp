package tp.gerenciamento.util;

import tp.gerenciamento.domain.entity.Endereco;
import tp.gerenciamento.domain.entity.Pessoa;
import tp.gerenciamento.infraestructure.controller.v1.request.EnderecoRequest;
import tp.gerenciamento.infraestructure.controller.v1.request.PessoaRequest;

import static tp.gerenciamento.infraestructure.conf.EntityFabric.randomEndereco;
import static tp.gerenciamento.infraestructure.conf.EntityFabric.randomPessoa;

public class RequestFabric {


    public static PessoaRequest pessoaRequest(){
        Pessoa pessoa = randomPessoa();
        PessoaRequest pessoaRequest = new PessoaRequest();
        pessoaRequest.setId(pessoa.getId());
        pessoaRequest.setNome(pessoa.getNome());
        pessoaRequest.setDataNascimento(pessoa.getDataNascimento());
        return pessoaRequest;
    }


    public static EnderecoRequest enderecoRequest(String idPessoa){
        EnderecoRequest enderecoRequest = new EnderecoRequest();
        Endereco endereco = randomEndereco();
        enderecoRequest.setPessoa(idPessoa);
        enderecoRequest.setId(endereco.getId());
        enderecoRequest.setLogradouro(endereco.getLogradouro());
        enderecoRequest.setNumero(endereco.getNumero());
        enderecoRequest.setCidade(endereco.getCidade());
        enderecoRequest.setEstado(endereco.getEstado());
        enderecoRequest.setCep(endereco.getCep());
        enderecoRequest.setEnderecoPrincipal(true);
        return enderecoRequest;
    }

}
