package tp.gerenciamento.domain.gateway;

import tp.gerenciamento.domain.entity.Endereco;
import tp.gerenciamento.domain.entity.Pessoa;

import java.util.Optional;

public interface EnderecoGateway {
    Endereco salvar(Pessoa pessoa, Endereco endereco);

    Optional<Endereco> findById(String id);

    void deleteById(String id);
}
