package com.example.demo.controller;

import com.example.demo.dto.DatosDetalleTopico;
import com.example.demo.dto.DatosRegistroTopico;
import com.example.demo.model.Topico;
import com.example.demo.repository.TopicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datos) {

        var existe = repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());
        if (existe) {
            return ResponseEntity.badRequest().body("El tópico con ese título y mensaje ya existe.");
        }

        var topico = new Topico(datos);
        repository.save(topico);
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }
    @GetMapping
    public ResponseEntity<Page<DatosDetalleTopico>> listar(@PageableDefault(size = 10, sort = "fechaCreacion") Pageable paginacion) {
        return ResponseEntity.ok(repository.findAll(paginacion).map(DatosDetalleTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id) {
        var topico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id) {
        var topico = repository.getReferenceById(id);
        repository.delete(topico);
        return ResponseEntity.noContent().build();
    }
}