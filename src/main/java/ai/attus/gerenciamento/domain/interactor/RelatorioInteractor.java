package ai.attus.gerenciamento.domain.interactor;

import ai.attus.gerenciamento.domain.gateway.RelatorioGateway;

import java.util.HashMap;
import java.util.LinkedList;

public class RelatorioInteractor {

    private final RelatorioGateway relatorioGateway;

    public RelatorioInteractor(RelatorioGateway relatorioGateway) {
        this.relatorioGateway = relatorioGateway;
    }

    public LinkedList<HashMap<String,Object>> genReport(){
        return relatorioGateway.genReport();
    }


}
