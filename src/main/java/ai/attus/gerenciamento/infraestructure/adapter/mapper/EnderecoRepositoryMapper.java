package ai.attus.gerenciamento.infraestructure.adapter.mapper;

import ai.attus.gerenciamento.domain.entity.Endereco;
import ai.attus.gerenciamento.infraestructure.entity.EnderecoEntity;
import ai.attus.gerenciamento.infraestructure.entity.PessoaEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static ai.attus.gerenciamento.infraestructure.adapter.mapper.UtilMapper.fromString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public  class EnderecoRepositoryMapper {

    public static Endereco enderecoEntityToDomain(EnderecoEntity enderecoEntity) {
        Endereco endereco = new Endereco();
        if(enderecoEntity.getId() != null)
            endereco.setId(enderecoEntity.getId().toString());
        endereco.setCep(enderecoEntity.getCep());
        endereco.setCidade(enderecoEntity.getCidade());
        endereco.setEstado(enderecoEntity.getEstado());
        endereco.setLogradouro(enderecoEntity.getLogradouro());
        endereco.setNumero(enderecoEntity.getNumero());
        endereco.setEnderecoPrincipal(enderecoEntity.isPrincipal());
        return endereco;
    }
    public static EnderecoEntity enderecoDomainToEntity(Endereco endereco, PessoaEntity pessoaEntity) {
        EnderecoEntity entity = new EnderecoEntity();
        entity.setId(fromString(endereco.getId()));
        entity.setPessoa(pessoaEntity);
        entity.setCep(endereco.getCep());
        entity.setCidade(endereco.getCidade());
        entity.setEstado(endereco.getEstado());
        entity.setLogradouro(endereco.getLogradouro());
        entity.setNumero(endereco.getNumero());
        entity.setPrincipal(endereco.isEnderecoPrincipal());
        return entity;
    }
}
