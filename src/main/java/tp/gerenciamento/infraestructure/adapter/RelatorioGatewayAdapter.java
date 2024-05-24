package tp.gerenciamento.infraestructure.adapter;

import tp.gerenciamento.domain.gateway.RelatorioGateway;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RelatorioGatewayAdapter implements RelatorioGateway {
    private final JdbcTemplate jdbcTemplate;

    public RelatorioGatewayAdapter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public LinkedList<HashMap<String, Object>> genReport() {
        LinkedList<HashMap<String, Object>> report = new LinkedList<>();
        report.add(getEnderecosGroupByEstado());
        report.add(getEnderecosGroupedByEstadoAndCidade());

        return report;
    }

    private HashMap<String, Object> getEnderecosGroupByEstado() {
        HashMap<String, Object> enderecosEstados = new HashMap<>();
        List<Map<String, Object>> result = jdbcTemplate.queryForList("SELECT e.estado, count(e.estado) as ocorrencias " +
                "FROM endereco e group by e.estado order by ocorrencias desc");
        for (Map<String, Object> row : result) {
            enderecosEstados.put((String) row.get("estado"),  row.get("ocorrencias"));
        }
        return enderecosEstados;
    }

    private HashMap<String, Object> getEnderecosGroupedByEstadoAndCidade() {
        List<Map<String, Object>> result = jdbcTemplate.queryForList("SELECT e.estado, e.cidade, count(e.cidade) as ocorrencias " +
                "FROM endereco e group by e.estado, e.cidade order by ocorrencias desc");

        HashMap<String, Object> cidadesEstados = new HashMap<>();
        for (Map<String, Object> row : result) {
            if (cidadesEstados.containsKey(row.get("estado"))) {
                ((HashMap) cidadesEstados.get(row.get("estado")))
                        .put( row.get("cidade"),row.get("ocorrencias"));
            } else {
                HashMap<String, Object> cidade = new HashMap<>();
                cidade.put((String) row.get("cidade"),  row.get("ocorrencias"));
                cidadesEstados.put((String) row.get("estado"), cidade);
            }
        }
        return cidadesEstados;
    }
}
