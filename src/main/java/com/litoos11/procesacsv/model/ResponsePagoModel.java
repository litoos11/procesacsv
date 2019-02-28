package com.litoos11.procesacsv.model;

import java.util.List;

import com.litoos11.procesacsv.entity.Pago;

// TODO: Auto-generated Javadoc
/**
 * The Class ResponsePagoModel.
 */
public class ResponsePagoModel {

	/** The total insert. */
	private int total_insert;
	
	/** The lista pagos. */
	private List<Pago> lista_pagos;

	/**
	 * Gets the total insert.
	 *
	 * @return the total insert
	 */
	public int getTotal_insert() {
		return total_insert;
	}

	/**
	 * Sets the total insert.
	 *
	 * @param total_insert the new total insert
	 */
	public void setTotal_insert(int total_insert) {
		this.total_insert = total_insert;
	}

	/**
	 * Gets the lista pagos.
	 *
	 * @return the lista pagos
	 */
	public List<Pago> getLista_pagos() {
		return lista_pagos;
	}

	/**
	 * Sets the lista pagos.
	 *
	 * @param lista_pagos the new lista pagos
	 */
	public void setLista_pagos(List<Pago> lista_pagos) {
		this.lista_pagos = lista_pagos;
	}

	/**
	 * Instantiates a new response pago model.
	 *
	 * @param total_insert the total insert
	 * @param lista_pagos the lista pagos
	 */
	public ResponsePagoModel(int total_insert, List<Pago> lista_pagos) {
		super();
		this.total_insert = total_insert;
		this.lista_pagos = lista_pagos;
	}

	/**
	 * Instantiates a new response pago model.
	 */
	public ResponsePagoModel() {
	}
}
