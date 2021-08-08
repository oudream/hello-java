package hello.poi.excel;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.*;

class SimpleA {
    public static final String WEDO_REDIS_2_KAFKA_FP = "/userdata/wedo-redis-2-kafka.xlsx";

    public static void helloReader2() {
        try {
            List<DotTableItem> dis = loadDotTable();
            Map<String, Map<String, Object>> disMaps = convertJsonMap(dis);
            for (Map.Entry<String, Map<String, Object>> entry : disMaps.entrySet()) {
                Map<String, Object> disMap = entry.getValue();
                JSONObject jo = new JSONObject(disMap);
                System.out.println(jo.toJSONString());
            }
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     "devAccessNo": "3500000069",
     "devices": [
     {
     "deviceId": "35011001000027833500000069",
     "time": "2021-08-5 15:00:00",
     "dataType": 1,
     "data": {
     * @param dis
     * @return
     */
    private static Map<String, Map<String, Object>> convertJsonMap(List<DotTableItem> dis) {
        Map<String, Map<String, Object>> disMap = new HashMap<>();
        for (DotTableItem di : dis) {
            String sDevAccessNo = di.getDevAccessNo();
            String sDeviceId = di.getDeviceId();
            String sAttrName = di.getAttrName();
            Object attrValue = di.getAttrValue();
            Map<String, Object> devAccessNo = disMap.get(di.getDevAccessNo());
            List<Map<String, Object>> devices;
            if (devAccessNo == null) {
                devAccessNo = new HashMap<>();
                devAccessNo.put("devAccessNo", di.getDevAccessNo());
                devices = new ArrayList<>();
                devAccessNo.put("devices", devices);
                disMap.put(di.getDevAccessNo(), devAccessNo);
            } else {
                devices = (List<Map<String, Object>>) devAccessNo.get("devices");
            }
            Map<String, Object> device = null;
            Map<String, Object> data = null;
            for (Map<String, Object> d : devices) {
                if (d.containsValue(sDeviceId)) {
                    device = d;
                    data = (Map<String, Object>) d.get("data");
                    break;
                }
            }
            if (device == null) {
                device = new HashMap<>();
                device.put("deviceId", sDeviceId);
                DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.CHINA);
                device.put("time", df.format(new Date()));
                device.put("dataType", 1);
                data = new HashMap<>();
                device.put("data", data);
                devices.add(device);
            }
            data.put(sAttrName, attrValue);
        }
        return disMap;
    }

    /**
     *
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    public static List<DotTableItem> loadDotTable() throws IOException, InvalidFormatException {
        List<DotTableItem> r = new ArrayList<>();

        // Creating a Workbook from an Excel file (.xls or .xlsx)
        FileInputStream fis = new FileInputStream(WEDO_REDIS_2_KAFKA_FP);
        Workbook workbook = WorkbookFactory.create(fis);
//        Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));

        // Retrieving the number of sheets in the Workbook
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

        /*
           =============================================================
           Iterating over all the sheets in the workbook (Multiple ways)
           =============================================================
        */

        // 1. You can obtain a sheetIterator and iterate over it
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        System.out.println("Retrieving Sheets using Iterator");
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            System.out.println("=> " + sheet.getSheetName());
        }

        // 2. Or you can use a for-each loop
        System.out.println("Retrieving Sheets using for-each loop");
        for (Sheet sheet : workbook) {
            System.out.println("=> " + sheet.getSheetName());
        }

        // 3. Or you can use a Java 8 forEach wih lambda
        System.out.println("Retrieving Sheets using Java 8 forEach with lambda");
        workbook.forEach(sheet -> {
            System.out.println("=> " + sheet.getSheetName());
        });

        /*
           ==================================================================
           Iterating over all the rows and columns in a Sheet (Multiple ways)
           ==================================================================
        */

        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        int iRow = 0;
        int noIndex = -1;
        int redisStringIndex = -1;
        int attrValueIndex = -1;
        int deviceNameIndex = -1;
        int deviceIdIndex = -1;
        int devAccessNoIndex = -1;
        int attrNameIndex = -1;
        // 序号	Redis STRING	Value	实际转换设备名称	设备编号	接入号	实际转换变量名称
        String noTitle = "序号";
        String redisStringTitle = "Redis";
        String attrValueTitle = "Value";
        String deviceNameTitle = "实际转换设备名称";
        String deviceIdTitle = "设备编号";
        String devAccessNoTitle = "接入号";
        String attrNameTitle = "实际转换变量名称";
        // 2. Or you can use a for-each loop to iterate over the rows and columns
        System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
        for (Row row : sheet) {
            if (iRow == 0) {
                int iCol = 0;
                for (Cell cell : row) {
                    String cellValue = dataFormatter.formatCellValue(cell);
                    if (cellValue.contains(noTitle)) {
                        noIndex = iCol;
                    }
                    if (cellValue.contains(redisStringTitle)) {
                        redisStringIndex = iCol;
                    }
                    if (cellValue.contains(attrValueTitle)) {
                        attrValueIndex = iCol;
                    }
                    if (cellValue.contains(deviceNameTitle)) {
                        deviceNameIndex = iCol;
                    }
                    if (cellValue.contains(deviceIdTitle)) {
                        deviceIdIndex = iCol;
                    }
                    if (cellValue.contains(devAccessNoTitle)) {
                        devAccessNoIndex = iCol;
                    }
                    if (cellValue.contains(attrNameTitle)) {
                        attrNameIndex = iCol+1;
                    }
                    iCol++;
                }
                iRow++;
                continue;
            }
            if (redisStringIndex < 0 || deviceIdIndex < 0 || devAccessNoIndex < 0 || attrNameIndex < 0) {
                System.out.println("ERROR ! ERROR ! ERROR ! : " + row.toString());
            }
            DotTableItem item = new DotTableItem();
            if (noIndex > -1) {
                String cellValue = dataFormatter.formatCellValue(row.getCell(noIndex));
                item.setNo(cellValue);
            }
            if (redisStringIndex > -1) {
                String cellValue = dataFormatter.formatCellValue(row.getCell(redisStringIndex));
                item.setRedisString(cellValue);
            }
            if (attrValueIndex > -1) {
                String cellValue = dataFormatter.formatCellValue(row.getCell(attrValueIndex));
                item.setAttrValue(cellValue);
            }
            if (deviceNameIndex > -1) {
                String cellValue = dataFormatter.formatCellValue(row.getCell(deviceNameIndex));
                item.setDeviceName(cellValue);
            }
            if (deviceIdIndex > -1) {
                String cellValue = dataFormatter.formatCellValue(row.getCell(deviceIdIndex));
                item.setDeviceId(cellValue);
            }
            if (devAccessNoIndex > -1) {
                String cellValue = dataFormatter.formatCellValue(row.getCell(devAccessNoIndex));
                item.setDevAccessNo(cellValue);
            }
            if (attrNameIndex > -1) {
                String cellValue = dataFormatter.formatCellValue(row.getCell(attrNameIndex));
                item.setAttrName(cellValue);
            }
            r.add(item);
            iRow++;
        }

        // Closing the workbook
//        workbook.close();
        fis.close();
        return r;
    }

    private static void printCellValue(Cell cell) {
        switch (cell.getCellTypeEnum()) {
            case BOOLEAN:
                System.out.print(cell.getBooleanCellValue());
                break;
            case STRING:
                System.out.print(cell.getRichStringCellValue().getString());
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    System.out.print(cell.getDateCellValue());
                } else {
                    System.out.print(cell.getNumericCellValue());
                }
                break;
            case FORMULA:
                System.out.print(cell.getCellFormula());
                break;
            case BLANK:
                System.out.print("");
                break;
            default:
                System.out.print("");
        }

        System.out.print("\t");
    }

    public static void main(String args[]) {
        System.out.println("begin:");

        try {
            SimpleA.helloReader2();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("end.");
    }

}