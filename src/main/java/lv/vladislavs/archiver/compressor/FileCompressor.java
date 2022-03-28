package lv.vladislavs.archiver.compressor;

import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileCompressor {
    @NonNull
    InputStream compressMultipartFiles(MultipartFile[] multipartFile);
}