package utils;

import org.testng.annotations.DataProvider;

import commonLibs.utils.ExcelDriver;

public class TestDataProvider {

	private ExcelDriver excelDriver;

	@DataProvider
	public Object[][] getData() throws Exception {

		String excelWorkbookName;
		String excelsheetname;

		excelWorkbookName = System.getProperty("user.dir") + "/testData/TestData.xlsx";
		excelsheetname = "Test Data";

		excelDriver = new ExcelDriver();

		excelDriver.openWorkbook(excelWorkbookName);

		Object[][] data;

		int rowCount = excelDriver.getRowCount(excelsheetname);

		int cellCount = excelDriver.getCellCountFromARow(excelsheetname, 0);

		data = new Object[rowCount + 1][cellCount];

		for (int row = 0; row <= rowCount; row++) {
			for (int cell = 0; cell < cellCount; cell++) {
				
				data[row][cell] = excelDriver.getCellData(excelsheetname, row, cell);
			
			}
		}

		return data;
	}
}
