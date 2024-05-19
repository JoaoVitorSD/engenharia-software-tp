package ai.attus.gerenciamento.domain.gateway;

import ai.attus.gerenciamento.domain.entity.Endereco;
import ai.attus.gerenciamento.domain.entity.Pessoa;

import java.util.Optional;

public interface EnderecoGateway {
    Endereco salvar(Pessoa pessoa, Endereco endereco);

    Optional<Endereco> findById(String id);

    void deleteById(String id);
}
