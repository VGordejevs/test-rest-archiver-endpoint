###If we add multiple archiving methods (7z, for example):
* ArchivingMethod enum must be populated. Ex.: SEVEN_ZIP("7z"") enum.
* Implementation of new compressor must be added and implement FileCompressor interface.
* FileCompressorFactory must be updated to provide new compressor.

### Face a significant increase in request count:
* In this case, if execution time is significant, an async pattern
with callback urls can be used to decrease active connection count.
* Should be managed on infrastructure level using load balancers.

### Allow 1GB max file size:
* Max file size can be adjusted in application properties.