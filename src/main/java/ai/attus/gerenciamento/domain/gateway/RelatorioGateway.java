package ai.attus.gerenciamento.domain.gateway;

import java.util.HashMap;
import java.util.LinkedList;

public interface RelatorioGateway {
    LinkedList<HashMap<String,Object>> genReport();
}
