package tp.gerenciamento.infraestructure.conf.beans;

import tp.gerenciamento.infraestructure.adapter.PessoaGatewayAdapter;
import tp.gerenciamento.domain.interactor.PessoaInteractor;
import tp.gerenciamento.infraestructure.repository.EnderecoRepository;
import tp.gerenciamento.infraestructure.repository.PessoaRepository;
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
