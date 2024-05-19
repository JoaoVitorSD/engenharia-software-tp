package tp.gerenciamento.domain.entity;

import java.time.LocalDate;
import java.util.List;

public class Pessoa {

    private String id;
    private String nome;
    private LocalDate dataNascimento;
    private List<Endereco> enderecos;



    public Endereco getEnderecoPrincipal() {
        if(enderecos==null){
            return null;
        }
        return enderecos.stream()
                .filter(Endereco::isEnderecoPrincipal)
                .findFirst().orElse(null);
    }
    public void atualizar(Pessoa pessoa) {
        this.nome = pessoa.getNome();
        this.dataNascimento = pessoa.getDataNascimento();
        this.enderecos = pessoa.getEnderecos();
    }
    public void padronizarEnderecoPrincipal() {
        if(enderecos == null){
            return;
        }
        List<Endereco> enderecos = this.enderecos.stream().filter(Endereco::isEnderecoPrincipal).toList();
        if(enderecos.size() > 1){
            enderecos.forEach(endereco -> endereco.setEnderecoPrincipal(false));
            this.enderecos.get(0).setEnderecoPrincipal(true);
        }
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }


}
