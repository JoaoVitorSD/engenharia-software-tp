package ai.attus.gerenciamento.domain.exception;

public class ParametroInvalidoException extends RuntimeException{


    public ParametroInvalidoException(String message) {
        super(message);
    }

    public ParametroInvalidoException(String fieldName, ParametroTipo tipo ) {
        super(String.format("%s %s", fieldName,tipo.mensagemBase));
    }

    public  enum ParametroTipo {
        STRING_BLANK("não pode ser vazio"),

        DATE_NULL("não pode ser nulo");

        public final String mensagemBase;

        ParametroTipo(String mensagemBase) {
            this.mensagemBase = mensagemBase;
        }
    }

}
