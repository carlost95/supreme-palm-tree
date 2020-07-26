package com.undec.corralon.modelo;

public class JwtRequest {
    private String nombreUsuario;
    private String password;

    //need default constructor for JSON Parsing
    public JwtRequest()
    {

    }

    public JwtRequest(String nombreUsuario, String password) {
        this.setUsername(nombreUsuario);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.nombreUsuario;
    }

    public void setUsername(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
