package com.malynovsky.restapp.reporting;

import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;
import java.util.Map;

public interface ReportService {

    Workbook buildExcel(List<? extends Reportable> data);

    Workbook buildExcel(Map<Integer, List<? extends Reportable>> data);
}
