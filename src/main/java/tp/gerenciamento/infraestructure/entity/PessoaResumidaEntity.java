package tp.gerenciamento.infraestructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "pessoa_resumida")
@Data
public class PessoaResumidaEntity extends PessoaBaseEntity{
    @Column(name = "endereco_id")
    private UUID endereco;
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;
    private String estado;

}
