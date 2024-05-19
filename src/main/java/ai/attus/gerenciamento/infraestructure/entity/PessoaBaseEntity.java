package ai.attus.gerenciamento.infraestructure.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@MappedSuperclass
public class PessoaBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "pessoa_id")
    private UUID id;
    private String nome;
    private LocalDate dataNascimento;
    private String concatenatedInfo;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getConcatenatedInfo() {
        return concatenatedInfo;
    }

    public void setConcatenatedInfo(String concatenatedInfo) {
        this.concatenatedInfo = concatenatedInfo;
    }
}
