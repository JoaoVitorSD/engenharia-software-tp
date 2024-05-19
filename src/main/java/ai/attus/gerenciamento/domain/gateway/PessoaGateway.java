package ai.attus.gerenciamento.domain.gateway;

import ai.attus.gerenciamento.domain.entity.Pessoa;
import ai.attus.gerenciamento.domain.entity.PessoaResumida;
import ai.attus.gerenciamento.domain.input.PaginaPosicao;
import ai.attus.gerenciamento.domain.output.Pagina;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;

public interface PessoaGateway {

    Pessoa salvar(Pessoa pessoa);


    Pagina<PessoaResumida> listar(PaginaPosicao paginaPosicao);

    Optional<Pessoa> findById(String id);

    void deleteById(String id);

}
