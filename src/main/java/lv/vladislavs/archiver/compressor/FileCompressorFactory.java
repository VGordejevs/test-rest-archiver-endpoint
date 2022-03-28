package lv.vladislavs.archiver.compressor;

import lv.vladislavs.archiver.exception.FileCompressorNotFoundForMethodException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class FileCompressorFactory {
    @Autowired
    ZipCompressor zipCompressor;

    @NonNull
    public FileCompressor from(ArchivingMethod archivingMethod) {
        switch (archivingMethod) {
            case ZIP:
                return zipCompressor;

            default:
                throw new FileCompressorNotFoundForMethodException(archivingMethod);
        }
    }
}
