package Utilities;

	import java.io.FileInputStream;
	//import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
	//import org.apache.poi.xssf.usermodel.XSSFCell;
	//import org.apache.poi.xssf.usermodel.XSSFRow;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.collect.Table.Cell;
	 
	public class ExcelUtils {
		public static FileInputStream fi;
		public static FileOutputStream fo;
		public static XSSFWorkbook wb;
		public static XSSFSheet ws;
		public static XSSFRow row;
		public static XSSFCell cell;
		public static CellStyle style;  
		public static String[] readExcelData(String sheet1Name) throws IOException{ 

	        String path = System.getProperty("user.dir")+"\\testData\\data.xlsx";
	        FileInputStream fi = new FileInputStream(path);
	        XSSFWorkbook wb = new XSSFWorkbook(fi);
	        XSSFSheet ws = wb.getSheet(sheet1Name);        
	        XSSFRow r = ws.getRow(0);
	        int c_no = r.getLastCellNum();
	        String Data[] = new String[c_no];
	        for(int j=0; j<c_no; j++)
	        {
	            Data[j] = String.valueOf(r.getCell(j));
	        }
	        System.out.println(Data);
	        return Data;
	    }
	 
		public static void setCellData(String xlfile,String xlsheet,int rownum,int colnum,String data) throws IOException
		{
			fi=new FileInputStream(xlfile);
			wb=new XSSFWorkbook(fi);
			ws=wb.getSheet(xlsheet);
			row=ws.getRow(rownum);
			CellStyle wrapCellStyle=wb.createCellStyle();
			wrapCellStyle.setWrapText(true);
			{
				row=ws.createRow(rownum);
			}
			//row=ws.getRow(rownum);
			cell=row.createCell(colnum);
			cell.setCellValue(data);
			cell.setCellStyle( wrapCellStyle);
			ws.autoSizeColumn(colnum);
			fo=new FileOutputStream(xlfile);
			wb.write(fo);		
			wb.close();
			fi.close();
			fo.close();
		}
		
		 
	}


