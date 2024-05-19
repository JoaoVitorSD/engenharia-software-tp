package tp.gerenciamento.infraestructure.adapter;

import tp.gerenciamento.domain.entity.Endereco;
import tp.gerenciamento.domain.entity.Pessoa;
import tp.gerenciamento.domain.gateway.EnderecoGateway;
import tp.gerenciamento.infraestructure.adapter.mapper.EnderecoRepositoryMapper;
import tp.gerenciamento.infraestructure.adapter.mapper.PessoaRepositoryMapper;
import tp.gerenciamento.infraestructure.entity.EnderecoEntity;
import tp.gerenciamento.infraestructure.repository.EnderecoRepository;

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
