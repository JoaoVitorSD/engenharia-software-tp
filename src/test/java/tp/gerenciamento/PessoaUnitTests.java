package tp.gerenciamento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tp.gerenciamento.domain.entity.Pessoa;
import tp.gerenciamento.domain.entity.Endereco;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PessoaUnitTests {


    Pessoa pessoa;

    @BeforeEach
    public void setup() {
        pessoa = new Pessoa();
    }


    @Test
    public void updatingPessoaShouldUpdateAllFields() {
        pessoa.setNome("Original");
        pessoa.setDataNascimento(LocalDate.now().minusYears(20));
        pessoa.setEnderecos(new ArrayList<>());

        Pessoa updated = new Pessoa();
        updated.setNome("Updated");
        updated.setDataNascimento(LocalDate.now().minusYears(10));
        updated.setEnderecos(List.of(new Endereco()));

        pessoa.atualizar(updated);

        assertEquals(updated.getNome(), pessoa.getNome());
        assertEquals(updated.getDataNascimento(), pessoa.getDataNascimento());
        assertEquals(updated.getEnderecos(), pessoa.getEnderecos());
    }

    @Test
    public void padronizarEnderecoPrincipalShouldSetOnlyFirstEnderecoAsPrincipal() {
        Endereco endereco1 = new Endereco();
        endereco1.setEnderecoPrincipal(true);
        Endereco endereco2 = new Endereco();
        endereco2.setEnderecoPrincipal(true);
        pessoa.setEnderecos(List.of(endereco1, endereco2));

        pessoa.padronizarEnderecoPrincipal();

        assertTrue(endereco1.isEnderecoPrincipal());
        assertFalse(endereco2.isEnderecoPrincipal());
    }

    @Test
    public void getEnderecoPrincipalShouldReturnNullWhenEnderecosIsNull() {
        pessoa.setEnderecos(null);

        assertNull(pessoa.getEnderecoPrincipal());
    }

    @Test
    public void getEnderecoPrincipalShouldReturnNullWhenNoEnderecoIsPrincipal() {
        Endereco endereco = new Endereco();
        endereco.setEnderecoPrincipal(false);
        pessoa.setEnderecos(List.of(endereco));

        assertNull(pessoa.getEnderecoPrincipal());
    }

    @Test
    public void getEnderecoPrincipalShouldReturnPrincipalEndereco() {
        Endereco endereco1 = new Endereco();
        endereco1.setEnderecoPrincipal(false);
        Endereco endereco2 = new Endereco();
        endereco2.setEnderecoPrincipal(true);
        pessoa.setEnderecos(List.of(endereco1, endereco2));

        assertEquals(endereco2, pessoa.getEnderecoPrincipal());
    }


}
