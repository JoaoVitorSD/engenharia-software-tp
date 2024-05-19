package tp.gerenciamento;


import tp.gerenciamento.infraestructure.controller.v1.request.EnderecoRequest;
import tp.gerenciamento.infraestructure.controller.v1.request.PessoaRequest;
import tp.gerenciamento.infraestructure.controller.v1.response.EnderecoResponse;
import tp.gerenciamento.infraestructure.controller.v1.response.PessoaResponse;
import tp.gerenciamento.util.ApiCall;
import tp.gerenciamento.util.Status;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.UUID;

import static tp.gerenciamento.util.ApiCall.defaultResponseClass;
import static tp.gerenciamento.util.RequestFabric.enderecoRequest;
import static tp.gerenciamento.util.RequestFabric.pessoaRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EnderecoTests {



    @Autowired
    private ApiCall apiCall;

    private final String API_URL = "/v1/endereco";

    @Test
    public void CreatingEndereco_WhenValid_Then201() throws Exception {
        PessoaRequest pessoaRequest = pessoaRequest();


        PessoaResponse response = apiCall.post("/v1/pessoa", pessoaRequest, PessoaResponse.class, Status.CREATED);

        EnderecoRequest request = enderecoRequest(response.getId());

        EnderecoResponse enderecoResponse = apiCall.post(API_URL, request,EnderecoResponse.class,Status.CREATED);
        assertResponse(request, enderecoResponse);
    }

    @Test
    public void CreatingEndereco_WhenAlreadyHasPrincipal_OldPrincipalIsReplaced() throws Exception {
        PessoaRequest pessoaRequest = pessoaRequest();
        PessoaResponse response = apiCall.post("/v1/pessoa", pessoaRequest,PessoaResponse.class,Status.CREATED);

        EnderecoRequest request = enderecoRequest(response.getId());
        request.setEnderecoPrincipal(true);

        EnderecoResponse enderecoResponse = apiCall.post(API_URL, request,EnderecoResponse.class,Status.CREATED);
        assertResponse(request, enderecoResponse);

        request = enderecoRequest(response.getId());
        request.setEnderecoPrincipal(true);

        enderecoResponse = apiCall.post(API_URL, request,EnderecoResponse.class,Status.CREATED);
        assertResponse(request, enderecoResponse);

    }
    @Test
    public void CreatingEndereco_WhenFirstAddress_AddressIsSeteAsMainAddress() throws Exception {
        PessoaRequest pessoaRequest = pessoaRequest();
        pessoaRequest.setEnderecos(new ArrayList<>());
        PessoaResponse response = apiCall.post("/v1/pessoa", pessoaRequest,PessoaResponse.class,Status.CREATED);

        EnderecoRequest request = enderecoRequest(response.getId());
        request.setEnderecoPrincipal(false);
        EnderecoResponse enderecoResponse = apiCall.post(API_URL, request,EnderecoResponse.class,Status.CREATED);
        request.setEnderecoPrincipal(true);
        assertResponse(request, enderecoResponse);
    }

    @Test
    public void CreatingEndereco_WhenEnderecoNull_Then400() throws Exception {

        apiCall.post(API_URL, null,defaultResponseClass, Status.BAD_REQUEST);
    }

    @Test
    public void CreatingEndereco_WhenPessoaNull_Then400() throws Exception {
        EnderecoRequest request = enderecoRequest(null);
        apiCall.post(API_URL, request, defaultResponseClass,Status.BAD_REQUEST);
    }
    @Test
    public void CreatingEndereco_WhenPessoaNotFound_Then404() throws Exception {
        EnderecoRequest request = enderecoRequest(UUID.randomUUID().toString());
        apiCall.post(API_URL, request, defaultResponseClass,Status.NOT_FOUND);
    }

    @Test
    public void GettingPessoaWithAddress_WhenValid_Then200()throws Exception{
        PessoaRequest pessoaRequest = pessoaRequest();
        PessoaResponse response = apiCall.post("/v1/pessoa", pessoaRequest,PessoaResponse.class,Status.CREATED);

        EnderecoRequest request = enderecoRequest(response.getId());
        apiCall.post(API_URL, request,EnderecoResponse.class,Status.CREATED);

        PessoaResponse enderecoResponse = apiCall.get("/v1/pessoa/" + response.getId(), new TypeReference<>() {}, Status.OK);
        assertEquals(1,enderecoResponse.getEnderecos().size());

    }

    @Test
    public void UpdatingEndereco_WhenValid_Then200() throws Exception {
        PessoaRequest pessoaRequest = pessoaRequest();
        PessoaResponse pessoaResponse = apiCall.post("/v1/pessoa", pessoaRequest,PessoaResponse.class,Status.CREATED);

        EnderecoRequest request = enderecoRequest(pessoaResponse.getId());
        EnderecoResponse enderecoResponse = apiCall.post(API_URL, request,EnderecoResponse.class,Status.CREATED);
        assertResponse(request, enderecoResponse);

        request.setId(enderecoResponse.getId());
        request.setCep("12345678");
        request.setCidade("Cidade");
        request.setEstado("Estado");
        request.setLogradouro("Logradouro");
        request.setNumero("Numero");

        enderecoResponse = apiCall.put(API_URL, request,EnderecoResponse.class,Status.OK);
        assertResponse(request, enderecoResponse);

    }

    @Test
    public void DeletingEndereco_WhenValid_Then204() throws Exception {
        PessoaRequest pessoaRequest = pessoaRequest();
        PessoaResponse pessoaResponse = apiCall.post("/v1/pessoa", pessoaRequest,PessoaResponse.class,Status.CREATED);

        EnderecoRequest request = enderecoRequest(pessoaResponse.getId());
        EnderecoResponse enderecoResponse = apiCall.post(API_URL, request,EnderecoResponse.class,Status.CREATED);
        assertResponse(request, enderecoResponse);

        apiCall.delete(API_URL + "/" + enderecoResponse.getId(),Status.NO_CONTENT);
    }
    @Test
    public void DeletingEndereco_WhenNotExists_Then404() throws Exception {
        apiCall.delete(API_URL + "/" + UUID.randomUUID(),Status.NOT_FOUND);
    }
    private void assertResponse(EnderecoRequest request, EnderecoResponse response) {
        if (request.getId() != null)
            assertEquals(request.getId(), response.getId());
        assertEquals(request.getCep(), response.getCep());
        assertEquals(request.getCidade(), response.getCidade());
        assertEquals(request.getEstado(), response.getEstado());
        assertEquals(request.getNumero(), response.getNumero());
        assertEquals(request.getLogradouro(), response.getLogradouro());
        assertEquals(request.isEnderecoPrincipal(), response.getEnderecoPrincipal());
    }

}
