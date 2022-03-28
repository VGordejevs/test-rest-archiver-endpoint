package lv.vladislavs.archiver.exception;

import lv.vladislavs.archiver.compressor.ArchivingMethod;
import org.springframework.http.HttpStatus;

public class FileCompressionException extends RestArchiverException{
    public FileCompressionException(ArchivingMethod archivingMethod) {
        super("FILE_COMPRESSION_ERROR",
                "There has been file compression exception for method: " + archivingMethod.name(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}