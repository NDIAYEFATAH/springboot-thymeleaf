package sn.dev.examen.service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sn.dev.examen.dao.ISectorDao;
import sn.dev.examen.dto.ClasseDto;
import sn.dev.examen.dto.SectorDto;
import sn.dev.examen.entity.ClasseEntity;
import sn.dev.examen.entity.SectorEntity;
import sn.dev.examen.mapper.SectorMapper;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Setter
public class SectorService implements ISectorService{
    private ISectorDao sectorDao;
    private SectorMapper sectorMapper;
    private MessageSource messageSource;

    @Override
    public List<SectorDto> getAllSectors() {
        return sectorMapper.listSectorEntityToSectorDto(sectorDao.findAll());
    }

    @Override
    public SectorDto createSector(SectorDto sectorDto) {
        SectorEntity entity = sectorMapper.toSectorEntity(sectorDto);
        SectorEntity savedEntity = sectorDao.save(entity);
        return sectorMapper.toSectorDto(savedEntity);
    }

    @Override
    public SectorDto updateSector(SectorDto sectorDto) {
        // Vérification de l'existence de l'entité
        Optional<SectorEntity> optionalSector = sectorDao.findById(sectorDto.getId());

        if (optionalSector.isPresent()) {
            SectorEntity existingSector = optionalSector.get();

            // Mise à jour des champs
            existingSector.setName(sectorDto.getName());

            // Sauvegarde
            SectorEntity updated = sectorDao.save(existingSector);

            // Conversion vers DTO
            return sectorMapper.toSectorDto(updated);
        } else {
            // Gestion d'erreur possible ici
            throw new RuntimeException("Secteur avec ID " + sectorDto.getId() + " introuvable.");
        }
    }

    @Override
    public void deleteSector(int id) {
        // Vérifie si l'entité existe avant de supprimer
        if (sectorDao.existsById(id)) {
            sectorDao.deleteById(id);
        } else {
            throw new RuntimeException("Aucun secteur trouvé avec l'id " + id);
        }
    }

    @Override
    public SectorDto getSectorById(int id) {
        SectorEntity entity = sectorDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Secteur introuvable avec l'ID : " + id));

        return sectorMapper.toSectorDto(entity);
    }

    @Override
    public Page<SectorDto> getAllClassesPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SectorEntity> pageResult = sectorDao.findAll(pageable);
        return pageResult.map(sectorMapper::toSectorDto);
    }

}
