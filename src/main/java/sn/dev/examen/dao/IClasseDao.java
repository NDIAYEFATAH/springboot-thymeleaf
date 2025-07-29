package sn.dev.examen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sn.dev.examen.entity.ClasseEntity;

import java.util.List;

@Repository
public interface IClasseDao extends JpaRepository<ClasseEntity, Integer> {
    @Query("SELECT c FROM ClasseEntity c WHERE LOWER(c.className) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(c.description) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<ClasseEntity> searchByQuery(@Param("query") String query);

}
