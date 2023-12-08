package io.bootify.gestion_biblioteca_m_t.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LibroDTO {

    private Long id;

    @Size(max = 255)
    private String autor;

}
