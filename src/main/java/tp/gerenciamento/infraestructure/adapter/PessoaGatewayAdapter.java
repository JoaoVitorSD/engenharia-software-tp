package tp.gerenciamento.infraestructure.adapter;

import tp.gerenciamento.domain.entity.Pessoa;
import tp.gerenciamento.domain.entity.PessoaResumida;
import tp.gerenciamento.domain.gateway.PessoaGateway;
import tp.gerenciamento.infraestructure.adapter.mapper.EnderecoRepositoryMapper;
import tp.gerenciamento.infraestructure.adapter.mapper.PessoaRepositoryMapper;
import tp.gerenciamento.domain.input.PaginaPosicao;
import tp.gerenciamento.domain.output.Pagina;
import tp.gerenciamento.infraestructure.entity.EnderecoEntity;
import tp.gerenciamento.infraestructure.entity.PessoaEntity;
import tp.gerenciamento.infraestructure.entity.PessoaResumidaEntity;
import tp.gerenciamento.infraestructure.repository.EnderecoRepository;
import tp.gerenciamento.infraestructure.repository.PessoaRepository;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.*;

import static tp.gerenciamento.infraestructure.adapter.mapper.PageJpaMapper.pageJpaToDomain;

public class PessoaGatewayAdapter implements PessoaGateway {
    private final PessoaRepository repository;

    private final EnderecoRepository enderecoRepository;

    public PessoaGatewayAdapter(PessoaRepository repository, EnderecoRepository enderecoRepository) {
        this.repository = repository;
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public Pessoa salvar(Pessoa pessoa) {
        PessoaEntity pessoaEntity = PessoaRepositoryMapper.pessoaDomainToEntity(pessoa);
        PessoaEntity createdEntity = repository.save(pessoaEntity);
        if (pessoa.getEnderecos() != null) {
            List<EnderecoEntity> enderecos = enderecoRepository.saveAll(pessoa.getEnderecos()
                    .stream()
                    .map((e) -> EnderecoRepositoryMapper.enderecoDomainToEntity(e, createdEntity))
                    .toList());
            return PessoaRepositoryMapper.pessoaEntityToDomain(pessoaEntity, enderecos);
        }
        return PessoaRepositoryMapper.pessoaEntityToDomain(pessoaEntity, null);
    }

    @Override
    public Pagina<PessoaResumida> listar(PaginaPosicao posicao) {
        String filter = posicao.getFilter() != null ? posicao.getFilter().toLowerCase() : null;
        LoggerFactory.getLogger("listing").info("Listing with filter: {}", filter);
        Page<PessoaResumidaEntity> page = repository.findByConcatenatedInfoContaining(filter, PageRequest.of(posicao.getPage(), posicao.getSize()));
        return pageJpaToDomain(page, PessoaRepositoryMapper::pessoaResumidaEntityToDomain);
    }

    @Override
    public Optional<Pessoa> findById(String id) {
        Optional<PessoaEntity> entityOptional = repository.findById(UUID.fromString(id));
        if (entityOptional.isEmpty()) {
            return Optional.empty();
        }
        PessoaEntity entity = entityOptional.get();
        List<EnderecoEntity> enderecos = enderecoRepository.findByPessoa(entity);
        return Optional.of(PessoaRepositoryMapper.pessoaEntityToDomain(entity, enderecos));
    }

    @Override
    public void deleteById(String id) {
        enderecoRepository.deleteByPessoaId(UUID.fromString(id));
        repository.deleteById(UUID.fromString(id));
    }


}
