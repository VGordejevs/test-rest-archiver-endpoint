package lv.vladislavs.archiver.compressor;

import lv.vladislavs.archiver.exception.FileCompressorNotFoundForMethodException;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileCompressor {
    @NonNull
    InputStream compressMultipartFiles(MultipartFile[] multipartFile);

    @NonNull
    static FileCompressor from(ArchivingMethod archivingMethod) {
        switch (archivingMethod) {
            case ZIP:
                return new ZipCompressor();

            default:
                throw new FileCompressorNotFoundForMethodException(archivingMethod);
        }
    }
}