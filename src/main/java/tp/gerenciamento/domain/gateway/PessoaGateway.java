package tp.gerenciamento.domain.gateway;

import tp.gerenciamento.domain.entity.Pessoa;
import tp.gerenciamento.domain.entity.PessoaResumida;
import tp.gerenciamento.domain.input.PaginaPosicao;
import tp.gerenciamento.domain.output.Pagina;

import java.util.Optional;

public interface PessoaGateway {

    Pessoa salvar(Pessoa pessoa);


    Pagina<PessoaResumida> listar(PaginaPosicao paginaPosicao);

    Optional<Pessoa> findById(String id);

    void deleteById(String id);

}
