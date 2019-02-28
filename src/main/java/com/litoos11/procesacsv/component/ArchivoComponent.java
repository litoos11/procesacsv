package com.litoos11.procesacsv.component;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.litoos11.procesacsv.constant.Constants;
import com.litoos11.procesacsv.entity.Pago;

// TODO: Auto-generated Javadoc
/**
 * The Class ArchivoComponent.
 */
@Component("archivoComponent")
public class ArchivoComponent {
	
	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(ArchivoComponent.class);
	
	/**
	 * Gets the file server.
	 *
	 * @return the file server
	 */
	public List<String> getFileServer(){
		List<String> listFiles = new ArrayList<>();
		LOG.info("--METHOD: getFileServer();");
		
		try {
			JSch jsch = new JSch();			
			Session session = jsch.getSession(Constants.USER_SERVER, Constants.IP_SERVER, Constants.PORT_SERVER);
			session.setPassword(Constants.PASSWORD_SERVER);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp sftpChannel = (ChannelSftp) channel;
			sftpChannel.cd(Constants.PATH_SERVER);
			@SuppressWarnings("unchecked")
			List<LsEntry> filesEntries = sftpChannel.ls(Constants.PATH_SERVER);
			for (LsEntry file : filesEntries) {
				//LOG.info("--FILE: " + file.getFilename());
				if(!file.getFilename().equals("..") && !file.getFilename().equals(".")) {
					byte[] buffer = new byte[1024];
					BufferedInputStream  bis = new BufferedInputStream(sftpChannel.get(file.getFilename()));
					File outputfile = new File(Constants.PATH_LOCAL + file.getFilename());
					OutputStream os = new FileOutputStream(outputfile);
					BufferedOutputStream bos = new BufferedOutputStream(os);					
					int readCount;
					while((readCount = bis.read(buffer)) > 0) {
						bos.write(buffer, 0, readCount);
					}
					bis.close();
					bos.close();
					
					listFiles.add(file.getFilename());
				}
				
			}
						
			sftpChannel.exit();
			sftpChannel.disconnect();		
			
		}catch(Exception ex) {
			LOG.error("ERROR AL DESCARGAR ARCHIVO: " + ex.getMessage());
		}		
		
		
		return listFiles;
	}
	
	/**
	 * Delete file local.
	 *
	 * @param files the files
	 */
	public void deleteFileLocal(List<String> files) {		
		
		try {
			for(String file : files) {
				File pathFile = new File(Constants.PATH_LOCAL + file);
				LOG.info("--METHOD: deleteFile()  --FILE: " + Constants.PATH_LOCAL + file);
				if(pathFile.exists()) {
					pathFile.delete();
				}
			}		
			
		}catch(Exception ex) {
			LOG.error("--ERROR AL ELIMINAR EL ARCHIVO: " + ex.getMessage());
		}
		
	}
	
	/**
	 * Read file CSV.
	 *
	 * @param inputFile the input file
	 * @return the list
	 * @throws NumberFormatException the number format exception
	 * @throws ParseException the parse exception
	 */
	public List<Pago> readFileCSV(String inputFile) throws NumberFormatException, ParseException  {
		String path = Constants.PATH_LOCAL + inputFile;	
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";
		List<Pago> listPagos = new ArrayList<>();
		
		SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy"); 		
		try {
			br = new BufferedReader(new FileReader(path));
			//LOG.info("--LINEAS: " + br.lines());
			int lineas = 0;
			while((line = br.readLine()) != null){		
				String[] datosPagos = line.split(csvSplitBy);
				//LOG.info("--LENTHG: " + datosPagos.length);
				if(lineas > 0 && datosPagos.length > 0) {
					
					
					listPagos.add( new Pago (datosPagos[0], datosPagos[1], datosPagos[2], datosPagos[3],
							datosPagos[4], formatDate.parse(datosPagos[5]),
							formatDate.parse(datosPagos[6]), (int) Double.parseDouble(datosPagos[7]),
							(int) Double.parseDouble(datosPagos[8]), (int) Double.parseDouble(datosPagos[9]), 
							(int) Double.parseDouble(datosPagos[10]), (int) Double.parseDouble(datosPagos[11]), 
							datosPagos[12], new Date(),
							inputFile, new Date()));
				}
				
				//LOG.info("--LINEAS: " + lineas);
				lineas++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			LOG.info("--FileNotFoundException" + e.getMessage());
			e.printStackTrace();
			
		}catch (IOException e) {
			LOG.info("--IOException" + e.getMessage());
		    e.printStackTrace();
		}finally {
		    if (br != null) {
		        try {
		            br.close();
		        } catch (IOException e) {
		        	LOG.info("--IOException finally" + e.getMessage());
		            e.printStackTrace();
		        }
		    }
			
		}
		
		return listPagos;
	}


	/**
	 * Read file xls.
	 *
	 * @param inputFile the input file
	 * @return the list
	 */
	public List<Pago> readFileXls(String inputFile) {
		String path = Constants.PATH_LOCAL + inputFile;
		List<Pago> listPagos = new ArrayList<>();
		ArrayList<String> datosPagos = new ArrayList<String>();
		//Pago pago = new Pago();
		
		try {
						
			XSSFWorkbook libro = new XSSFWorkbook(new FileInputStream(path));
			
			XSSFSheet hoja = libro.getSheetAt(0);
			Cell celda;
			Row fila;
			
			LOG.info("FILAS: " + hoja.getPhysicalNumberOfRows());
			LOG.info("FILAS: " + hoja.getLastRowNum());
			
			SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy"); 
			DecimalFormat formatDecimal = new DecimalFormat("#");
			
			String cellValue;
			
			Iterator<Row> rowIterator = hoja.iterator();
			
			while(rowIterator.hasNext()) {
				
				fila = rowIterator.next();				
				
				Iterator<Cell> cellIterator = fila.cellIterator();
				//LOG.info("FILA: " + fila.getRowNum());
				//LOG.info("CELDAS: " + fila.getLastCellNum());
				while(cellIterator.hasNext()) {									
					celda = cellIterator.next();
					//LOG.info("COLUMNA: " + celda.getColumnIndex());
					
					switch (celda.getCellType()) {
					case STRING:
						datosPagos.add(celda.getStringCellValue());
						break;
					case NUMERIC:
						
						if(DateUtil.isCellDateFormatted(celda)){ 
						    cellValue = formatDate.format(celda.getDateCellValue()); 
						}else if(celda.getColumnIndex() == 0 || celda.getColumnIndex()== 3) {
							cellValue = (String)formatDecimal.format(celda.getNumericCellValue());
						}else {
							cellValue = String.valueOf(celda.getNumericCellValue());
						}						
						datosPagos.add(cellValue);
						break;
					case _NONE:
						datosPagos.add(String.valueOf(celda.getNumericCellValue()));
						break;

					default :
					}	
					
				}
				
				if(fila.getRowNum() > 0 && !datosPagos.isEmpty()) {
					//LOG.info("FILA: " + fila.getRowNum());
					listPagos.add( new Pago (datosPagos.get(0), datosPagos.get(1), datosPagos.get(2), datosPagos.get(3),
												datosPagos.get(4), formatDate.parse(datosPagos.get(5)),
												formatDate.parse(datosPagos.get(6)), (int) Double.parseDouble(datosPagos.get(7)),
												(int) Double.parseDouble(datosPagos.get(8)), (int) Double.parseDouble(datosPagos.get(9)), 
												(int) Double.parseDouble(datosPagos.get(10)), (int) Double.parseDouble(datosPagos.get(11)), 
												datosPagos.get(12), new Date(),
												inputFile, new Date()));
					
				}
				
				datosPagos.clear();	
			}
			
			libro.close();
			
			
		}catch(Exception ex) {
			LOG.error("ERROR AL PROCESAR ARCHIVO: " + ex.getMessage());			 
		}
	
		
		return listPagos;
	}
	
		
	/**
	 * Read file xls test.
	 *
	 * @param inputFile the input file
	 * @return the list
	 */
	public List<Pago> readFileXlsTest(String inputFile) {
		LOG.info("--METHOD: readFileXlsTest()");
		String path = Constants.PATH_LOCAL + inputFile;
		List<Pago> listPagos = new ArrayList<>();
		ArrayList<String> datosPagos = new ArrayList<String>();
		//Pago pago = new Pago();
		
		try {
						
			XSSFWorkbook libro = new XSSFWorkbook(new FileInputStream(path));
			XSSFSheet hoja = libro.getSheetAt(0);
			XSSFCell celda;
			XSSFRow fila;
			
			LOG.info("FILAS: " + hoja.getPhysicalNumberOfRows());
			
			SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy"); 
			DecimalFormat formatDecimal = new DecimalFormat("#");
			
			String cellValue;
			
			for(int rowIndex = 0; rowIndex < hoja.getLastRowNum(); rowIndex++) {
				LOG.info("FILAS: " + rowIndex);
				fila = hoja.getRow(rowIndex);
				if(fila == null) {
					break;
				}else {
					//LOG.info("CELDAS: " + hoja.getLastRowNum());
					for(int cellIndex = 0; cellIndex < fila.getLastCellNum(); cellIndex++) {
						celda = fila.getCell(cellIndex);
						
						switch (celda.getCellType()) {
						case STRING:
							datosPagos.add(celda.getStringCellValue());
							break;
						case NUMERIC:
							
							if(DateUtil.isCellDateFormatted(celda)){ 
							    cellValue = formatDate.format(celda.getDateCellValue()); 
							}else if(celda.getColumnIndex() == 0 || celda.getColumnIndex()== 3) {
								cellValue = (String)formatDecimal.format(celda.getNumericCellValue());
							}else {
								cellValue = String.valueOf(celda.getNumericCellValue());
							}						
							datosPagos.add(cellValue);
							break;
						case _NONE:
							datosPagos.add(String.valueOf(celda.getNumericCellValue()));
							break;

						default :
						}	
					}
					
					if(fila.getRowNum() > 0 && !datosPagos.isEmpty()) {
						//LOG.info("FILA: " + fila.getRowNum());
						listPagos.add( new Pago (datosPagos.get(0), datosPagos.get(1), datosPagos.get(2), datosPagos.get(3),
													datosPagos.get(4), formatDate.parse(datosPagos.get(5)),
													formatDate.parse(datosPagos.get(6)), (int) Double.parseDouble(datosPagos.get(7)),
													(int) Double.parseDouble(datosPagos.get(8)), (int) Double.parseDouble(datosPagos.get(9)), 
													(int) Double.parseDouble(datosPagos.get(10)), (int) Double.parseDouble(datosPagos.get(11)), 
													datosPagos.get(12), new Date(),
													inputFile, new Date()));
						
					}
					
					datosPagos.clear();	
				}
			}
			
			
			libro.close();
			
			
		}catch(Exception ex) {
			LOG.error("ERROR AL PROCESAR ARCHIVO: " + ex.getMessage());			 
		}
	
		
		return listPagos;
	}
	

}
