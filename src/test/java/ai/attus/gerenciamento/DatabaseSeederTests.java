package ai.attus.gerenciamento;

import ai.attus.gerenciamento.domain.entity.Endereco;
import ai.attus.gerenciamento.domain.entity.Pessoa;
import ai.attus.gerenciamento.infraestructure.conf.DatabaseSeeder;
import ai.attus.gerenciamento.infraestructure.conf.EntityFabric;
import ai.attus.gerenciamento.infraestructure.repository.EnderecoRepository;
import ai.attus.gerenciamento.infraestructure.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class DatabaseSeederTests {

    @Autowired
    private  DatabaseSeeder databaseSeeder;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;
    @BeforeEach
    public void setUp() {
        enderecoRepository.deleteAll();
        pessoaRepository.deleteAll();
    }
    @Test
    public void seedDatabase_WhenSeedDataIsTrue_ThenSeedData() {
        databaseSeeder.setSeedData(true);
        databaseSeeder.setupEnvironment();
        assertEquals(EntityFabric.firstName.length, pessoaRepository.count());
    }

    @Test
    public void seedDatabase_WhenSeedDataIsFalse_ThenDontSeedData() {
        databaseSeeder.setSeedData(false);
        databaseSeeder.setupEnvironment();
        assertEquals(0, pessoaRepository.count());
    }

    @Test
    public void randomPessoa_WhenCalled_ThenReturnRandomPessoa() {
        Pessoa pessoa = EntityFabric.randomPessoa();

        String[] name = pessoa.getNome().split(" ");
        assertTrue(Arrays.asList(EntityFabric.firstName).contains(name[0]));
        assertTrue(Arrays.asList(EntityFabric.secondName).contains(name[1]));
        assertNotNull(pessoa.getDataNascimento());
    }

    @Test
    public void randomEndereco_WhenCalled_ThenReturnRandomEndereco() {
        Endereco endereco = EntityFabric.randomEndereco();
        String[] name = endereco.getLogradouro().split(" ");
        assertTrue(Arrays.asList(EntityFabric.firstName).contains(name[1]));
        assertTrue(Arrays.asList(EntityFabric.secondName).contains(name[2]));
        assertNotNull(endereco.getNumero());
        assertNotNull(endereco.getCidade());
        assertNotNull(endereco.getEstado());
        assertNotNull(endereco.getCep());
    }
}
