package com.litoos11.procesacsv.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

// TODO: Auto-generated Javadoc
/**
 * The Class Pago.
 */
@Entity
@Table(name = "xxcust_pago_csv")
public class Pago {

	/** The id. */
	@Id
	@SequenceGenerator(name = "xxcust_pago_csv_seq", sequenceName = "xxcust_pago_csv_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "xxcust_pago_csv_seq")
	@Column(name = "id")
	private int id;

	/** The numero solicitud. */
	// @Id
	@Column(name = "numero_solicitud")
	private String numero_solicitud;

	/** The numero operacion. */
	@Column(name = "numero_operacion")
	private String numero_operacion;

	/** The tipo pago. */
	@Column(name = "tipo_pago")
	private String tipo_pago;

	/** The origen. */
	@Column(name = "origen")
	private String origen;

	/** The numero documento. */
	@Column(name = "numero_documento")
	private String numero_documento;

	/** The fecha alta. */
	@Column(name = "fecha_alta")
	@Temporal(TemporalType.DATE)
	private Date fecha_alta;

	/** The fecha pago. */
	@Column(name = "fecha_pago")
	@Temporal(TemporalType.DATE)
	private Date fecha_pago;

	/** The importe pago. */
	@Column(name = "importe_pago")
	private int importe_pago;

	/** The premium. */
	@Column(name = "premium")
	private int premium;

	/** The moratorio. */
	@Column(name = "moratorio")
	private int moratorio;

	/** The otros ingresos. */
	@Column(name = "otros_ingresos")
	private int otros_ingresos;

	/** The nota credito. */
	@Column(name = "nota_credito")
	private int nota_credito;

	/** The factura. */
	@Column(name = "factura")
	private String factura;

	/** The fecha. */
	@Column(name = "fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;

	/** The nombre archivo. */
	@Column(name = "nombre_archivo")
	private String nombre_archivo;

	/** The fecha carga. */
	@Column(name = "fecha_carga")
	@Temporal(TemporalType.DATE)
	private Date fecha_carga;

	/**
	 * Instantiates a new pago.
	 *
	 * @param numero_solicitud the numero solicitud
	 * @param numero_operacion the numero operacion
	 * @param tipo_pago the tipo pago
	 * @param origen the origen
	 * @param numero_documento the numero documento
	 * @param fecha_alta the fecha alta
	 * @param fecha_pago the fecha pago
	 * @param importe_pago the importe pago
	 * @param premium the premium
	 * @param moratorio the moratorio
	 * @param otros_ingresos the otros ingresos
	 * @param nota_credito the nota credito
	 * @param factura the factura
	 * @param fecha the fecha
	 * @param nombre_archivo the nombre archivo
	 * @param fecha_carga the fecha carga
	 */
	public Pago(String numero_solicitud, String numero_operacion, String tipo_pago, String origen,
			String numero_documento, Date fecha_alta, Date fecha_pago, int importe_pago, int premium, int moratorio,
			int otros_ingresos, int nota_credito, String factura, Date fecha, String nombre_archivo, Date fecha_carga) {
		super();
		this.numero_solicitud = numero_solicitud;
		this.numero_operacion = numero_operacion;
		this.tipo_pago = tipo_pago;
		this.origen = origen;
		this.numero_documento = numero_documento;
		this.fecha_alta = fecha_alta;
		this.fecha_pago = fecha_pago;
		this.importe_pago = importe_pago;
		this.premium = premium;
		this.moratorio = moratorio;
		this.otros_ingresos = otros_ingresos;
		this.nota_credito = nota_credito;
		this.factura = factura;
		this.fecha = fecha;
		this.nombre_archivo = nombre_archivo;
		this.fecha_carga = fecha_carga;
	}

	/**
	 * Instantiates a new pago.
	 */
	public Pago() {
	}

	/**
	 * Gets the numero solicitud.
	 *
	 * @return the numero solicitud
	 */
	public String getNumero_solicitud() {
		return numero_solicitud;
	}

	/**
	 * Sets the numero solicitud.
	 *
	 * @param numero_solicitud the new numero solicitud
	 */
	public void setNumero_solicitud(String numero_solicitud) {
		this.numero_solicitud = numero_solicitud;
	}

	/**
	 * Gets the numero operacion.
	 *
	 * @return the numero operacion
	 */
	public String getNumero_operacion() {
		return numero_operacion;
	}

	/**
	 * Sets the numero operacion.
	 *
	 * @param numero_operacion the new numero operacion
	 */
	public void setNumero_operacion(String numero_operacion) {
		this.numero_operacion = numero_operacion;
	}

	/**
	 * Gets the tipo pago.
	 *
	 * @return the tipo pago
	 */
	public String getTipo_pago() {
		return tipo_pago;
	}

	/**
	 * Sets the tipo pago.
	 *
	 * @param tipo_pago the new tipo pago
	 */
	public void setTipo_pago(String tipo_pago) {
		this.tipo_pago = tipo_pago;
	}

	/**
	 * Gets the origen.
	 *
	 * @return the origen
	 */
	public String getOrigen() {
		return origen;
	}

	/**
	 * Sets the origen.
	 *
	 * @param origen the new origen
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}

	/**
	 * Gets the numero documento.
	 *
	 * @return the numero documento
	 */
	public String getNumero_documento() {
		return numero_documento;
	}

	/**
	 * Sets the numero documento.
	 *
	 * @param numero_documento the new numero documento
	 */
	public void setNumero_documento(String numero_documento) {
		this.numero_documento = numero_documento;
	}

	/**
	 * Gets the fecha alta.
	 *
	 * @return the fecha alta
	 */
	public Date getFecha_alta() {
		return fecha_alta;
	}

	/**
	 * Sets the fecha alta.
	 *
	 * @param fecha_alta the new fecha alta
	 */
	public void setFecha_alta(Date fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	/**
	 * Gets the fecha pago.
	 *
	 * @return the fecha pago
	 */
	public Date getFecha_pago() {
		return fecha_pago;
	}

	/**
	 * Sets the fecha pago.
	 *
	 * @param fecha_pago the new fecha pago
	 */
	public void setFecha_pago(Date fecha_pago) {
		this.fecha_pago = fecha_pago;
	}

	/**
	 * Gets the importe pago.
	 *
	 * @return the importe pago
	 */
	public int getImporte_pago() {
		return importe_pago;
	}

	/**
	 * Sets the importe pago.
	 *
	 * @param importe_pago the new importe pago
	 */
	public void setImporte_pago(int importe_pago) {
		this.importe_pago = importe_pago;
	}

	/**
	 * Gets the premium.
	 *
	 * @return the premium
	 */
	public int getPremium() {
		return premium;
	}

	/**
	 * Sets the premium.
	 *
	 * @param premium the new premium
	 */
	public void setPremium(int premium) {
		this.premium = premium;
	}

	/**
	 * Gets the moratorio.
	 *
	 * @return the moratorio
	 */
	public int getMoratorio() {
		return moratorio;
	}

	/**
	 * Sets the moratorio.
	 *
	 * @param moratorio the new moratorio
	 */
	public void setMoratorio(int moratorio) {
		this.moratorio = moratorio;
	}

	/**
	 * Gets the otros ingresos.
	 *
	 * @return the otros ingresos
	 */
	public int getOtros_ingresos() {
		return otros_ingresos;
	}

	/**
	 * Sets the otros ingresos.
	 *
	 * @param otros_ingresos the new otros ingresos
	 */
	public void setOtros_ingresos(int otros_ingresos) {
		this.otros_ingresos = otros_ingresos;
	}

	/**
	 * Gets the nota credito.
	 *
	 * @return the nota credito
	 */
	public int getNota_credito() {
		return nota_credito;
	}

	/**
	 * Sets the nota credito.
	 *
	 * @param nota_credito the new nota credito
	 */
	public void setNota_credito(int nota_credito) {
		this.nota_credito = nota_credito;
	}

	/**
	 * Gets the factura.
	 *
	 * @return the factura
	 */
	public String getFactura() {
		return factura;
	}

	/**
	 * Sets the factura.
	 *
	 * @param factura the new factura
	 */
	public void setFactura(String factura) {
		this.factura = factura;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha the new fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the nombre archivo.
	 *
	 * @return the nombre archivo
	 */
	public String getNombre_archivo() {
		return nombre_archivo;
	}

	/**
	 * Sets the nombre archivo.
	 *
	 * @param nombre_archivo the new nombre archivo
	 */
	public void setNombre_archivo(String nombre_archivo) {
		this.nombre_archivo = nombre_archivo;
	}

	/**
	 * Gets the fecha carga.
	 *
	 * @return the fecha carga
	 */
	public Date getFecha_carga() {
		return fecha_carga;
	}

	/**
	 * Sets the fecha carga.
	 *
	 * @param fecha_carga the new fecha carga
	 */
	public void setFecha_carga(Date fecha_carga) {
		this.fecha_carga = fecha_carga;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

}
