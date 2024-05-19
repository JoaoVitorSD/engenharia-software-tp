package tp.gerenciamento.infraestructure.adapter.mapper;

import tp.gerenciamento.domain.entity.Endereco;
import tp.gerenciamento.domain.entity.Pessoa;
import tp.gerenciamento.domain.entity.PessoaResumida;
import tp.gerenciamento.infraestructure.entity.EnderecoEntity;
import tp.gerenciamento.infraestructure.entity.PessoaEntity;
import tp.gerenciamento.infraestructure.entity.PessoaResumidaEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class PessoaRepositoryMapper {

    public static Pessoa pessoaEntityToDomain(PessoaEntity entity, List<EnderecoEntity> enderecosEntity) {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(entity.getId().toString());
        pessoa.setNome(entity.getNome());
        pessoa.setDataNascimento(entity.getDataNascimento());
        if (enderecosEntity != null) {
            ArrayList<Endereco> enderecos = new ArrayList<>();
            enderecosEntity.forEach(e -> enderecos.add(EnderecoRepositoryMapper.enderecoEntityToDomain(e)));
            pessoa.setEnderecos(enderecos);
        }
        return pessoa;
    }

    public static PessoaResumida pessoaResumidaEntityToDomain(PessoaResumidaEntity entity) {
        PessoaResumida pessoa = new PessoaResumida();
        pessoa.setId(entity.getId().toString());
        pessoa.setNome(entity.getNome());
        pessoa.setDataNascimento(entity.getDataNascimento());
        Endereco endereco = new Endereco();
        endereco.setLogradouro(entity.getLogradouro());
        endereco.setCidade(entity.getCidade());
        endereco.setEstado(entity.getEstado());
        endereco.setNumero(entity.getNumero());
        endereco.setCep(entity.getCep());
        endereco.setEnderecoPrincipal(true);
        pessoa.setEnderecoPrincipal(endereco);
        return pessoa;
    }

    public static PessoaEntity pessoaDomainToEntity(Pessoa pessoa) {
        PessoaEntity entity = new PessoaEntity();
        entity.setId(UtilMapper.fromString(pessoa.getId()));
        entity.setNome(pessoa.getNome());
        entity.setConcatenatedInfo(genConcatenatedInfo(pessoa).toLowerCase());
        entity.setDataNascimento(pessoa.getDataNascimento());
        return entity;
    }


    private static String genConcatenatedInfo(Pessoa pessoa) {
        StringBuilder builder = new StringBuilder(pessoa.getNome());

        if (pessoa.getEnderecos() != null) {
            pessoa.getEnderecos().forEach(e -> builder.append(getEnderecoInfo(e)));
        }
        return builder.toString();
    }

    private static String getEnderecoInfo(Endereco endereco) {
        StringBuilder builder = new StringBuilder();
        builder.append(endereco.getCidade());
        builder.append(endereco.getEstado());
        builder.append(endereco.getLogradouro());
        builder.append(endereco.getNumero());
        builder.append(endereco.getCep());
        return builder.toString();
    }


}
