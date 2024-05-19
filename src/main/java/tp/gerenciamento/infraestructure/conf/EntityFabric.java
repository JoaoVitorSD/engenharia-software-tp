package tp.gerenciamento.infraestructure.conf;

import tp.gerenciamento.domain.entity.Endereco;
import tp.gerenciamento.domain.entity.Pessoa;

import java.time.LocalDate;
import java.util.ArrayList;

public class EntityFabric {
    public static final String[] firstName = {"João", "Maria", "José", "Ana", "Pedro", "Paulo", "Lucas", "Luciana", "Mariana", "Mário", "Luiz", "Luiza", "Larissa", "Larisa"};

    public static final String[] secondName = {"Silva", "Santos", "Oliveira", "Souza", "Costa", "Pereira", "Rodrigues", "Almeida", "Nascimento", "Lima", "Araújo", "Ferreira", "Gomes", "Carvalho"};
    public static final String[] cidade = {"São Paulo", "Rio de Janeiro", "Belo Horizonte", "Curitiba", "Porto Alegre", "Florianópolis", "Brasília", "Salvador", "Recife", "Fortaleza", "Manaus", "Belém", "Goiânia", "Campo Grande"};
    public static final String[] estado = {"São Paulo", "Rio de Janeiro", "Minas Gerais", "Paraná", "Rio Grande do Sul", "Santa Catarina", "Distrito Federal", "Bahia", "Pernambuco", "Ceará", "Amazonas", "Pará", "Goiás", "Mato Grosso do Sul"};

    public static Pessoa criarPessoa(int i, int j) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(genRandomName(i, j));
        pessoa.setDataNascimento(genRandomDataNascimento());
        ArrayList<Endereco> enderecos = new ArrayList<>();
        int numberOfAddress = (int) (Math.random() * 3) + 1;
        int mainAddress = (int) (Math.random() * numberOfAddress);
        for (int k = 0; k < numberOfAddress; k++) {
            Endereco endereco =randomEndereco();
            endereco.setEnderecoPrincipal(k == mainAddress);
            enderecos.add(endereco);
        }
        pessoa.setEnderecos(enderecos);
        return pessoa;
    }
    public static Pessoa randomPessoa() {
        int i = (int) (Math.random() * firstName.length);
        int j = (int) (Math.random() * secondName.length);
        return criarPessoa(i, j);
    }

    public static Endereco randomEndereco() {
        int i = (int) (Math.random() * firstName.length);
        int j = (int) (Math.random() * secondName.length);

        return criarEndereco(i, j);
    }
    public static Endereco criarEndereco(int i, int j) {
        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua " + firstName[i] + " " + secondName[j]);
        endereco.setCep(String.format("%05d-%03d", i * 10, j));
        endereco.setNumero(Integer.toString(i + j));
        int localePos = (int) (Math.random() * cidade.length);
        endereco.setCidade(cidade[localePos]);
        endereco.setEstado(estado[localePos]);
        return endereco;
    }

    private static String genRandomName(int i, int j) {
        return firstName[i] + " " + secondName[j];
    }

    private static LocalDate genRandomDataNascimento() {
        int year =LocalDate.now().getYear() -(10);
        int month = (int) (Math.random() * 12) + 1;
        int day = (int) (Math.random() * 28) + 1;
        return LocalDate.of(year, month, day);
    }
}
