package lv.vladislavs.archiver.compressor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lv.vladislavs.archiver.exception.ArchivingMethodNotFoundException;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum ArchivingMethod {
    ZIP("zip");

    private final String extension;

    public static ArchivingMethod fromExtensionIgnoreCase(String extension) {
        return Arrays.stream(values())
                .filter(archivingMethod -> archivingMethod.getExtension().equalsIgnoreCase(extension))
                .findFirst()
                .orElseThrow(() -> new ArchivingMethodNotFoundException(extension));
    }
}