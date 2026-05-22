package com.yemm.usuario_service.services;

import com.yemm.usuario_service.entities.Usuario;
import com.yemm.usuario_service.repositories.IUsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioDao usuarioDao;

    @Override
    public List<Usuario> findAll() {
        return usuarioDao.findAll();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioDao.findById(id);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuarioDao.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usuarioDao.existsByEmail(email);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioDao.save(usuario);
    }

    @Override
    public void delete(Long id) {
        usuarioDao.deleteById(id);
    }
}