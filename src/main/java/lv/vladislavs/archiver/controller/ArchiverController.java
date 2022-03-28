package lv.vladislavs.archiver.controller;

import lv.vladislavs.archiver.compressor.ArchivingMethod;
import lv.vladislavs.archiver.compressor.FileCompressor;
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
    private final static String archivedFileName = "compressed";

    @PostMapping("/{extension}")
    public ResponseEntity<byte[]> archiver(@PathVariable String extension, @RequestParam("files") MultipartFile[] multipartFiles) throws IOException {
        ArchivingMethod archivingMethod = ArchivingMethod.fromExtensionIgnoreCase(extension);
        FileCompressor fileCompressor = FileCompressor.from(archivingMethod);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        responseHeaders.add(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + archivedFileName + "." + archivingMethod);

        return new ResponseEntity<>(fileCompressor.compressMultipartFiles(multipartFiles).readAllBytes(),
                responseHeaders,
                HttpStatus.OK);
    }
}