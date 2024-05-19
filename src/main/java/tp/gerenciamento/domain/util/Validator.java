package tp.gerenciamento.domain.util;

import tp.gerenciamento.domain.exception.ParametroInvalidoException;

import java.time.LocalDate;

public class Validator {


    public static void stringBlank(String value, String fieldName){
        if(value==null || value.isBlank())
            throw new ParametroInvalidoException(fieldName, ParametroInvalidoException.ParametroTipo.STRING_BLANK);
    }
    public static void dateNull(LocalDate value, String fieldName){
        if(value==null)
            throw new ParametroInvalidoException(fieldName, ParametroInvalidoException.ParametroTipo.DATE_NULL);
    }
}
