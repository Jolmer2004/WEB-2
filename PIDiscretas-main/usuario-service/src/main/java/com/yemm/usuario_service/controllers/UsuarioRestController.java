package com.yemm.usuario_service.controllers;

import com.yemm.usuario_service.entities.Usuario;
import com.yemm.usuario_service.services.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/${api.version}/usuario-service")
public class UsuarioRestController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        if (!usuario.isPresent()) {
            Map<String, Object> error = new HashMap<>();
            error.put("mensaje", "Usuario con ID " + id + " no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.ok(usuario.get());
    }

    @PostMapping("/usuarios/registro")
    public ResponseEntity<?> registrar(@Valid @RequestBody Usuario usuario,
                                       BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(construirErrores(result));
        }
        if (usuarioService.existsByEmail(usuario.getEmail())) {
            Map<String, Object> error = new HashMap<>();
            error.put("mensaje", "Ya existe un usuario con el email: " + usuario.getEmail());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    @PostMapping("/usuarios/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
        String email    = credenciales.get("email");
        String password = credenciales.get("password");
        Optional<Usuario> usuario = usuarioService.findByEmail(email);
        if (!usuario.isPresent() || !usuario.get().getPassword().equals(password)) {
            Map<String, Object> error = new HashMap<>();
            error.put("mensaje", "Email o contraseña incorrectos");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
        return ResponseEntity.ok(usuario.get());
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @Valid @RequestBody Usuario usuario,
                                        BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(construirErrores(result));
        }
        Optional<Usuario> actual = usuarioService.findById(id);
        if (!actual.isPresent()) {
            Map<String, Object> error = new HashMap<>();
            error.put("mensaje", "Usuario con ID " + id + " no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        usuario.setId(id);
        usuario.setCreateAt(actual.get().getCreateAt());
        return ResponseEntity.ok(usuarioService.save(usuario));
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (!usuarioService.findById(id).isPresent()) {
            Map<String, Object> error = new HashMap<>();
            error.put("mensaje", "Usuario con ID " + id + " no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private Map<String, Object> construirErrores(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        List<String> lista = result.getFieldErrors()
            .stream()
            .map(fe -> "El campo '" + fe.getField() + "' " + fe.getDefaultMessage())
            .collect(Collectors.toList());
        errores.put("errors", lista);
        return errores;
    }
}