package com.litoos11.procesacsv.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.litoos11.procesacsv.entity.Pago;

/**
 * The Interface PagoRepository.
 */
@Repository("pagoRepository")
public interface PagoRepository extends JpaRepository<Pago, Serializable> {

}
