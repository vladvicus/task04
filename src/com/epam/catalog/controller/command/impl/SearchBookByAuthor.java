package com.epam.catalog.controller.command.impl;

import com.epam.catalog.bean.Book;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.BookService;
import com.epam.catalog.service.exception.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

import java.util.List;


public class SearchBookByAuthor implements Command {

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
        String author = null;
        try {
            author = arr[1];
        } catch (NumberFormatException e) {
            System.out.println("Illegal format for parameter" + e);
            return null;
        }

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookService bookService = serviceFactory.getBookService();
        List<Book> booksFoundByAuthor = null;
        try {
            booksFoundByAuthor = bookService.findBooksByAuthor(author);

        } catch (ServiceException e) {

            // write log
            System.out.println(MESSAGE_EXECUTE + e);
        }

        return booksFoundByAuthor;

    }
}
