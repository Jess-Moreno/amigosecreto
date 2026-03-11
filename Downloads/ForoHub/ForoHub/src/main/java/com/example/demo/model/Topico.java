package com.example.demo.model;

import com.example.demo.dto.DatosRegistroTopico;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private String estatus = "NO_RESPONDIDO";
    private String autor;
    private String curso;

    public Topico() {}

    public Topico(DatosRegistroTopico datos) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.autor = datos.autor();
        this.curso = datos.curso();
    }

    // Getters manuales para no depender de Lombok
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getMensaje() { return mensaje; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public String getEstatus() { return estatus; }
    public String getAutor() { return autor; }
    public String getCurso() { return curso; }
}