package com.emergentes.dao;
import com.emergentes.modelo.Blog;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class BlogDAOimpl extends ConexionDB implements BlogDAO{

    @Override
    public void insert(Blog blog) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("INSERT INTO posts (fecha,titulo,contenido) values (?,?,?)");
            ps.setDate(1,   blog.getFecha());
            ps.setString(2, blog.getTitulo());
            ps.setString(3, blog.getContenido());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Blog blog) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE posts SET fecha = ?, titulo = ?, contenido = ? where id = ?");
            ps.setDate(1, blog.getFecha());
            ps.setString(2, blog.getTitulo());
            ps.setString(3, blog.getContenido());
            ps.setInt(4, blog.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM posts WHERE id = ?");
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Blog getById(int id) throws Exception {
       Blog cli = new Blog();
        try {
            this.conectar();
            
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM posts WHERE id = ?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                cli.setId(rs.getInt("id"));
                cli.setFecha(rs.getDate("fecha"));
                cli.setTitulo(rs.getString("titulo"));
                cli.setContenido(rs.getString("contenido"));
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return cli;
    }

    @Override
    public List<Blog> getAll() throws Exception {
        List<Blog> lista = null;
        try {
            this.conectar();
            
            PreparedStatement ps = this.conn.prepareStatement("SELECT* FROM posts");
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Blog>();
            while (rs.next()){
                Blog cli =  new Blog();
                
                cli.setId(rs.getInt("id"));
                cli.setFecha(rs.getDate("fecha"));
                cli.setTitulo(rs.getString("titulo"));
                cli.setContenido(rs.getString("contenido"));
                
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
