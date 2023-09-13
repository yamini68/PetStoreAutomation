package api.utilities;

import api.utlities.XUtility;
import org.testng.annotations.DataProvider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {

    @DataProvider(name = "Data")
    public Object[][] getAllData() throws IOException {
        String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";
        XUtility xl = new XUtility(path);

        int rownum = xl.getRowCount("Sheet1");
        int colcount = xl.getCellCount("Sheet1", 1);

        Object[][] apidata = new Object[rownum][colcount];

        for (int i = 1; i <= rownum; i++) {
            for (int j = 0; j < colcount; j++) {
                apidata[i - 1][j] = xl.getCellData("Sheet1", i, j);
            }
        }

        return apidata;
    }

    @DataProvider(name = "UserNames")
    public Iterator<Object[]> getUserNames() throws IOException {
        String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";
        XUtility xl = new XUtility(path);

        int rownum = xl.getRowCount("Sheet1");
        List<Object[]> data = new ArrayList<>();

        for (int i = 1; i <= rownum; i++) {
            String username = xl.getCellData("Sheet1", i, 1);
            data.add(new Object[]{username});
        }

        return data.iterator();
    }
}
