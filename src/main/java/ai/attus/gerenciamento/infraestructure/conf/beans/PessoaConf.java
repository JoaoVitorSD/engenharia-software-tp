package ai.attus.gerenciamento.infraestructure.conf.beans;

import ai.attus.gerenciamento.domain.interactor.PessoaInteractor;
import ai.attus.gerenciamento.infraestructure.adapter.PessoaGatewayAdapter;
import ai.attus.gerenciamento.infraestructure.repository.EnderecoRepository;
import ai.attus.gerenciamento.infraestructure.repository.PessoaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PessoaConf {

    @Bean
    public PessoaInteractor pessoaInteractor(PessoaGatewayAdapter pessoaGatewayAdapter){
        return new PessoaInteractor(pessoaGatewayAdapter);
    }

    @Bean
    public PessoaGatewayAdapter pessoaGatewayAdapter(PessoaRepository pessoaRepository, EnderecoRepository enderecoRepository){
        return new PessoaGatewayAdapter(pessoaRepository,enderecoRepository);
    }


}
