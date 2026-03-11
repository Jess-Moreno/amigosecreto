package com.example.demo.repository;

import com.example.demo.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    // Agrega esta línea mágica aquí abajo:
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
}