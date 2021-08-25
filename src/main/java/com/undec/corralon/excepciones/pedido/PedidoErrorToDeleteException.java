package com.undec.corralon.excepciones.pedido;

public class PedidoErrorToDeleteException extends PedidoException {
    public PedidoErrorToDeleteException() {
        super("PedidoErrorToDeleteException: Se genero error al eliminar pedido");
    }
}
