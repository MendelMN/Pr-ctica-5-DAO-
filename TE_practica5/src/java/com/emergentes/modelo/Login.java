package com.emergentes.modelo;
public class Login {
    private int id;
    private String usuario;
    private String password;

    public Login() {
    }
    @Override
    public String toString() {
        return "Login{" + "id=" + id + ", usuario=" + usuario + ", password=" + password + '}';
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
  
}
