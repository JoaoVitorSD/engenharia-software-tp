package ai.attus.gerenciamento.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "pessoa")
@Data
public class PessoaEntity extends PessoaBaseEntity{

}
