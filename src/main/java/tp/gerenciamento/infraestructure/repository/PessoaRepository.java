package tp.gerenciamento.infraestructure.repository;

import tp.gerenciamento.infraestructure.entity.PessoaEntity;
import tp.gerenciamento.infraestructure.entity.PessoaResumidaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface PessoaRepository extends JpaRepository<PessoaEntity, UUID> {


    @Query("SELECT p FROM PessoaResumidaEntity p WHERE :filter is null or  p.concatenatedInfo LIKE concat('%', :filter, '%')")
    Page<PessoaResumidaEntity> findByConcatenatedInfoContaining(String filter, PageRequest pageable);
}
