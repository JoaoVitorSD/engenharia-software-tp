package tp.gerenciamento.infraestructure.conf;

import tp.gerenciamento.domain.entity.Pessoa;
import tp.gerenciamento.domain.interactor.EnderecoInteractor;
import tp.gerenciamento.domain.interactor.PessoaInteractor;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import static tp.gerenciamento.infraestructure.conf.EntityFabric.*;

@Component
public class DatabaseSeeder {
    private final JdbcTemplate jdbcTemplate;
    private final PessoaInteractor pessoaInteractor;
    private final EnderecoInteractor enderecoInteractor;
    private boolean seedData;

    @PostConstruct
    public void setupEnvironment() {
        jdbcTemplate.execute("drop table if exists pessoa_resumida;");
        jdbcTemplate.execute("create view pessoa_resumida as select pessoa.pessoa_id, pessoa.nome,pessoa.concatenated_info,pessoa.data_nascimento, endereco.logradouro, endereco.cep, endereco.numero, endereco.cidade,endereco.estado  from pessoa left join endereco on pessoa.pessoa_id = endereco.pessoa_id and endereco.principal = true;");

        seedDatabase();
    }

    private void seedDatabase() {
        if (seedData) {
            for (int i = 0; i < firstName.length; i++) {
                    Pessoa pessoa =randomPessoa();
                    pessoaInteractor.criar(pessoa);
            }
        }
    }


    public DatabaseSeeder(JdbcTemplate jdbcTemplate, PessoaInteractor pessoaInteractor, EnderecoInteractor enderecoInteractor, @Value("${seed.data:false}") Boolean seedData) {
        this.jdbcTemplate = jdbcTemplate;
        this.pessoaInteractor = pessoaInteractor;
        this.enderecoInteractor = enderecoInteractor;
        this.seedData = seedData;
    }

    public void setSeedData(Boolean seedData) {
        this.seedData = seedData;
    }
}
