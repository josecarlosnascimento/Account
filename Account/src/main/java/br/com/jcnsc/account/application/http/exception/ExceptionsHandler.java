package br.com.jcnsc.account.application.http.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e, WebRequest request){

        ExceptionDTO exceptionDTO = ExceptionDTO.builder().code(HttpStatus.NOT_FOUND.value())
                                                            .description(e.getMessage())
                                                            .build();

        return  handleExceptionInternal(e, exceptionDTO, new HttpHeaders(), HttpStatus.NOT_FOUND, request);

    }

    @ExceptionHandler(DataNulaException.class)
    public ResponseEntity<Object> handleDataNulaException(DataNulaException e, WebRequest request){

        ExceptionDTO exceptionDTO = ExceptionDTO.builder().code(HttpStatus.NOT_FOUND.value())
                .description(e.getMessage())
                .build();

        return  handleExceptionInternal(e, exceptionDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @ExceptionHandler(DataInvalidaException.class)
    public ResponseEntity<Object> handleDataInvalidaException(DataInvalidaException e, WebRequest request){

        ExceptionDTO exceptionDTO = ExceptionDTO.builder().code(HttpStatus.NOT_FOUND.value())
                .description(e.getMessage())
                .build();

        return  handleExceptionInternal(e, exceptionDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<ExceptionDTO> errorList =  listaErro(ex.getBindingResult());
        return handleExceptionInternal(ex, errorList, headers, status, request);
    }

    private List<ExceptionDTO> listaErro(BindingResult bindingResult){
        List<ExceptionDTO> erros = new ArrayList<>();

        for (FieldError erro : bindingResult.getFieldErrors()) {
            erros.add(ExceptionDTO.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .description(erro.getDefaultMessage())
                    .build());
        }

        return erros;
    }
}