package com.realdolmen.sportclub.events.service.export;

import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.common.entity.RegisteredUser;
import com.realdolmen.sportclub.common.entity.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class EventExcelExporter {
    public static byte[] export(List<User> attendees) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Aanwezigen");
        List<Object[]> data = new ArrayList<>();
        // Headers
        data.add(new Object[]{"Voornaam", "Naam", "Geboortedatum", "Straat", "Nummer", "Postcode", "Stad", "Land", "Extra informatie"});
        for (User user : attendees) {
            if (user instanceof RegisteredUser) {
                RegisteredUser ru = (RegisteredUser) user;
                data.add(new Object[]{
                        user.getFirstName(),
                        user.getLastName(),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy").format(ru.getDateOfBirth()),
                        ru.getAddress().getStreet(),
                        ru.getAddress().getHomeNumber(),
                        ru.getAddress().getPostalCode(),
                        ru.getAddress().getCity(),
                        ru.getAddress().getCountry(),
                        Objects.toString(ru.getNonEditableField(), "")});
            } else { // Guests have less information
                data.add(new Object[]{user.getFirstName(), user.getLastName(), "", "", "", "", "", "", ""});
            }
        }

        int rowNum = 0;

        for (Object[] entry : data) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object field : entry) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                }
            }
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
        return outputStream.toByteArray();
    }
}
