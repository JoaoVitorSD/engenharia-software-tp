package ai.attus.gerenciamento.infraestructure.repository;

import ai.attus.gerenciamento.infraestructure.entity.EnderecoEntity;
import ai.attus.gerenciamento.infraestructure.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;


public interface EnderecoRepository extends JpaRepository<EnderecoEntity, UUID> {


    List<EnderecoEntity> findByPessoa(PessoaEntity pessoaEntity);

    void deleteByPessoaId(UUID id);
}
