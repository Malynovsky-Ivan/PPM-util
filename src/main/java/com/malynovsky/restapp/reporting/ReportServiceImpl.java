package com.malynovsky.restapp.reporting;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    @Override
    public Workbook buildExcel(List<? extends Reportable> data) {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Report");

        int rowNumber = 1;
        for (Reportable rowData : data) {
            Row row = sheet.createRow(rowNumber);

            List<ReportItem> items = rowData.getData();
            for (int i = 0; i < items.size(); i++) {
                Cell cell = row.createCell(i, items.get(i).getType().getType());
                items.get(i).getType().setValue(workbook.getCreationHelper(), cell, items.get(i).getValue());
            }
            rowNumber++;
        }

        return workbook;
    }

    @Override
    public Workbook buildExcel(Map<Integer, List<? extends Reportable>> data) {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Report");

        int rowNumber = 1;
        for (Integer age : data.keySet().stream().sorted().collect(Collectors.toList())) {
            Row row = sheet.createRow(rowNumber);
            Cell cell = row.createCell(1, CellType.NUMERIC);
            cell.setCellValue(age);

            for (Reportable rowData : data.get(age)) {
                row = sheet.createRow(rowNumber);

                List<ReportItem> items = rowData.getData();
                for (int i = 0; i < items.size(); i++) {
                    cell = row.createCell(i, items.get(i).getType().getType());
                    items.get(i).getType().setValue(workbook.getCreationHelper(), cell, items.get(i).getValue());
                }
                rowNumber++;
            }
        }

        return workbook;
    }
}
