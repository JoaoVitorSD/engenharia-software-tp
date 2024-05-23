package tp.gerenciamento.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "endereco")
@Data
public class EnderecoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "endereco_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private PessoaEntity pessoa;
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;
    private String estado;
    private boolean principal;

}
