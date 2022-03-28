package lv.vladislavs.archiver.exception;

import lv.vladislavs.archiver.compressor.ArchivingMethod;
import org.springframework.http.HttpStatus;

public class FileCompressorNotFoundForMethodException extends RestArchiverException {
    public FileCompressorNotFoundForMethodException(ArchivingMethod archivingMethod) {
        super("FILE_COMPRESSOR_NOT_FOUND_FOR_METHOD",
                "File compressor not found for method: " + archivingMethod.name(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}