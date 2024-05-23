package tp.gerenciamento.domain.interactor;

import tp.gerenciamento.domain.entity.Endereco;
import tp.gerenciamento.domain.entity.Pessoa;
import tp.gerenciamento.domain.exception.ElementoNaoEncontradoException;
import tp.gerenciamento.domain.gateway.EnderecoGateway;
import tp.gerenciamento.domain.gateway.PessoaGateway;
import tp.gerenciamento.domain.util.Validator;

import java.util.List;

public class EnderecoInteractor {
    private final EnderecoGateway enderecoGateway;
    private final PessoaGateway pessoaGateway;

    public EnderecoInteractor(EnderecoGateway enderecoGateway, PessoaGateway pessoaGateway) {
        this.enderecoGateway = enderecoGateway;
        this.pessoaGateway = pessoaGateway;
    }


    public Endereco criarEndereco(String pessoaID, Endereco endereco) {
        Pessoa pessoa = getPessoa(pessoaID);
        endereco.setId(null);
        validarEndereco(endereco);
        substituirEnderecoPrincipal(pessoa, endereco);
        pessoaGateway.salvar(pessoa);
        return enderecoGateway.salvar(pessoa, endereco);
    }

    public Endereco editarEndereco(String pessoaID, Endereco endereco) {
        Pessoa pessoa = getPessoa(pessoaID);
        validarEndereco(endereco);
        Validator.stringBlank(endereco.getId(), "ID");
        Endereco enderecoOld = enderecoGateway
                .findById(endereco.getId())
                .orElseThrow(() -> new ElementoNaoEncontradoException("Endereço não encontrado"));
        enderecoOld.atualizar(endereco);
        substituirEnderecoPrincipal(pessoa, enderecoOld);
        pessoaGateway.salvar(pessoa);
        return enderecoGateway.salvar(pessoa, enderecoOld);
    }

    private void substituirEnderecoPrincipal(Pessoa pessoa, Endereco endereco) {
        List<Endereco> enderecos = pessoa.getEnderecos();
        if (enderecos == null|| enderecos.isEmpty()) {
            endereco.setEnderecoPrincipal(true);
            return;
        }

        if (endereco.isEnderecoPrincipal()) {
            enderecos.stream()
                    .filter(Endereco::isEnderecoPrincipal)
                    .forEach(e -> e.setEnderecoPrincipal(false));

        }
    }


    private Pessoa getPessoa(String pessoaID) {
        Validator.stringBlank(pessoaID, "Pessoa");
        return pessoaGateway.findById(pessoaID)
                .orElseThrow(() -> new ElementoNaoEncontradoException("Pessoa não encontrada"));
    }

    private void validarEndereco(Endereco endereco) {
        Validator.stringBlank(endereco.getCep(), "CEP");
        Validator.stringBlank(endereco.getCidade(), "Cidade");
        Validator.stringBlank(endereco.getEstado(), "Estado");
        Validator.stringBlank(endereco.getLogradouro(), "Logradouro");
        Validator.stringBlank(endereco.getNumero(), "Número");
    }

    public void deletarEndereco(String id) {
        Validator.stringBlank(id, "ID");
        Endereco enderecoOld = enderecoGateway
                .findById(id)
                .orElseThrow(() -> new ElementoNaoEncontradoException("Endereço não encontrado"));

        enderecoGateway.deleteById(enderecoOld.getId());
    }
}
