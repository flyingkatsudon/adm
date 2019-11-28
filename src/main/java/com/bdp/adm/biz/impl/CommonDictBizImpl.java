package com.bdp.adm.biz.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.bdp.adm.biz.CommonDictBiz;
import com.bdp.adm.dao.CommonDictDAO;
import com.bdp.adm.util.CheckPwd;
import com.bdp.adm.vo.UserVO;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

public class CommonDictBizImpl implements CommonDictBiz {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonDictBizImpl.class);
	
	@Autowired
	private CommonDictDAO commonDictDAO;
	
	@Override
	public void exportXlsx(int tableNo, HttpServletResponse response) throws Exception {

		String columnsQuery = "SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'shinhan' AND TABLE_NAME = ";
		String dataQuery = "SELECT * FROM ";
		
		String[] tableList = {"std.taxonomy_std_info", "std.stopword_info", "dw.tkn_dict_info", "std.trend_keywords_std_info", "std.doc_filter_info", "dw.ewd_info", "std.crawl_keywords_std_info"};
		
		columnsQuery += "'" + tableList[tableNo] + "'";
		dataQuery += "shinhan.`" + tableList[tableNo] + "`";
		
		System.out.println(columnsQuery);
		System.out.println(dataQuery);

		ArrayList<Object> colMapList = new ArrayList<Object>(); // 각 column명을 Map<String, String>에 저장한 리스트 
		ArrayList<Object> columns = new ArrayList<Object>(); // column명 리스트에 저장
		
		// db에서 컬럼명 가져오는 쿼리 실행
		colMapList = commonDictDAO.getColumnNames(columnsQuery); 

		// column명 리스트에 저장
		for (Object o : colMapList) {
			Map<String, String> t = (Map<String, String>) o;
			logger.debug("columns -> {}", t);
			columns.add(t.get("COLUMN_NAME"));
		}

		// db에서 데이터 가져옴
		ArrayList<Object> rows = new ArrayList<Object>(); // 데이터 리스트
		rows = commonDictDAO.getList(dataQuery);
		
		// Create a Workbook
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        /* CreationHelper helps us create instances of various things like DataFormat, 
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way*/ 
        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();
        
        // Create a Sheet
        Sheet sheet = workbook.createSheet("sheet");

        // Create a Row
        Row headerRow = sheet.createRow(0);

        // Create cells
        for(int i = 0; i < columns.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(String.valueOf(columns.get(i)));
        }

        // Create Other rows and cells with employees data
        // list: 데이터 row, columns: column 명 list
        // headerRow가 있으니 row는 1번부터 데이터 채움
        for(int i=0; i<rows.size(); i++) {
            Row row = sheet.createRow(i+1);
            
        	Map<String, String> data = (Map<String, String>) rows.get(i);

            for(int j=0; j<columns.size(); j++) {	
            	logger.debug("{}", data);

                Cell cell = row.createCell(j);
                cell.setCellValue(String.valueOf(data.get(columns.get(j))));

                String cellValue = dataFormatter.formatCellValue(cell);
 	            cell.setCellValue(cellValue);
            }
            System.out.println("");
        }

		// Resize all columns to fit the content size
        for(int i = 0; i < columns.size(); i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        File file = new File(tableList[tableNo] + "_export.xlsx");
        logger.debug("file -> {}", file);
                
        response.setHeader("Set-Cookie", "fileDownload=true; path=/");
        //response.setHeader("Set-Cookie", "fileDownload=true; path=" + "C:\\Users\\Jeremy\\Downloads");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getPath() + "\";"); 
        workbook.write(response.getOutputStream());

        // Closing the workbook
        workbook.close();

	}
	
	public File convert(MultipartFile multipartFile) throws Exception {
		
		File convFile = new File(multipartFile.getOriginalFilename());
		convFile.createNewFile();
	    FileOutputStream fos = new FileOutputStream(convFile);
	    fos.write(multipartFile.getBytes());
	    fos.close();
		
	    return convFile;
	}
	
	public ArrayList<Object> xlsxIterator(File file) throws Exception {
		ArrayList<Object> list = new ArrayList<Object>();
		ArrayList<String> columns = new ArrayList<String>(); // field name
		
		// Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook = WorkbookFactory.create(file);

        // Retrieving the number of sheets in the Workbook
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

        // 1. You can obtain a sheetIterator and iterate over it
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        System.out.println("Retrieving Sheets using Iterator");
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            System.out.println("=> " + sheet.getSheetName());
        }
        
        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);
        
        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        // 1. You can obtain a rowIterator and columnIterator and iterate over them
        System.out.println("\n\nIterating over Rows and Columns using Iterator\n");
        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
        	Map<String, String> map = new HashMap<String, String>();
        	
            Row row = rowIterator.next();

            int rowNum = row.getRowNum();
            // Now let's iterate over the columns of the current row
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                String cellValue = dataFormatter.formatCellValue(cell);
                
            	if (rowNum == 0) {
            		columns.add(String.valueOf(cellValue));
            	} else {
            		map.put(columns.get(cell.getColumnIndex()), cellValue);
            	}
            }
            
            if (rowNum != 0) 
            	list.add(map);
        }

        // Closing the workbook
        workbook.close();
        
        logger.debug("columns -> {}", columns);
        logger.debug("list -> {}", list);
        list.add(new Integer(list.size()));
        int insertRes = this.insertData(list, columns, sheet.getSheetName());
		
        list.add(new Integer(insertRes));
        //if (insertRes == 0) return null;

        return list;
	}

	@Override
	public ArrayList<Object> importXlsx(MultipartFile[] multipartFiles) throws Exception {
		
		ArrayList<Object> result = new ArrayList<Object>();
		
		for (MultipartFile file : multipartFiles) {
			List<Object> resultList = xlsxIterator(convert(file));
			if (resultList == null) resultList = new ArrayList<Object>();
			result.add(resultList);
		}
        
        return result;
    }

	@Override
	public int insertData(ArrayList<Object> list, ArrayList<String> columns, String tableName) {
		int resCnt = 0;
		columns.add("load_name");
		columns.add("load_date");
		String cols = String.join(",", columns);
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		UserVO curUser = (UserVO)attr.getRequest().getSession().getAttribute("user");
		
		if (curUser == null) curUser = new UserVO();
		
		String loadName = curUser.getUniqueHash();
		
		for (Object i : list) {
			try {
				
				HashMap<String, String> maps = (HashMap<String, String>) i;
				List<String> values = new ArrayList<String>();
				List<String> eqls = new ArrayList<String>();
				for (String colName : columns) {
					String curValue = maps.get(colName);
					if (curValue == null) curValue = "";
					if (colName.equals("load_name")) curValue = loadName;
					if (colName.equals("load_date")) curValue = "now()";
		
					if (!colName.equals("load_date")) {
						values.add("'" + curValue + "'");
					} else {
						values.add(curValue);
					}
					
					String tmpVal = colName +"='" + curValue + "'";
					eqls.add(tmpVal);
				}
				
				String paramStr  = String.join(",", values);
				String paramStr2 = String.join(",", eqls);
				System.out.println(paramStr);
				String insertQuery = "INSERT INTO " + tableName + " (" + cols + ") VALUES (" + paramStr + ") ";
						//+ "ON DUPLICATE KEY UPDATE " + paramStr2;
				resCnt += commonDictDAO.insertData(insertQuery);
			} catch(Exception e) {
				e.getStackTrace();
				logger.debug("{} - INSERT FAILED SKIP ", e.getMessage());
				continue;
			}
		}
		System.out.println("### " + resCnt + " row(s) inserted");
		return resCnt;
		
	}
}
