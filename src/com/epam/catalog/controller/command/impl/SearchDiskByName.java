package com.epam.catalog.controller.command.impl;

import com.epam.catalog.bean.Disk;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.DiskService;
import com.epam.catalog.service.exception.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

import java.util.List;

public class SearchDiskByName implements Command {

    @Override
    public List<?> execute(String request) {

        request = request.replaceAll("\\s{2,}", " ");
        System.out.println(request);
        String[] arr = request.split(",");
        if (arr.length == 1) return null;
        for (String element : arr) {
            element.trim();
            System.out.println(element);
        }
        String name = arr[1];
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        DiskService diskService = serviceFactory.getDiskService();
        List<Disk> disksFoundByName = null;
        try {
            disksFoundByName = diskService.findDisksByName(name);
        } catch (ServiceException e) {
            // write log
            System.out.println(MESSAGE_EXECUTE + e);
        }
        return disksFoundByName;
    }

}
