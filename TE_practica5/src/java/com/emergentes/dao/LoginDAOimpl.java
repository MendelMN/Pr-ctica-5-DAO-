package com.emergentes.dao;
import com.emergentes.modelo.Login;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LoginDAOimpl extends ConexionDB implements LoginDAO{
    @Override
    public Login getById(int id) throws Exception {
        Login cli = new Login();
        try {
            this.conectar();
            
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM usuarios WHERE id = ?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                cli.setId(rs.getInt("id"));
                cli.setUsuario(rs.getString("usuario"));
                cli.setPassword(rs.getString("password"));
                
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return cli;
    }

    @Override
    public List<Login> getAll() throws Exception {
       List<Login> lista = null;
        try {
            this.conectar();
            
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM usuarios");
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Login>();
            while (rs.next()){
                Login cli =  new Login();
                
                cli.setId(rs.getInt("id"));
                cli.setUsuario(rs.getString("usuario"));
                cli.setPassword(rs.getString("password"));

                lista.add(cli);
            }
            rs.close();
            ps.close();
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }
    
}
