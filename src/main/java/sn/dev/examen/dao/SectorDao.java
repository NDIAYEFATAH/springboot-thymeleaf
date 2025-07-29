package sn.dev.examen.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import sn.dev.examen.entity.SectorEntity;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class SectorDao {
    EntityManager entityManager;

    public Optional<SectorEntity> allSectors()
    {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SectorEntity> cr = cb.createQuery(SectorEntity.class);
        Root<SectorEntity> root = cr.from(SectorEntity.class);
        cr.select(root);
        return Optional.ofNullable(entityManager.createQuery(cr).getSingleResult());
    }
}
