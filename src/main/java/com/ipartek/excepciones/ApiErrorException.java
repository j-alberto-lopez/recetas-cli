package com.ipartek.excepciones;

import com.ipartek.modelo.MsgError;

public class ApiErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private int codigo;
    private String mensaje;
    
    public ApiErrorException(MsgError msgError) {
        super(msgError.getMensaje());
        this.codigo = msgError.getCodigo();
        this.mensaje = msgError.getMensaje();
    }
    
    public ApiErrorException(int codigo, String mensaje) {
        super(mensaje);
        this.codigo = codigo;
        this.mensaje = mensaje;
    }
    
    public int getCodigo() {
        return codigo;
    }
    
    public String getMensaje() {
        return mensaje;
    }
}