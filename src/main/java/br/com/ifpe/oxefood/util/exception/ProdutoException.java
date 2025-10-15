package br.com.ifpe.oxefood.util.exception;
    //erro 500 erro interno
    //excessão checada= extends exception/ precisa declarar se a função pode ser declarada ou n
    //exccessao n checada= RuntimeException/ não precisa do throws

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class ProdutoException extends RuntimeException {

    public static final String MSG_VALOR_MINIMO_PRODUTO = "Não é permitido inserir produtos com valores inferiores a R$ 20 e maiores que R$ 100.";

    public ProdutoException(String msg) {

	super(String.format(msg));
    }
}
