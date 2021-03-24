package data;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProvider {

    private XSSFSheet hojaExcel;

    public DataProvider(String pathToFile, String sheetName) {

        XSSFWorkbook excel;

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(pathToFile);
            excel = new XSSFWorkbook(is);
        } catch (Exception e) {
            System.err.println("Error al cargar excel: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error al cargar excel");
        }

        for(int i=0; i< excel.getNumberOfSheets(); i++){
            if(sheetName.equalsIgnoreCase(excel.getSheetName(i))) {
                hojaExcel = excel.getSheetAt(i);
                break;
            }
        }

        if (hojaExcel == null) {
            throw new RuntimeException(String.format("Error al cargar excel, hoja '%s' no fue encontrada", sheetName));
        }
    }

    public List<String> getData(String nameCase) {
        List<String> data = new ArrayList<>();
        Iterator<Row> filas = hojaExcel.iterator();
        Row primeraFila = filas.next();
        Iterator<Cell> celda = primeraFila.cellIterator();

        int k=0;
        int columna =0;
        while(celda.hasNext()){
            Cell celdaSelecciona = celda.next();
            if(celdaSelecciona.getStringCellValue().equalsIgnoreCase("CaseCode")){
                columna = k;
                break;
            }
            k++;
        }

        while(filas.hasNext()){
            Row r = filas.next();

            if(nameCase.equalsIgnoreCase(r.getCell(columna).getStringCellValue())){
                Iterator<Cell> cv = r.cellIterator();

                while(cv.hasNext()){
                    Cell c = cv.next();
                    switch (c.getCellType()){
                        case STRING:
                            data.add(c.getStringCellValue());
                            break;
                        case NUMERIC:
                            data.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        return data;
    }

}
