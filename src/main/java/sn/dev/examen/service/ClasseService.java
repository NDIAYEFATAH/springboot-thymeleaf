package sn.dev.examen.service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sn.dev.examen.dao.IClasseDao;
import sn.dev.examen.dao.ISectorDao;
import sn.dev.examen.dto.ClasseDto;
import sn.dev.examen.entity.ClasseEntity;
import sn.dev.examen.entity.SectorEntity;
import sn.dev.examen.mapper.ClasseMapper;

import java.util.List;

@Service
@AllArgsConstructor
@Setter
public class ClasseService implements IClasseService {
    private IClasseDao classeDao;
    private ISectorDao sectorDao;
    private ClasseMapper classeMapper;
    @Override
    public List<ClasseDto> getAllClasses() {
        return classeMapper.listClasseEntityToClasseDto(classeDao.findAll());
    }

    @Override
    public ClasseDto createClasse(ClasseDto classeDto) {
        ClasseEntity entity = classeMapper.toClasseEntity(classeDto);
        // Chercher le secteur correspondant
        SectorEntity sector = sectorDao.findById(classeDto.getSectorId())
                .orElseThrow(() -> new RuntimeException("Secteur non trouvé avec ID: " + classeDto.getSectorId()));

        entity.setSector(sector);
        ClasseEntity saved = classeDao.save(entity);
        return classeMapper.toClasseDto(saved);
    }

    @Override
    public ClasseDto updateClasse(ClasseDto dto) {
        ClasseEntity classe = classeDao.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Classe non trouvée"));

        classe.setClassName(dto.getClassName());
        classe.setDescription(dto.getDescription());

        // Récupérer le secteur via son ID (venant du formulaire)
        SectorEntity sector = sectorDao.findById(dto.getSector().getId())
                .orElseThrow(() -> new RuntimeException("Secteur non trouvé"));

        classe.setSector(sector);

        classeDao.save(classe);
        return classeMapper.toClasseDto(classe);
    }


    @Override
    public void deleteClasse(int id) {
        // Vérifie si la classe existe
        ClasseEntity classe = classeDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Classe avec ID " + id + " non trouvée"));

        // Supprime la classe
        classeDao.delete(classe);
    }
    public Page<ClasseDto> getAllClassesPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ClasseEntity> pageResult = classeDao.findAll(pageable);
        return pageResult.map(classeMapper::toClasseDto);
    }
    public List<ClasseDto> searchClasses(String query) {
        List<ClasseEntity> results = classeDao.searchByQuery(query);
        return classeMapper.listClasseEntityToClasseDto(results);
    }
}
