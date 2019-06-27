package demo;

import commonLibs.utils.ExcelDriver;

public class DemoExcelDriver {

	public static void main(String[] args) {

		try {
			String filename = "C:/Users/Saurabh Dhingra/git/agama-frameworks-20062019/modularFramework-20062019/testData/TestData.xlsx";
			String sheetname = "Test Data";
			ExcelDriver excelDriver = new ExcelDriver();

			excelDriver.createWorkbook(filename);

			excelDriver.openWorkbook(filename);

			excelDriver.createSheet(sheetname);

			excelDriver.setCellData(sheetname, 0, 1, "Saurabh");

			excelDriver.setCellData(sheetname, 0, 2, "Dhingra");

			excelDriver.setCellData(sheetname, 1, 1, "Saurabh");

			excelDriver.setCellData(sheetname, 1, 2, "Dhingra");

			excelDriver.setCellData(sheetname, 2, 1, "Gaurav");

			excelDriver.setCellData(sheetname, 2, 2, "Yadav");

			excelDriver.saveFile();

			excelDriver.closeWorkbook();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
