// 代码生成时间: 2025-08-25 09:47:24
package com.yourcompany.excelgenerator;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.enterprise.context.ApplicationScoped;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * ExcelGenerator类，用于生成Excel表格文件。
 */
@QuarkusMain
@ApplicationScoped
public class ExcelGenerator {

    // 主方法，用于启动程序
    public static void main(String... args) {
        ExcelGenerator generator = new ExcelGenerator();
        generator.generateExcelFile();
    }

    // 生成Excel文件的方法
    public void generateExcelFile() {
        try {
            Workbook workbook = new XSSFWorkbook(); // 创建工作簿
            Sheet sheet = workbook.createSheet("Example Sheet"); // 创建工作表

            // 填充数据到工作表
            Row row = sheet.createRow(0); // 创建行
            row.createCell(0).setCellValue("Header 1"); // 单元格1
            row.createCell(1).setCellValue("Header 2"); // 单元格2

            // 更多的数据填充可以在这里添加

            // 写入文件
            try (FileOutputStream fileOut = new FileOutputStream("Example.xlsx")) {
                workbook.write(fileOut);
            } catch (IOException e) {
                throw new RuntimeException("Failed to write Excel file", e);
            } finally {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to create Excel file", e);
        }
    }
}
