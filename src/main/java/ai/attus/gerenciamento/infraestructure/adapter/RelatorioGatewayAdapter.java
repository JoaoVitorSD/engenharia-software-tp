package ai.attus.gerenciamento.infraestructure.adapter;

import ai.attus.gerenciamento.domain.gateway.RelatorioGateway;
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
        HashMap<String, Object> enderecosEstados = new HashMap<>();
        List<Map<String,Object>> result =  jdbcTemplate.queryForList("SELECT e.estado, count(e.estado) as ocorrencias FROM endereco e group by e.estado order by ocorrencias desc");
        for(Map<String,Object> row : result){
            enderecosEstados.put((String)row.get("estado"), row.get("ocorrencias"));
        }
        result = jdbcTemplate.queryForList("SELECT e.estado, e.cidade, count(e.cidade) as ocorrencias FROM endereco e group by e.estado, e.cidade order by ocorrencias desc");

        HashMap<String, Object> cidadesEstados = new HashMap<>();
        for(Map<String,Object> row : result){
            if(cidadesEstados.containsKey(row.get("estado"))){
                ((HashMap<String, Object>)cidadesEstados.get(row.get("estado")))
                        .put((String)row.get("cidade"), row.get("ocorrencias"));
            }else{
                HashMap<String, Object> cidade = new HashMap<>();
                cidade.put((String)row.get("cidade"), row.get("ocorrencias"));
                cidadesEstados.put((String)row.get("estado"), cidade);
            }
        }
        LinkedList<HashMap<String, Object>> resultado = new LinkedList<>();
        resultado.add(enderecosEstados);
        resultado.add(cidadesEstados);

        return resultado;
    }
}
