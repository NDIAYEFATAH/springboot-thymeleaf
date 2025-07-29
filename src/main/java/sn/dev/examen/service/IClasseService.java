package sn.dev.examen.service;

import org.springframework.data.domain.Page;
import sn.dev.examen.dto.ClasseDto;

import java.util.List;

public interface IClasseService {
    List<ClasseDto> getAllClasses();
    ClasseDto createClasse(ClasseDto classeDto);
    ClasseDto updateClasse(ClasseDto classeDto);
    void deleteClasse(int id);

    Page<ClasseDto> getAllClassesPaginated(int page, int size);

    List<ClasseDto> searchClasses(String query);
}
