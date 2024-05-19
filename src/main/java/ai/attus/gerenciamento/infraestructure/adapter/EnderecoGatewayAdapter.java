package ai.attus.gerenciamento.infraestructure.adapter;

import ai.attus.gerenciamento.domain.entity.Endereco;
import ai.attus.gerenciamento.domain.entity.Pessoa;
import ai.attus.gerenciamento.domain.gateway.EnderecoGateway;
import ai.attus.gerenciamento.infraestructure.adapter.mapper.EnderecoRepositoryMapper;
import ai.attus.gerenciamento.infraestructure.adapter.mapper.PessoaRepositoryMapper;
import ai.attus.gerenciamento.infraestructure.entity.EnderecoEntity;
import ai.attus.gerenciamento.infraestructure.repository.EnderecoRepository;

import java.util.Optional;
import java.util.UUID;

public class EnderecoGatewayAdapter implements EnderecoGateway {
    private final EnderecoRepository repository;

    public EnderecoGatewayAdapter(EnderecoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Endereco salvar(Pessoa pessoa, Endereco endereco) {
        EnderecoEntity enderecoEntity = EnderecoRepositoryMapper.enderecoDomainToEntity(endereco, PessoaRepositoryMapper.pessoaDomainToEntity(pessoa));
        return EnderecoRepositoryMapper.enderecoEntityToDomain(repository.save(enderecoEntity));
    }

    @Override
    public Optional<Endereco> findById(String id) {
        return repository.findById(UUID.fromString(id)).map(EnderecoRepositoryMapper::enderecoEntityToDomain);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(UUID.fromString(id));
    }
}
