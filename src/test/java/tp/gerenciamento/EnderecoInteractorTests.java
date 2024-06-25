package tp.gerenciamento;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import tp.gerenciamento.domain.entity.Endereco;
import tp.gerenciamento.domain.entity.Pessoa;
import tp.gerenciamento.domain.exception.ElementoNaoEncontradoException;
import tp.gerenciamento.domain.exception.ParametroInvalidoException;
import tp.gerenciamento.domain.interactor.EnderecoInteractor;
import tp.gerenciamento.infraestructure.adapter.PessoaGatewayAdapter;
import tp.gerenciamento.infraestructure.conf.EntityFabric;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EnderecoInteractorTests {


    @Autowired
    private EnderecoInteractor enderecoInteractor;

    private Pessoa pessoa;
    private Endereco endereco;
    @MockBean
    private PessoaGatewayAdapter pessoaGatewayAdapter;
    @BeforeEach
    public void setup(){
        doNothing().when(pessoaGatewayAdapter).deleteById(anyString());
        pessoa = EntityFabric.randomPessoa();
        pessoa.setId(UUID.randomUUID().toString());
        when(pessoaGatewayAdapter.salvar(pessoa)).thenReturn(pessoa);

        when(pessoaGatewayAdapter.findById(pessoa.getId())).thenReturn(Optional.of(pessoa));
        endereco = EntityFabric.randomEndereco();
    }

    @AfterEach
    public void tearDown(){
        pessoaGatewayAdapter.deleteById(pessoa.getId());
    }
    @Test
    @Transactional
    @Rollback
    public void testCriarEnderecoReturnsEndereco(){
        assertNotNull(enderecoInteractor.criarEndereco(pessoa.getId(),endereco));
    }

    @Test
    @Transactional
    @Rollback
    public void testCriarEnderecoWhenPessoaDoesNotExistsThrowsEntityNotFoundException(){
        assertThrows(ElementoNaoEncontradoException.class, () -> enderecoInteractor.criarEndereco(UUID.randomUUID().toString(),endereco));
    }

    @Test
    @Transactional
    @Rollback
    public void testCriarEnderecoWhenPessoaIdIsNullThrowsParametroInvalidoException(){
        assertThrows(ParametroInvalidoException.class, () -> enderecoInteractor.criarEndereco(null,endereco));
    }

    @Test
    @Transactional
    @Rollback
    public void testCriarEnderecoWhenLogradouroIsNullThrowsParametroInvalidoException(){
        endereco.setLogradouro(null);
        assertThrows(ParametroInvalidoException.class, () -> enderecoInteractor.criarEndereco(pessoa.getId(),endereco));
    }

    @Test
    @Transactional
    @Rollback
    public void testCriarEnderecoWhenLogradouroIsBlankThrowsParametroInvalidoException(){
        endereco.setLogradouro(" ");
        assertThrows(ParametroInvalidoException.class, () -> enderecoInteractor.criarEndereco(pessoa.getId(),endereco));
    }

    @Test
    @Transactional
    @Rollback
    public void testCriarEnderecoWhenNumeroIsNullThrowsParametroInvalidoException(){
        endereco.setNumero(null);
        assertThrows(ParametroInvalidoException.class, () -> enderecoInteractor.criarEndereco(pessoa.getId(),endereco));
    }

    @Test
    @Transactional
    @Rollback
    public void testCriarEnderecoWhenNumeroIsBlankThrowsParametroInvalidoException(){
        endereco.setNumero(" ");
        assertThrows(ParametroInvalidoException.class, () -> enderecoInteractor.criarEndereco(pessoa.getId(),endereco));
    }

    @Test
    @Transactional
    @Rollback
    public void testCriarEnderecoWhenCidadeIsNullThrowsParametroInvalidoException(){
        endereco.setCidade(null);
        assertThrows(ParametroInvalidoException.class, () -> enderecoInteractor.criarEndereco(pessoa.getId(),endereco));
    }

    @Test
    @Transactional
    @Rollback
    public void testCriarEnderecoWhenCidadeIsBlankThrowsParametroInvalidoException(){
        endereco.setCidade(" ");
        assertThrows(ParametroInvalidoException.class, () -> enderecoInteractor.criarEndereco(pessoa.getId(),endereco));
    }

    @Test
    @Transactional
    @Rollback
    public void testCriarEnderecoWhenEstadoIsNullThrowsParametroInvalidoException(){
        endereco.setEstado(null);
        assertThrows(ParametroInvalidoException.class, () -> enderecoInteractor.criarEndereco(pessoa.getId(),endereco));
    }
}
