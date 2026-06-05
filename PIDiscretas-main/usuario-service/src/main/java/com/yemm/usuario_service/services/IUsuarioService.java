package com.yemm.usuario_service.services;

import com.yemm.usuario_service.entities.Usuario;
import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    List<Usuario> findAll();
    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
    Usuario save(Usuario usuario);
    void delete(Long id);
}