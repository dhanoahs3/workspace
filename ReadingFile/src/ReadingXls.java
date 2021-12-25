
public class ReadingXls {
	
	public static void main(String[] args) {
		Xls_Reader xls = new Xls_Reader("C:\\FGFG\\selenium\\Data.xlsx");
		int rows=xls.getRowCount("Login");
		System.out.println("Total rows - "+rows);
		int cols=xls.getColumnCount("Login");
		System.out.println("Total cols - "+cols);
		
		String data = xls.getCellData("Login", "Password", 3);
		System.out.println(data);
		 data = xls.getCellData("Login", 0 , 5);
		 System.out.println(data);
		 

	}
	
	
	
}
