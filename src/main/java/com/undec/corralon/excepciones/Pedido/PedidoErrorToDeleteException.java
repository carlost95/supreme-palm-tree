package com.undec.corralon.excepciones.Pedido;

public class PedidoErrorToDeleteException extends PedidoException {
    public PedidoErrorToDeleteException() {
        super("PedidoErrorToDeleteException: Se genero error al eliminar pedido");
    }
}
