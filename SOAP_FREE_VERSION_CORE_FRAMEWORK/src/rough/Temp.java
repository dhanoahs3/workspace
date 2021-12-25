package rough;

import com.soapuitutorial.util.Xls_Reader;
import com.soapuitutorial.util.XLUtil;

public class Temp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Xls_Reader xls = new Xls_Reader("C:\\testing\\Soap reporting\\Test Data.xlsx");
        
		
		XLUtil.getTestData("SuiteA","TestCaseA1", xls);
        
		XLUtil.getTestData("SuiteA","TestCaseA2", xls);


		
		
	}

}
