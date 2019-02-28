package com.litoos11.procesacsv.service;

import java.util.List;

import com.litoos11.procesacsv.entity.Pago;

// TODO: Auto-generated Javadoc
/**
 * The Interface PagoService.
 */
public interface PagoService {

	/**
	 * Adds the pago.
	 *
	 * @param pago the pago
	 * @return the pago
	 */
	public abstract Pago addPago(Pago pago);
	
	/**
	 * Save pago.
	 *
	 * @param listPagos the list pagos
	 */
	public abstract void savePago(List<Pago> listPagos);
}
