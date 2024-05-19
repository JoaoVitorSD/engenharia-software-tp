package tp.gerenciamento.infraestructure.conf.beans;

import tp.gerenciamento.infraestructure.adapter.EnderecoGatewayAdapter;
import tp.gerenciamento.infraestructure.adapter.PessoaGatewayAdapter;
import tp.gerenciamento.domain.interactor.EnderecoInteractor;
import tp.gerenciamento.infraestructure.repository.EnderecoRepository;
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
