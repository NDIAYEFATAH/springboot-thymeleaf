package sn.dev.examen.service;

import org.springframework.data.domain.Page;
import sn.dev.examen.dto.ClasseDto;
import sn.dev.examen.dto.SectorDto;

import java.util.List;

public interface ISectorService {
    List<SectorDto> getAllSectors();
    SectorDto createSector(SectorDto sectorDto);
    SectorDto updateSector(SectorDto sectorDto);
    void deleteSector(int id);
    SectorDto getSectorById(int id);
    Page<SectorDto> getAllClassesPaginated(int page, int size);
}
