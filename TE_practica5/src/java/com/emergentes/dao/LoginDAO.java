package com.emergentes.dao;
import com.emergentes.modelo.Login;
import java.util.List;
public interface LoginDAO {
    
    public Login getById(int id) throws Exception;
    public List<Login> getAll() throws Exception;
}
