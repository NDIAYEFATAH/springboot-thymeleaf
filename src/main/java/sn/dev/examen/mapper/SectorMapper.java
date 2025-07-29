package sn.dev.examen.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import sn.dev.examen.dao.SectorDao;
import sn.dev.examen.dto.SectorDto;
import sn.dev.examen.entity.SectorEntity;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SectorMapper {
    SectorDto toSectorDto(SectorEntity sectorEntity);
    SectorEntity toSectorEntity(SectorDto sectorDto);
    List<SectorDto> listSectorEntityToSectorDto(List<SectorEntity> sectorEntities);
}
