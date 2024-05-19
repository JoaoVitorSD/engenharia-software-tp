package tp.gerenciamento.infraestructure.controller.v1;

import tp.gerenciamento.domain.entity.Endereco;
import tp.gerenciamento.domain.interactor.EnderecoInteractor;
import tp.gerenciamento.infraestructure.controller.v1.mapper.EnderecoControllerMapper;
import tp.gerenciamento.infraestructure.controller.v1.request.EnderecoRequest;
import tp.gerenciamento.infraestructure.controller.v1.response.EnderecoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/endereco")
@CrossOrigin
@Tag(name = "Endereco", description = "Criar e Editar Endereços")
public class EnderecoController {
    private final EnderecoInteractor interactor;

    public EnderecoController(EnderecoInteractor interactor) {
        this.interactor = interactor;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar Endereco", description = "Cria um novo endereço")
    public EnderecoResponse criarEndereco(@RequestBody EnderecoRequest request) {
        Endereco endereco = EnderecoControllerMapper.requestToEndereco(request);
        return EnderecoControllerMapper.enderecoResponse(interactor.criarEndereco(request.getPessoa(), endereco));
    }
    @PutMapping
    @Operation(summary = "Editar Endereco", description = "Edita um endereço existente")
    public EnderecoResponse editarEndereco(@RequestBody EnderecoRequest request) {
        Endereco endereco = EnderecoControllerMapper.requestToEndereco(request);
        return EnderecoControllerMapper.enderecoResponse(interactor.editarEndereco(request.getPessoa(), endereco));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar Endereco", description = "Deleta um endereço existente")
    public void deletarEndereco(@PathVariable String id) {
        interactor.deletarEndereco(id);
    }
}
