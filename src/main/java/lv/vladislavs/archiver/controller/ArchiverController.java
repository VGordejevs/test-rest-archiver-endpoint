package lv.vladislavs.archiver.controller;

import lv.vladislavs.archiver.compressor.ArchivingMethod;
import lv.vladislavs.archiver.compressor.FileCompressor;
import lv.vladislavs.archiver.compressor.FileCompressorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/archiver")
public class ArchiverController {
    private final static String ARCHIVED_FILE_NAME = "compressed";

    @Autowired
    FileCompressorFactory fileCompressorFactory;

    @PostMapping("/{extension}")
    public ResponseEntity<byte[]> archiver(@PathVariable String extension,
                                           @RequestParam("files") MultipartFile[] multipartFiles) throws IOException {
        ArchivingMethod archivingMethod = ArchivingMethod.fromExtensionIgnoreCase(extension);
        FileCompressor fileCompressor = fileCompressorFactory.from(archivingMethod);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        responseHeaders.add(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + ARCHIVED_FILE_NAME + "." + archivingMethod);

        return new ResponseEntity<>(fileCompressor.compressMultipartFiles(multipartFiles).readAllBytes(),
                responseHeaders,
                HttpStatus.OK);
    }
}