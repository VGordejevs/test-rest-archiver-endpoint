package lv.vladislavs.archiver.compressor;

import lv.vladislavs.archiver.exception.ArchivingMethodNotFoundException;
import org.junit.jupiter.api.Test;

import static lv.vladislavs.archiver.compressor.ArchivingMethod.ZIP;
import static lv.vladislavs.archiver.compressor.ArchivingMethod.fromExtensionIgnoreCase;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArchivingMethodTest {
    private final static String UNKNOWN_ARCHIVING_METHOD = "not-existing-archiving-method";

    @Test
    void testArchivingMethodFromExtensionIgnoreCaseSuccess() {
        assertEquals(ZIP, fromExtensionIgnoreCase(ZIP.getExtension().toUpperCase()));
    }

    @Test
    void testArchivingMethodFromExtensionIgnoreCaseNotFound() {
        ArchivingMethodNotFoundException archivingMethodNotFoundException =
                assertThrows(ArchivingMethodNotFoundException.class, () -> fromExtensionIgnoreCase(UNKNOWN_ARCHIVING_METHOD));
        assertThat(archivingMethodNotFoundException.getMessage(), containsString(UNKNOWN_ARCHIVING_METHOD));
    }
}