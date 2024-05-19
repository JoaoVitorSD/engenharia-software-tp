package tp.gerenciamento.infraestructure.conf.beans;

import tp.gerenciamento.infraestructure.adapter.RelatorioGatewayAdapter;
import tp.gerenciamento.domain.interactor.RelatorioInteractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class RelatorioConf {

    @Bean
    public RelatorioInteractor relatorioInteractor(RelatorioGatewayAdapter relatorioGatewayAdapter){
        return new RelatorioInteractor(relatorioGatewayAdapter);
    }

    @Bean
    public RelatorioGatewayAdapter relatorioGatewayAdapter(JdbcTemplate jdbcTemplate){
        return new RelatorioGatewayAdapter(jdbcTemplate);
    }
}
