package com.evan.wj.conrtoller;

import com.evan.wj.pojo.Book;
import com.evan.wj.service.BookService;
import com.evan.wj.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class LibraryController {

    @Autowired
    private BookService bookService;

    @GetMapping("/api/books")
    public List<Book> list(){
        return bookService.list();
    }

    @PostMapping("/api/books")
    public Book addOrUpdate(@RequestBody Book book){
        bookService.addOrUpdate(book);
        return book;
    }

    @PostMapping("/api/delte")
    public void deletById(@RequestBody Book book){
        bookService.deleteById(book.getId());
    }

    @GetMapping("/api/categories/{cid}/books")
    public List<Book> listByCategory(@PathVariable("cid") int cid){
        if (0 != cid){
            return bookService.listByCategory(cid);
        }else {
            return list();
        }
    }

    @CrossOrigin
    @PostMapping("/api/covers")
    public String coversUpload(MultipartFile fiel){
        String folder = "D:/workspace/img";
        File imgFolder = new File(folder);
        MultipartFile file = null;
        String imageFolder = null;
        File f = new File(imageFolder, StringUtils.getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:8443/api/file/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
