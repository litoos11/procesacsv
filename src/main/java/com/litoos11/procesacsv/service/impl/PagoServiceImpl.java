/*
 * 
 */
package com.litoos11.procesacsv.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.litoos11.procesacsv.configuration.EntityOracleConfiguration;
import com.litoos11.procesacsv.entity.Pago;
import com.litoos11.procesacsv.repository.PagoRepository;
import com.litoos11.procesacsv.service.PagoService;



// TODO: Auto-generated Javadoc
/**
 * The Class PagoServiceImpl.
 */
@Service("pagoServiceImpl")
public class PagoServiceImpl implements PagoService{

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(PagoServiceImpl.class);
	
	/** The pago repository. */
	@Autowired
	@Qualifier("pagoRepository")
	private PagoRepository pagoRepository;
	
	/** The entity oracle configuration. */
	@Autowired
    private EntityOracleConfiguration entityOracleConfiguration;
	
	/* (non-Javadoc)
	 * @see com.litoos11.procesacsv.service.PagoService#addPago(com.litoos11.procesacsv.entity.Pago)
	 */
	public Pago addPago(Pago pago) {
		// TODO Auto-generated method stub
		return pagoRepository.save(pago);
	}

	/* (non-Javadoc)
	 * @see com.litoos11.procesacsv.service.PagoService#savePago(java.util.List)
	 */
	public void savePago(List<Pago> listPagos) {
		// TODO Auto-generated method stub
		try {
			EntityManagerFactory emf = entityOracleConfiguration.entityManagerFactory().getObject();
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			int batcSize = 500;
			Pago pago = new Pago();
			for(int iPago = 0; iPago < listPagos.size(); iPago++) {
				pago = listPagos.get(iPago);
				em.persist(pago);
				
				if(iPago % batcSize == 0) {
					em.flush();
					em.clear();
					tx.commit();
					tx.begin();
				}
			}
			
			tx.commit();
			em.close();	
		}catch(Exception ex) {
			LOG.error("--ERROR EN METHOD savePago():  " + ex.getMessage());
		}
		
	}
	
	


}
