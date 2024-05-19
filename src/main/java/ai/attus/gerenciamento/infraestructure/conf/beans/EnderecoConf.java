package ai.attus.gerenciamento.infraestructure.conf.beans;

import ai.attus.gerenciamento.domain.interactor.EnderecoInteractor;
import ai.attus.gerenciamento.infraestructure.adapter.EnderecoGatewayAdapter;
import ai.attus.gerenciamento.infraestructure.adapter.PessoaGatewayAdapter;
import ai.attus.gerenciamento.infraestructure.repository.EnderecoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnderecoConf {

    @Bean
    public EnderecoInteractor enderecoInteractor(EnderecoGatewayAdapter enderecoGatewayAdapter, PessoaGatewayAdapter pessoaGatewayAdapter){
        return new EnderecoInteractor(enderecoGatewayAdapter, pessoaGatewayAdapter);
    }

    @Bean
    public EnderecoGatewayAdapter enderecoGatewayAdapter(EnderecoRepository enderecoRepository){
        return new EnderecoGatewayAdapter(enderecoRepository);
    }

}
