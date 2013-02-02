package com.dkord;

import com.dkord.datamodel.CateringProvider;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Peter Gyschuk
 */
@Stateless
public class CateringProviderBean implements CateringProviderLocal {

    @PersistenceContext(name = "delivery-persistence-unit")
    private EntityManager entityManager;
    
    @Override
    public List<CateringProvider> findAll() {
        List<CateringProvider> cateringProviders = entityManager.createNamedQuery("findAllCateringProviders", CateringProvider.class).getResultList();
        return cateringProviders;
    }

    @Override
    public CateringProvider save(CateringProvider cateringProvider) {
        cateringProvider = entityManager.merge(cateringProvider);
        entityManager.persist(cateringProvider);
        return cateringProvider;
    }

    @Override
    public void delete(CateringProvider cateringProvider) {
        cateringProvider = entityManager.merge(cateringProvider);
        entityManager.remove(cateringProvider);
    }
}
