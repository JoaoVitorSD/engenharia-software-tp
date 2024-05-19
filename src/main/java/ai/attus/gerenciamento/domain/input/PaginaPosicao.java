package ai.attus.gerenciamento.domain.input;

import lombok.Data;

@Data
public class PaginaPosicao{
    private int page = 0;
    private int size = 100;
    String filter;

}