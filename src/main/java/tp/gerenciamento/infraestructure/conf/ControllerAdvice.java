package tp.gerenciamento.infraestructure.conf;

import tp.gerenciamento.domain.exception.ElementoNaoEncontradoException;
import tp.gerenciamento.domain.exception.ParametroInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ParametroInvalidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleParametroInvalidoException(ParametroInvalidoException e) {
        return new ApiError(e.getMessage());
    }
    @ExceptionHandler(ElementoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleElementoNaoEncontradoException(ElementoNaoEncontradoException e) {
        return new ApiError(e.getMessage());
    }
}
