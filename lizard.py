import matplotlib.pyplot as plt
import numpy as np

input_text = """
18       3.0     1.0       16.5         2     ./src/main/java/tp/gerenciamento/infraestructure/conf/beans/EnderecoConf.java
17       3.0     1.0       14.0         2     ./src/main/java/tp/gerenciamento/infraestructure/conf/beans/RelatorioConf.java
18       3.0     1.0       16.5         2     ./src/main/java/tp/gerenciamento/infraestructure/conf/beans/PessoaConf.java
3       2.0     1.0        7.0         1     ./src/main/java/tp/gerenciamento/infraestructure/conf/ApiError.java
39       5.5     1.5       31.0         4     ./src/main/java/tp/gerenciamento/infraestructure/conf/DatabaseSeeder.java
55       7.3     1.2       72.7         6     ./src/main/java/tp/gerenciamento/infraestructure/conf/EntityFabric.java
20       3.0     1.0       18.0         2     ./src/main/java/tp/gerenciamento/infraestructure/conf/ControllerAdvice.java
37      13.0     2.5      137.5         2     ./src/main/java/tp/gerenciamento/infraestructure/adapter/RelatorioGatewayAdapter.java
61       7.0     1.6       63.2         5     ./src/main/java/tp/gerenciamento/infraestructure/adapter/PessoaGatewayAdapter.java
29       3.2     1.0       24.8         4     ./src/main/java/tp/gerenciamento/infraestructure/adapter/EnderecoGatewayAdapter.java
21       0.0     0.0        0.0         0     ./src/main/java/tp/gerenciamento/infraestructure/entity/EnderecoEntity.java
14       0.0     0.0        0.0         0     ./src/main/java/tp/gerenciamento/infraestructure/entity/PessoaResumidaEntity.java
8       0.0     0.0        0.0         0     ./src/main/java/tp/gerenciamento/infraestructure/entity/PessoaEntity.java
38       3.0     1.0       10.5         8     ./src/main/java/tp/gerenciamento/infraestructure/entity/PessoaBaseEntity.java
12       0.0     0.0        0.0         0     ./src/main/java/tp/gerenciamento/infraestructure/repository/PessoaRepository.java
10       0.0     0.0        0.0         0     ./src/main/java/tp/gerenciamento/infraestructure/repository/EnderecoRepository.java
9       0.0     0.0        0.0         0     ./src/main/java/tp/gerenciamento/infraestructure/controller/v1/response/PessoaBaseResponse.java
13       3.0     1.0       12.0         2     ./src/main/java/tp/gerenciamento/infraestructure/controller/v1/response/PessoaResponse.java
52       3.0     1.0       10.5        14     ./src/main/java/tp/gerenciamento/infraestructure/controller/v1/response/EnderecoResponse.java
10       3.0     1.0       10.5         2     ./src/main/java/tp/gerenciamento/infraestructure/controller/v1/response/PessoaResumidaResponse.java
13       0.0     0.0        0.0         0     ./src/main/java/tp/gerenciamento/infraestructure/controller/v1/request/EnderecoRequest.java
11       0.0     0.0        0.0         0     ./src/main/java/tp/gerenciamento/infraestructure/controller/v1/request/PessoaRequest.java
55       3.5     1.0       24.2         6     ./src/main/java/tp/gerenciamento/infraestructure/controller/v1/PessoaController.java
39       3.5     1.0       26.2         4     ./src/main/java/tp/gerenciamento/infraestructure/controller/v1/EnderecoController.java
21       3.0     1.0       12.5         2     ./src/main/java/tp/gerenciamento/infraestructure/controller/v1/RelatorioController.java
12       0.0     0.0        0.0         0     ./src/main/java/tp/gerenciamento/domain/gateway/PessoaGateway.java
6       0.0     0.0        0.0         0     ./src/main/java/tp/gerenciamento/domain/gateway/RelatorioGateway.java
9       0.0     0.0        0.0         0     ./src/main/java/tp/gerenciamento/domain/gateway/EnderecoGateway.java
13       4.0     2.5       32.0         2     ./src/main/java/tp/gerenciamento/domain/util/Validator.java
13       3.0     1.0       12.5         2     ./src/main/java/tp/gerenciamento/domain/interactor/RelatorioInteractor.java
70       8.3     1.4       61.6         7     ./src/main/java/tp/gerenciamento/domain/interactor/EnderecoInteractor.java
49       5.1     1.3       37.1         7     ./src/main/java/tp/gerenciamento/domain/interactor/PessoaInteractor.java
58       3.5     1.0       16.7        13     ./src/main/java/tp/gerenciamento/domain/output/Pagina.java
17       3.0     1.0       17.0         3     ./src/main/java/tp/gerenciamento/domain/exception/ParametroInvalidoException.java
6       3.0     1.0       12.0         1     ./src/main/java/tp/gerenciamento/domain/exception/ElementoNaoEncontradoException.java
60       3.3     1.0       14.3        15     ./src/main/java/tp/gerenciamento/domain/entity/Endereco.java
32       3.0     1.0       10.5         8     ./src/main/java/tp/gerenciamento/domain/entity/PessoaResumida.java
56       4.3     1.3       22.1        11     ./src/main/java/tp/gerenciamento/domain/entity/Pessoa.java
8       0.0     0.0        0.0         0     ./src/main/java/tp/gerenciamento/domain/input/PaginaPosicao.java
9       3.0     1.0       20.0         1     ./src/main/java/tp/gerenciamento/GerenciamentoApplication.java
"""

lines = input_text.strip().split('\n')

# Define the columns
columns = ['NLOC', 'Avg.NLOC', 'AvgCCN', 'Avg.token', 'function_cnt', 'file']

# Extract data into a list of dictionaries
data = []
for line in lines:
    parts = line.split()
    NLOC = int(parts[0])
    AvgNLOC = float(parts[1])
    AvgCCN = float(parts[2])
    AvgToken = float(parts[3])
    function_cnt = int(parts[4])
    file = ' '.join(parts[5:])
    data.append({
        'NLOC': NLOC,
        'Avg.NLOC': AvgNLOC,
        'AvgCCN': AvgCCN,
        'Avg.token': AvgToken,
        'function_cnt': function_cnt,
        'file': file
    })

# Parse the input text
# Parse the input text
lines = input_text.strip().split('\n')

# Define the columns
columns = [{"name":'NLOC', "color": "#1f77b4"}
    , {"name":'Avg_NLOC', "color": "#ff7f0e"}
    , {"name":'AvgCCN', "color": "#2ca02c"}
    , {"name":'Avg_token', "color": "#d62728"}
    , {"name":'function_cnt', "color": "#9467bd"}]

# Extract data into a list of dictionaries
data = []
for line in lines:
    parts = line.split()
    NLOC = int(parts[0])
    Avg_NLOC = float(parts[1])
    AvgCCN = float(parts[2])
    Avg_token = float(parts[3])
    function_cnt = int(parts[4])
    file = ' '.join(parts[5:])
    data.append({
        'NLOC': NLOC,
        'Avg_NLOC': Avg_NLOC,
        'AvgCCN': AvgCCN,
        'Avg_token': Avg_token,
        'function_cnt': function_cnt,
        'file': file
    })

def plot_metric(column):
    metric_name = column['name']
    metric_color = column['color']
    metric_sorted = sorted(data, key=lambda x: x[metric_name],reverse=True)[:5]
    metric_values = [entry[metric_name] for entry in metric_sorted]
    files = [entry['file'].split('/')[-1] for entry in metric_sorted]
    plt.figure(figsize=(10, 4))
    plt.bar(range(len(files)), metric_values, color=metric_color)
    plt.title(metric_name)
    plt.ylabel('Value')
    plt.xticks(range(len(files)), files, rotation=45, ha='right')
    plt.tight_layout()
    plt.savefig(f"{metric_name}.png")
    plt.close()

for column in columns:
    plot_metric(column)