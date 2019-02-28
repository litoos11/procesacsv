package com.litoos11.procesacsv.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.litoos11.procesacsv.component.ArchivoComponent;
import com.litoos11.procesacsv.entity.Pago;
import com.litoos11.procesacsv.model.ResponseFileModel;
import com.litoos11.procesacsv.service.PagoService;

// TODO: Auto-generated Javadoc
/**
 * The Class ArchivoController.
 */
@RestController
@RequestMapping("/procesa-pagos/v1")
public class ArchivoController {
	
	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(ArchivoController.class);
	
	/** The archivo component. */
	@Autowired
	@Qualifier("archivoComponent")
	private ArchivoComponent archivoComponent;
	
	/** The pago service. */
	@Autowired
	@Qualifier("pagoServiceImpl")
	private PagoService pagoService;

	
	/**
	 * Hola.
	 *
	 * @return the string
	 */
	@GetMapping("/hola")
	public String hola() {
		return "HOLA DESDE hola()";
	}
	
	/**
	 * Gets the file server.
	 *
	 * @return the file server
	 */
	@GetMapping("/getfile")
	public ResponseEntity<List<ResponseFileModel>> getFileServer() {
		LOG.info("--METHOD:  getFileServer();");
		List<String> listFiles = archivoComponent.getFileServer();
		List<ResponseFileModel> listResFile = new ArrayList<ResponseFileModel>(); 
		
		for(int i = 0; i < listFiles.size(); i++) {			
			listResFile.add(new ResponseFileModel(i, listFiles.get(i)));
			//archivoComponent.deleteFileLocal(listFiles.get(i));
		}
		
		return new ResponseEntity<List<ResponseFileModel>>(listResFile, HttpStatus.OK);
		
	}
	
	/**
	 * Carga data.
	 *
	 * @return the response entity
	 */
	@GetMapping("/carga-datos")
	public ResponseEntity<List<ResponseFileModel>> cargaData() {		
		LOG.info("--METHOD: cargaData();");
		List<String> listFiles = archivoComponent.getFileServer();		
		//Iterator<String> iFiles = listFiles.iterator();
		List<ResponseFileModel> listResFile = new ArrayList<ResponseFileModel>();
		//ResponsePagoModel resModel = new ResponsePagoModel();
		try {
			//LOG.info(" --LONGITUD: " + listFiles.size());
			for(int iFile = 0; iFile < listFiles.size(); iFile++) {	 
				//List<Pago> listPagos =  archivoComponent.readFileXls(listFiles.get(iFile));				
				List<Pago> listPagos =  archivoComponent.readFileCSV(listFiles.get(iFile));
				LOG.info(" --PAGOS A INSERTAR: " + listPagos.size());
				pagoService.savePago(listPagos);
				listResFile.add(new ResponseFileModel(iFile, listFiles.get(iFile)));
				
			}		
		}catch(Exception ex) {
			LOG.error("--ERROR METHOD cargaData(): " + ex.getMessage());
		}
	
		archivoComponent.deleteFileLocal(listFiles);
		return new ResponseEntity<List<ResponseFileModel>>(listResFile, HttpStatus.OK);
	
	}
}