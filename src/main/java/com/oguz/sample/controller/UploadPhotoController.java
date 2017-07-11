package com.oguz.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.StringJoiner;

/**
 * Created by oguz on 02.07.2017.
 */
@Controller
public class UploadPhotoController {
    private static String UPLOADED_FOLDER = "/home/oguz/allImage";
    @GetMapping("uploadPhoto")
    public String uploadPhoto () {
        return "uploadPhoto";
    }
    @PostMapping("uploadPhotoService")
    public String uploadPhotoService (@RequestParam("files") MultipartFile[] files,
                                      RedirectAttributes redirectAttributes) {
        System.out.println("burda 1");

        StringJoiner sj = new StringJoiner(" , ");

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue; //next pls
            }

            try {
                System.out.println("burda 1.4");
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                Files.write(path, bytes);
                System.out.println("bytes: " +bytes);
                sj.add(file.getOriginalFilename());
                System.out.println("burda 2");
                String base64 = new String(org.apache.tomcat.util.codec.binary.Base64.encodeBase64(bytes), "UTF-8");
                System.out.println(base64);
            } catch (IOException e) {

                e.printStackTrace();
            }

        }
        System.out.println("burda5");
        String uploadedFileName = sj.toString();
        if (StringUtils.isEmpty(uploadedFileName)) {
            redirectAttributes.addFlashAttribute("message",
                    "Please select a file to upload");
        } else {
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + uploadedFileName + "'");
        }

        return "uploadStatus";
    }
}
