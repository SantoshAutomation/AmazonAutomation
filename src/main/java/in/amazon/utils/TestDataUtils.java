package in.amazon.utils;

import java.util.ArrayList;

public class TestDataUtils {
    static ExcelReader reader;

    public static ArrayList<Object[]> getDataFromExcel() {
        ArrayList<Object[]> data = new ArrayList<>();
        reader = new ExcelReader(System.getProperty("user.dir") + "\\src\\main\\resources\\testData\\Sanity_TestData.xlsx");

        for (int rowNum = 2; rowNum <= reader.getRowCount("ProductDetails"); rowNum++) {

            String category = reader.getCellData("ProductDetails", "category", rowNum);
            String product = reader.getCellData("ProductDetails", "product", rowNum);
            String brand = reader.getCellData("ProductDetails", "brand", rowNum);
            String featured = reader.getCellData("ProductDetails", "featured", rowNum);

            data.add(new Object[]{category, product, brand, featured});
        }
        return data;
    }
}
