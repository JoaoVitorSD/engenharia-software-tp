package ai.attus.gerenciamento.infraestructure.controller.v1;

import ai.attus.gerenciamento.domain.interactor.RelatorioInteractor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;

@RestController
@RequestMapping("v1/report")
@CrossOrigin
public class RelatorioController {
    private final RelatorioInteractor relatorioInteractor;


    public RelatorioController(RelatorioInteractor relatorioInteractor) {
        this.relatorioInteractor = relatorioInteractor;
    }


    @GetMapping
    public  LinkedList<HashMap<String, Object>> getReport(){
        return relatorioInteractor.genReport();
    }
}
