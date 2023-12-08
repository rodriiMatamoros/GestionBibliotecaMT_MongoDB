package io.bootify.gestion_biblioteca_m_t.service;

import io.bootify.gestion_biblioteca_m_t.domain.Libro;
import io.bootify.gestion_biblioteca_m_t.model.LibroDTO;
import io.bootify.gestion_biblioteca_m_t.repos.LibroRepository;
import io.bootify.gestion_biblioteca_m_t.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(final LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<LibroDTO> findAll() {
        final List<Libro> libroes = libroRepository.findAll(Sort.by("id"));
        return libroes.stream()
                .map(libro -> mapToDTO(libro, new LibroDTO()))
                .toList();
    }

    public LibroDTO get(final Long id) {
        return libroRepository.findById(id)
                .map(libro -> mapToDTO(libro, new LibroDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final LibroDTO libroDTO) {
        final Libro libro = new Libro();
        mapToEntity(libroDTO, libro);
        return libroRepository.save(libro).getId();
    }

    public void update(final Long id, final LibroDTO libroDTO) {
        final Libro libro = libroRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(libroDTO, libro);
        libroRepository.save(libro);
    }

    public void delete(final Long id) {
        libroRepository.deleteById(id);
    }

    private LibroDTO mapToDTO(final Libro libro, final LibroDTO libroDTO) {
        libroDTO.setId(libro.getId());
        libroDTO.setAutor(libro.getAutor());
        return libroDTO;
    }

    private Libro mapToEntity(final LibroDTO libroDTO, final Libro libro) {
        libro.setAutor(libroDTO.getAutor());
        return libro;
    }

}
