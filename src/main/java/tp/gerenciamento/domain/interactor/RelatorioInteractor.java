package tp.gerenciamento.domain.interactor;

import tp.gerenciamento.domain.gateway.RelatorioGateway;

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
