package io.bootify.gestion_biblioteca_m_t.repos;

import io.bootify.gestion_biblioteca_m_t.domain.Libro;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LibroRepository extends JpaRepository<Libro, Long> {
}
