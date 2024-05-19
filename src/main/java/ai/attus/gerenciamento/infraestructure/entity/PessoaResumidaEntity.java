package ai.attus.gerenciamento.infraestructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "pessoa_resumida")
@Data
public class PessoaResumidaEntity extends PessoaBaseEntity{
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;
    private String estado;

}
