package tp.gerenciamento;

import tp.gerenciamento.domain.entity.Endereco;
import tp.gerenciamento.domain.entity.Pessoa;
import tp.gerenciamento.domain.output.Pagina;
import tp.gerenciamento.infraestructure.adapter.mapper.PessoaRepositoryMapper;
import tp.gerenciamento.infraestructure.controller.v1.request.PessoaRequest;
import tp.gerenciamento.infraestructure.controller.v1.response.PessoaResponse;
import tp.gerenciamento.infraestructure.controller.v1.response.PessoaResumidaResponse;
import tp.gerenciamento.infraestructure.entity.PessoaEntity;
import tp.gerenciamento.infraestructure.repository.EnderecoRepository;
import tp.gerenciamento.infraestructure.repository.PessoaRepository;
import tp.gerenciamento.util.ApiCall;
import tp.gerenciamento.util.Status;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import static tp.gerenciamento.infraestructure.conf.EntityFabric.randomEndereco;
import static tp.gerenciamento.infraestructure.conf.EntityFabric.randomPessoa;
import static tp.gerenciamento.util.RequestFabric.pessoaRequest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PessoaTests {


    @Autowired
    private ApiCall apiCall;

    private static final String API_URL = "/v1/pessoa";

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
    public void CreatingPessoa_WhenValid_201() throws Exception {
        PessoaRequest pessoaRequest = pessoaRequest();
        PessoaResponse response = apiCall.post(API_URL, pessoaRequest, PessoaResponse.class, Status.CREATED);
        assertRequest(pessoaRequest, response);

    }

    @Test
    public void CreatingPessoa_WhenNameNull_400() throws Exception {
        PessoaRequest pessoaRequest = pessoaRequest();
        pessoaRequest.setNome(null);
        apiCall.post(API_URL, pessoaRequest, PessoaResponse.class, Status.BAD_REQUEST);
    }

    @Test
    public void CreatingPessoa_WhenNameBlank_400() throws Exception {
        PessoaRequest pessoaRequest = pessoaRequest();
        pessoaRequest.setNome("               ");
        apiCall.post(API_URL, pessoaRequest, PessoaResponse.class, Status.BAD_REQUEST);
    }

    @Test
    public void CreatingPessoa_WhenDataNascimento_400() throws Exception {
        PessoaRequest pessoaRequest = pessoaRequest();
        pessoaRequest.setDataNascimento(null);
        apiCall.post(API_URL, pessoaRequest, PessoaResponse.class, Status.BAD_REQUEST);
    }

    @Test
    public void CreatingPessoa_WhenDataNascimentoFuture_400() throws Exception {
        PessoaRequest pessoaRequest = pessoaRequest();
        pessoaRequest.setDataNascimento(LocalDate.now().plusDays(1L));
        apiCall.post(API_URL, pessoaRequest, PessoaResponse.class, Status.BAD_REQUEST);
    }

    @Test
    public void UpdatingPessoa_WhenValid_200() throws Exception {
        PessoaRequest pessoaRequest = pessoaRequest();
        PessoaResponse response = apiCall.post(API_URL, pessoaRequest, PessoaResponse.class, Status.CREATED);

        pessoaRequest.setId(response.getId());
        pessoaRequest.setNome("Nome Alterado");
        pessoaRequest.setDataNascimento(LocalDate.now().minusYears(20L));
        PessoaResponse responseUpdated = apiCall.put(API_URL, pessoaRequest, PessoaResponse.class, Status.OK);
        assertRequest(pessoaRequest, responseUpdated);
    }

    @Test
    public void GettingPessoaById_WhenValid_200() throws Exception {
        PessoaRequest pessoaRequest = pessoaRequest();
        PessoaResponse response = apiCall.post(API_URL, pessoaRequest, PessoaResponse.class, Status.CREATED);

        PessoaResponse responseGet = apiCall.get(API_URL + "/" + response.getId(), new TypeReference<>() {
        }, Status.OK);
        assertRequest(pessoaRequest, responseGet);
    }

    @Test
    public void GettingPessoasPaginated_WhenValid_200() throws Exception {
        PessoaRequest pessoaRequest = pessoaRequest();
        PessoaResponse response = apiCall.post(API_URL, pessoaRequest, PessoaResponse.class, Status.CREATED);

        Pagina<PessoaResumidaResponse> responseGet = apiCall.get(API_URL, new TypeReference<>() {
        }, Status.OK);
        assertEquals(1, responseGet.getContent().size());
        PessoaResumidaResponse pessoaResumidaResponse = responseGet.getContent().get(0);
        assertEquals(response.getId(), pessoaResumidaResponse.getId());
        assertEquals(response.getNome(), pessoaResumidaResponse.getNome());
        assertEquals(response.getDataNascimento(), pessoaResumidaResponse.getDataNascimento());
        assertNull(pessoaResumidaResponse.getEnderecoPrincipal().getLogradouro());
        assertNotNull(pessoaResumidaResponse.getEnderecoPrincipal());

    }

    @Test
    public void GettingPessoasPaginated_WhenFilterExcludesPossibleResults_200AndEmptyPage() throws Exception {
        PessoaRequest pessoaRequest = pessoaRequest();
        PessoaResponse response = apiCall.post(API_URL, pessoaRequest, PessoaResponse.class, Status.CREATED);

        Pagina<PessoaResponse> responseGet = apiCall.get(API_URL + "?filter=" + pessoaRequest.getNome() + "1", new TypeReference<>() {
        }, Status.OK);
        assertEquals(0, responseGet.getContent().size());
    }

    @Test
    public void DeletingPessoa_WhenValid_204() throws Exception {
        PessoaRequest pessoaRequest = pessoaRequest();
        PessoaResponse response = apiCall.post(API_URL, pessoaRequest, PessoaResponse.class, Status.CREATED);

        apiCall.delete(API_URL + "/" + response.getId(), Status.NO_CONTENT);
    }
    @Test
    public void DeletingPessoa_WhenNotExists_404() throws Exception {
        apiCall.delete(API_URL + "/"+ UUID.randomUUID(), Status.NOT_FOUND);
    }
    private void assertRequest(PessoaRequest request, PessoaResponse pessoaResponse) {
        assertEquals(request.getNome(), pessoaResponse.getNome());
        assertEquals(request.getDataNascimento(), pessoaResponse.getDataNascimento());
    }


    @Test
    public void TestMappingPessoaWithNoEnderecoEntityToDomain() {
        Pessoa pessoa = randomPessoa();
        pessoa.setEnderecos(new ArrayList<>());
        PessoaEntity entity = PessoaRepositoryMapper.pessoaDomainToEntity(pessoa);
        assertEquals(pessoa.getNome().toLowerCase(), entity.getConcatenatedInfo());
    }

    @Test
    public void TestMappingPessoaWithEnderecoEntityToDomain() {
        Pessoa pessoa = randomPessoa();
        Endereco endereco = randomEndereco();
        pessoa.setEnderecos(new ArrayList<>());
        pessoa.getEnderecos().add(endereco);
        PessoaEntity entity = PessoaRepositoryMapper.pessoaDomainToEntity(pessoa);
        String textToCompare = pessoa.getNome()+endereco.getCidade()+endereco.getEstado()+endereco.getLogradouro()+endereco.getNumero()+endereco.getCep();
        assertEquals(textToCompare.toLowerCase(), entity.getConcatenatedInfo());
    }

    @Test
    public void TestGetEnderecoPrincipalWhenNullEnderecos(){
        Pessoa pessoa = randomPessoa();
        pessoa.setEnderecos(null);
        assertNull(pessoa.getEnderecoPrincipal());
    }


}
