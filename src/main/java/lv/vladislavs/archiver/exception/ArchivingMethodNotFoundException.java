package lv.vladislavs.archiver.exception;

import org.springframework.http.HttpStatus;

public class ArchivingMethodNotFoundException extends RestArchiverException {
    public ArchivingMethodNotFoundException(String extension) {
        super("ARCHIVING_METHOD_NOT_FOUND_ERROR",
                "Archiving method not found for extension: " + extension,
                HttpStatus.BAD_REQUEST);
    }
}