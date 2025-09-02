package Utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class dataProviders {
	
	
	@DataProvider(name="New Employee")
	public String[][] newEmployee() throws IOException
	{
		String path = System.getProperty("user.dir")+"\\src\\test\\resources\\OrangeHRMEmployee.xlsx";
		excelManager xl = new excelManager(path);
		int totalRow = xl.rowCount("Employee Info");
		int totalCol = xl.colCount("Employee Info", 0);
		
		String[][] newEmployee = new String[totalRow][totalCol];
		for(int i=0;i<totalRow;i++)
		{
			for(int j=0;j<totalCol;j++)
			{
				newEmployee[i][j] = xl.readAddEmployeeData("Employee Info", i+1, j);
			}
		}
		return newEmployee;
	}
}
