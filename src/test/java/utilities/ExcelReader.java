package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

public class ExcelReader {

	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFCell cell;
	public static XSSFRow row;
	
	 public static String filePath = System.getProperty("user.dir")+"\\src\\test\\java\\utilities\\TestData.xlsx";
	
    public static List<orderEntryTestData> readExcelFile(String filePath) {
        List<orderEntryTestData> orderList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getPhysicalNumberOfRows();

            for (int i = 1; i < rowCount; i++) { // Starting from 1 to skip header row
                Row row = sheet.getRow(i);
                if (row != null) {
                	orderEntryTestData order = new orderEntryTestData();
                	order.setStudySource(row.getCell(0).getStringCellValue());
                	order.setPatientLocation(row.getCell(1).getStringCellValue());
//                    person.set(row.getCell(0).getStringCellValue());
//                    person.setAge((int) row.getCell(1).getNumericCellValue());
//                    person.setEmail(row.getCell(2).getStringCellValue());

                	orderList.add(order);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return orderList;
    }

 
    
    
    
    
    
    public static orderEntryTestData readOrderEntryData(String sheetName) {
        orderEntryTestData order = null;
        
            
			try {
				FileInputStream fis = new FileInputStream(filePath);
				  Workbook workbook = new XSSFWorkbook(fis);;	
         

				 Sheet sheet = workbook.getSheet(sheetName);
                int rowCount = sheet.getPhysicalNumberOfRows();

            // Starting from 1 to skip header row
                Row row = sheet.getRow(1);
               short totalCellCount=  row.getLastCellNum();
              
                if (row != null) {
                	order = new orderEntryTestData();
                   
//                	order.setStudySource(row.getCell(0).getStringCellValue());
////                	order.setTntGroup(row.getCell(1).getStringCellValue());
////                	order.setAdditionFaxNumber((int) row.getCell(2).getNumericCellValue());
////                	order.setRequisitionTime(row.getCell(3).getStringCellValue());
//                	order.setPhysician(row.getCell(4).getStringCellValue());
////                	order.setAlternatePhysician(row.getCell(5).getStringCellValue());
//                	
//                	order.setCriticality(row.getCell(6).getStringCellValue());
//                	order.setInsuranceType(row.getCell(7).getStringCellValue());
//                	order.setPatientLocation(row.getCell(8).getStringCellValue());
////                	order.setProviderPhoneNumber((int) row.getCell(9).getNumericCellValue());
//                	order.setMRNnumber(row.getCell(10).getStringCellValue());
//                	order.setFirstName(row.getCell(11).getStringCellValue());
//                	order.setLastName(row.getCell(12).getStringCellValue());
//                	 order.setAge((int) row.getCell(13).getNumericCellValue());
//                	order.setModality(row.getCell(14).getStringCellValue());
//                	
//                	order.setSelectProcedure(row.getCell(15).getStringCellValue());
//                	
//                	order.setImagecount((int) row.getCell(16).getNumericCellValue());
//                	order.setClinicalHistory(row.getCell(17).getStringCellValue());
//                	order.setGender(row.getCell(18).getStringCellValue());
                	
                	order.setRadioButton(row.getCell(0).getStringCellValue());
                	order.setPatientLocation(row.getCell(1).getStringCellValue());
                	order.setLastName(row.getCell(2).getStringCellValue());
                	order.setImagecount((int) row.getCell(3).getNumericCellValue());
                	order.setSelectProcedure(row.getCell(4).getStringCellValue());
                	order.setClinicalHistory(row.getCell(5).getStringCellValue());
                	

                	
                }
   
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        return order;
    }
    
    
    public static WorkflowTestData readWorklistData(String sheetName) {
		
    	WorkflowTestData workflowData=null;
    	
		try {
			FileInputStream fis = new FileInputStream(filePath);
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet(sheetName);
			
		    int rowCount = sheet.getPhysicalNumberOfRows();
		    
		    // Starting from 1 to skip header row
		     Row row = sheet.getRow(1);
		     short totalCellCount=  row.getLastCellNum();
		     
		     if (row != null) {
		    	 workflowData = new WorkflowTestData();
		    	 
		    	 workflowData.setTotalReads(row.getCell(0).getNumericCellValue());
		    	 workflowData.setReportType(row.getCell(1).getStringCellValue());
		    	 workflowData.setRad(row.getCell(2).getStringCellValue());
		    	 workflowData.setRAD2(row.getCell(3).getStringCellValue());
		    	 workflowData.setReportingText(row.getCell(4).getStringCellValue());
		    	 workflowData.setReportingText2(row.getCell(5).getStringCellValue());
		    	 workflowData.setDTextBox(row.getCell(7).getStringCellValue());
		    	 workflowData.setDComments(row.getCell(8).getStringCellValue());
		    	 workflowData.setPeerReview(row.getCell(10).getStringCellValue());
		    	 
		     }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
    	return workflowData;
    	
    }

    public static  modalityTestData readModalitySimulator(String sheetname) {
    	modalityTestData ModalitySimulator=null;
    	try {
    		FileInputStream fis = new FileInputStream(filePath);
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet(sheetname);
			
		    int rowCount = sheet.getPhysicalNumberOfRows();
		    
		    // Starting from 1 to skip header row
		     Row row = sheet.getRow(1);
		     short totalCellCount=  row.getLastCellNum();
		     if (row!=null) {
				ModalitySimulator=new modalityTestData();
				 
				ModalitySimulator.setSimulatorMode(row.getCell(0).getStringCellValue());
				ModalitySimulator.setCT(row.getCell(1).getStringCellValue());
				ModalitySimulator.setMR(row.getCell(2).getStringCellValue());
				ModalitySimulator.setUS(row.getCell(3).getStringCellValue());
				ModalitySimulator.setCR(row.getCell(4).getStringCellValue());
				ModalitySimulator.setAETitle(row.getCell(5).getStringCellValue());
				ModalitySimulator.setIPAddress(row.getCell(6).getStringCellValue());
				ModalitySimulator.setPort(row.getCell(7).getNumericCellValue());
				ModalitySimulator.setPatientName(row.getCell(8).getStringCellValue());
				ModalitySimulator.setPatientID(row.getCell(9).getStringCellValue());
				ModalitySimulator.setInstitutionName(row.getCell(10).getStringCellValue());
				ModalitySimulator.setAge(row.getCell(11).getNumericCellValue());
				ModalitySimulator.setGender(row.getCell(12).getStringCellValue());
				ModalitySimulator.setStudyDescription(row.getCell(13).getStringCellValue());
				ModalitySimulator.setStudyComments(row.getCell(14).getStringCellValue());
//				ModalitySimulator.setRefemingPhysician(row.getCell(12).getStringCellValue());
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ModalitySimulator;
    	
    }
    
    public static int getRowCount(String xlFileName, String xlSheetName) throws IOException {
		
		fi =new FileInputStream(xlFileName);
		
		wb=new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheetName);
		int rowCount=ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;
	 }

    public static int getCellCount(String xlFileName,String xlSheetName, int rownum) throws IOException {
		
        fi =new FileInputStream(xlFileName);
 		
 		wb=new XSSFWorkbook(fi);
 		ws = wb.getSheet(xlSheetName);
 		row = ws.getRow(rownum);
 		int cellCount = row.getLastCellNum();
 		wb.close();
 		fi.close();
 		
 		
 		return cellCount;
 	}
 	
 	
 	public static String getCelldata(String xlFileName,String xlSheetName, int rownum, int colnum) throws IOException {
 		
 		 
 		    fi =new FileInputStream(xlFileName);
 			
 			wb=new XSSFWorkbook(fi);
 			ws = wb.getSheet(xlSheetName);
 			row = ws.getRow(rownum);
 		    cell=row.getCell(colnum);
 		    
 		    String data;
 		    
 		    
 		    try {
 		    	
 		    	DataFormatter format = new DataFormatter();
 		    	String celldata = format.formatCellValue(cell);
 		    	return celldata;
 		    }catch(Exception e) {
 		    	
 		    	data="";
 		    }
 		    wb.close();
 		    fi.close();
 		
 		return data;
 	}
 	
 	
 	public static List<Map<String, String>> getOrderData(String xlFileName, String xlSheetName ) throws IOException{
		
 		List<Map<String, String>> listOfOrder = new ArrayList<>();
 		
 		
 
 		
 	
        fi =new FileInputStream(xlFileName);
		
		wb=new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheetName);
		int rowCount=ws.getLastRowNum();
		row = ws.getRow(rowCount);
		int cellCount = row.getLastCellNum();
 		
		
		DataFormatter format = new DataFormatter();
	    String celldata = format.formatCellValue(cell);
		
	    Iterator<Row> rowIterator= ws.iterator();
	    Row headerRow = rowIterator.next();
	    
	    
	    for(int columCount=0; columCount<=cellCount; columCount++) {
	    	Map<String, String> orderEntryData=new HashMap<String, String>(); 
	    	
	    	String header=ws.getRow(0).getCell(columCount).getStringCellValue();
	    	String testData=ws.getRow(1).getCell(columCount).getStringCellValue();
	    	orderEntryData.put(header, testData);
	    	listOfOrder.add(orderEntryData);
	    	
	    }
	    
	    
 		return listOfOrder;
 		
 		
 	}
 	
 	
 	
    public static void main(String[] args) throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\java\\utilities\\TestData.xlsx";
//        List<orderEntryTestData> persons =  readExcelFile(filePath);
//        persons.forEach(System.out::println);
//        
//        
          orderEntryTestData orderdata= readOrderEntryData("hosptalInfo");
//           System.out.println(orderdata);
//        
        
//        List<Map<String, String>> list=  getOrderData(filePath,"hosptalInfo");
//        
//       System.out.println(list.get(0).get("PatientLocation"));
//        
//        if (!list.isEmpty()) {
//            System.out.println(list.get(0)); // Print first row
//        } else {
//            System.out.println("No data found.");
//        }
    }
    
    public static List<String> getListOfData(String sheetName){
    	
    	
    	

        try {
			fi =new FileInputStream(filePath);
			wb=new XSSFWorkbook(fi);
			ws = wb.getSheet(sheetName);
			int rowCount=ws.getLastRowNum();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
    	
    	return null;
    }
    
 	
}

