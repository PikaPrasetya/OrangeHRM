package Utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelManager {
	
	public FileInputStream fi;
	public FileInputStream fo;
	public XSSFWorkbook wb;
	public XSSFSheet ws;
	public XSSFRow row;
	public XSSFCell cell;
	String path;
	
	public excelManager(String path)
	{
		this.path = path;
	}
	
	public String readAddEmployeeData(String sheet,int rowNum,int colNum) throws IOException
	{
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheet);
		row = ws.getRow(rowNum);
		if(row==null)
		{
			return "";
		}
		cell = row.getCell(colNum);
		String data = (cell == null) ? "":cell.toString();
		wb.close();
		fi.close();
		return data;
	}
	
	public int rowCount(String sheet) throws IOException
	{
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheet);
		int count = 0;
		int rowNum = ws.getLastRowNum();
		for(int i=1;i<=rowNum;i++)
		{
			row = ws.getRow(i);
			if(row != null && row.getCell(0) != null && !row.getCell(0).toString().isBlank())
			{
				count++;
			}
		}
		wb.close();
		fi.close();
		return count;
	}
	
	public int colCount(String sheet,int rowNum) throws IOException
	{
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheet);
		int colNum = ws.getRow(rowNum).getLastCellNum();
		wb.close();
		fi.close();
		return colNum;
	}

}
