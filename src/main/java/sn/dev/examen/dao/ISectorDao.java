package sn.dev.examen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.dev.examen.entity.SectorEntity;

@Repository
public interface ISectorDao extends JpaRepository<SectorEntity, Integer> {

}
