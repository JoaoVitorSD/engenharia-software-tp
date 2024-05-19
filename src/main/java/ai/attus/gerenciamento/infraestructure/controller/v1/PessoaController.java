package ai.attus.gerenciamento.infraestructure.controller.v1;

import ai.attus.gerenciamento.domain.entity.Pessoa;
import ai.attus.gerenciamento.domain.entity.PessoaResumida;
import ai.attus.gerenciamento.domain.input.PaginaPosicao;
import ai.attus.gerenciamento.domain.interactor.PessoaInteractor;
import ai.attus.gerenciamento.domain.output.Pagina;
import ai.attus.gerenciamento.infraestructure.controller.v1.mapper.PessoaControllerMapper;
import ai.attus.gerenciamento.infraestructure.controller.v1.request.PessoaRequest;
import ai.attus.gerenciamento.infraestructure.controller.v1.response.PessoaResponse;
import ai.attus.gerenciamento.infraestructure.controller.v1.response.PessoaResumidaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/pessoa")
@CrossOrigin
@Tag(name = "Pessoa", description = "Criar, Editar e Filtrar Pessoas")
public class PessoaController {
    private final PessoaInteractor pessoaInteractor;

    public PessoaController(PessoaInteractor pessoaInteractor) {
        this.pessoaInteractor = pessoaInteractor;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar Pessoa", description = "Cria uma nova pessoa")
    public PessoaResponse criar(@RequestBody PessoaRequest pessoaRequest) {
        Pessoa pessoa = PessoaControllerMapper.requestToEntity(pessoaRequest);
        return PessoaControllerMapper.entityToResponse(pessoaInteractor.criar(pessoa));
    }
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Editar Pessoa", description = "Edita uma pessoa existente")
    public PessoaResponse update(@RequestBody PessoaRequest pessoaRequest) {
        Pessoa pessoa = PessoaControllerMapper.requestToEntity(pessoaRequest);
        return PessoaControllerMapper.entityToResponse(pessoaInteractor.editar(pessoa));
    }

    @GetMapping
    @Operation(summary = "Filtrar Pessoas", description = "Pagina as pessoas de acordo com o page, size e filter passados. Retorna as pessoas, sem a lista de endereços.")
    public Pagina<PessoaResumidaResponse> listar(PaginaPosicao paginaPosicao){
        Pagina<PessoaResumida> pessoas = pessoaInteractor.listar(paginaPosicao);
        return pessoas.map(PessoaControllerMapper::entityToResumidaResponse);
    }


    @GetMapping("{id}")
    @Operation(summary = "Buscar Pessoa por ID", description = "Busca uma pessoa pelo ID")
    public PessoaResponse buscarPorId(@PathVariable String id) {
        return PessoaControllerMapper.entityToResponse(pessoaInteractor.findById(id));
    }



    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar Pessoa", description = "Deleta uma pessoa existente")
    public void deletar(@PathVariable String id) {
        pessoaInteractor.deletar(id);
    }


}
