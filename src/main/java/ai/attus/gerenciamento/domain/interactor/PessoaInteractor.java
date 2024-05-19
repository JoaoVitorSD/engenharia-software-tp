package ai.attus.gerenciamento.domain.interactor;

import ai.attus.gerenciamento.domain.entity.Pessoa;
import ai.attus.gerenciamento.domain.entity.PessoaResumida;
import ai.attus.gerenciamento.domain.exception.ElementoNaoEncontradoException;
import ai.attus.gerenciamento.domain.exception.ParametroInvalidoException;
import ai.attus.gerenciamento.domain.gateway.PessoaGateway;
import ai.attus.gerenciamento.domain.input.PaginaPosicao;
import ai.attus.gerenciamento.domain.output.Pagina;
import ai.attus.gerenciamento.domain.util.Validator;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

public class PessoaInteractor {


    private final PessoaGateway pessoaGateway;


    public PessoaInteractor(PessoaGateway pessoaGateway) {
        this.pessoaGateway = pessoaGateway;
    }

    public Pessoa criar(Pessoa pessoa) {
        validarPessoa(pessoa);
        pessoa.setId(null);
        pessoa.padronizarEnderecoPrincipal();
        return pessoaGateway.salvar(pessoa);
    }

    public Pessoa editar(Pessoa pessoa) {
        validarPessoa(pessoa);
        Validator.stringBlank(pessoa.getId(), "id");
        Pessoa pessoaAntiga = pessoaGateway.findById(pessoa.getId())
                .orElseThrow(() -> new ElementoNaoEncontradoException("Pessoa não encontrada"));
        pessoaAntiga.atualizar(pessoa);
        pessoaAntiga.padronizarEnderecoPrincipal();
        return pessoaGateway.salvar(pessoaAntiga);
    }


    private void validarPessoa(Pessoa pessoa) {
        Validator.stringBlank(pessoa.getNome(), "Nome");
        Validator.dateNull(pessoa.getDataNascimento(), "Data de Nascimento");

        if(pessoa.getDataNascimento().isAfter(LocalDate.now()))
            throw new ParametroInvalidoException("Data de nascimento não pode ser maior que a data atual");
    }


    public Pagina<PessoaResumida> listar(PaginaPosicao paginaPosicao) {
        return pessoaGateway.listar(paginaPosicao);
    }

    public Pessoa findById(String id) {
        return pessoaGateway.findById(id).orElseThrow(() -> new ElementoNaoEncontradoException("Pessoa não encontrada"));
    }


    public void deletar(String id){
        Validator.stringBlank(id, "ID");
        if(pessoaGateway.findById(id).isEmpty())
            throw new ElementoNaoEncontradoException("Pessoa não encontrada");
        pessoaGateway.deleteById(id);
    }



}
