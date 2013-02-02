package com.dkord;

import com.dkord.datamodel.CateringProvider;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Peter Gyschuk
 */
@Local
public interface CateringProviderLocal {

    List<CateringProvider> findAll();
    
    CateringProvider save(CateringProvider cateringProvider);
    
    void delete(CateringProvider cateringProvider);
}
