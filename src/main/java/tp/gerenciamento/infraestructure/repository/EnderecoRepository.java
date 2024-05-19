package tp.gerenciamento.infraestructure.repository;

import tp.gerenciamento.infraestructure.entity.EnderecoEntity;
import tp.gerenciamento.infraestructure.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.List;


public interface EnderecoRepository extends JpaRepository<EnderecoEntity, UUID> {


    List<EnderecoEntity> findByPessoa(PessoaEntity pessoaEntity);

    void deleteByPessoaId(UUID id);
}
