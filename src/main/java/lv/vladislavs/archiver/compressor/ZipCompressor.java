package lv.vladislavs.archiver.compressor;

import lv.vladislavs.archiver.exception.FileCompressionException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Component
public class ZipCompressor implements FileCompressor {
    @Override
    public InputStream compressMultipartFiles(MultipartFile[] multipartFiles) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
        try {
            for (int i = 0; i < multipartFiles.length; i++) {
                MultipartFile multipartFile = multipartFiles[i];

                InputStream inputStream = multipartFile.getInputStream();
                String fileName = multipartFile.getOriginalFilename();
                if (fileName == null) {
                    // As fileName is nullable, generate unique file name to avoid duplicates.
                    fileName = "file (" + i + ")";
                }
                ZipEntry zipEntry = new ZipEntry(fileName);
                zipOutputStream.putNextEntry(zipEntry);

                byte[] bytes = new byte[1024];
                int length;
                while ((length = inputStream.read(bytes)) >= 0) {
                    zipOutputStream.write(bytes, 0, length);
                }

                zipOutputStream.closeEntry();
            }

            zipOutputStream.close();
        } catch (IOException ioException) {
            throw new FileCompressionException(ArchivingMethod.ZIP);
        }

        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }
}