package sn.dev.examen.mapper;

import org.mapstruct.Mapper;
import sn.dev.examen.dao.ClasseDao;
import sn.dev.examen.dto.ClasseDto;
import sn.dev.examen.entity.ClasseEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = SectorMapper.class)
public interface ClasseMapper {

    ClasseDto toClasseDto(ClasseEntity classeEntity);
    ClasseEntity toClasseEntity(ClasseDto classeDto);
    List<ClasseDto> listClasseEntityToClasseDto(List<ClasseEntity> classeEntities);
}
