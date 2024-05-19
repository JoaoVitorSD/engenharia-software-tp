package ai.attus.gerenciamento.domain.util;

import ai.attus.gerenciamento.domain.exception.ParametroInvalidoException;

import java.time.LocalDate;

import static ai.attus.gerenciamento.domain.exception.ParametroInvalidoException.ParametroTipo;
public class Validator {


    public static void stringBlank(String value, String fieldName){
        if(value==null || value.isBlank())
            throw new ParametroInvalidoException(fieldName,ParametroTipo.STRING_BLANK);
    }
    public static void dateNull(LocalDate value, String fieldName){
        if(value==null)
            throw new ParametroInvalidoException(fieldName,ParametroTipo.DATE_NULL);
    }
}
