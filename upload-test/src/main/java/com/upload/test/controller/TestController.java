package com.upload.test.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TestController {
    @ResponseBody
    @PostMapping(value = "/fileAndUrl")
    public ResponseEntity<Void> fileAndUrl(@RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "name", required = false) String name) throws Exception {
        System.err.println("File: " + file + " and " + name);
        file.transferTo(new File("D:/" + name));
        return ResponseEntity.ok().build();
    }

    @ResponseBody
    @PostMapping(value = "/uploadMultipleFiles")
    public ResponseEntity<Void> files(@RequestParam(value = "files", required = false) MultipartFile[] files)
            throws Exception {
        Arrays.stream(files).forEach(file -> {
            try {
                String fileName = file.getOriginalFilename();
                System.err.println(fileName);
                file.transferTo(new File("D:/" + fileName));
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        });
        return ResponseEntity.ok().build();
    }

    @GetMapping("/crawler-test")
    public ResponseEntity<Void> testCrawler(@RequestParam long sleep) throws InterruptedException {
        Thread.sleep(sleep);
        return ResponseEntity.ok().build();
    }
}
