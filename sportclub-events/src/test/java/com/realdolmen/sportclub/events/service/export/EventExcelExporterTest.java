package com.realdolmen.sportclub.events.service.export;

import com.realdolmen.sportclub.common.entity.Address;
import com.realdolmen.sportclub.common.entity.Guest;
import com.realdolmen.sportclub.common.entity.RegisteredUser;
import com.realdolmen.sportclub.common.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventExcelExporterTest {
    @Test
    public void canExportListOfUsers() throws Exception {
        List<User> users = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            users.add(new Guest());
        }
        for(int i = 0; i < 3; i++) {
            RegisteredUser u = new RegisteredUser();
            u.setDateOfBirth(LocalDate.now().minusYears(20));
            u.setAddress(new Address());
        }
        byte[] bytes = EventExcelExporter.export(users);
        Assert.assertTrue(bytes.length > 0);
    }
}
