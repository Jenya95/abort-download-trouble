package com.sanevich.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ThreadLocalRandom;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
@Service
public class ZipDownloadService {
    public StreamingResponseBody downloadAsZip(int num) {
        return response -> {
            try (ZipOutputStream zos = new ZipOutputStream(response)) {
                for (int i = 0; i < num; i++) {
                    try {
                        try (InputStream fis = new RandomInputStream(104857600)) {
                            ZipEntry zipEntry = new ZipEntry(i + ".txt");
                            zos.putNextEntry(zipEntry);

                            byte[] bytes = new byte[1024];
                            int length;
                            while ((length = fis.read(bytes)) >= 0) {
                                zos.write(bytes, 0, length);
                            }
                            zos.flush();
                        }
                    } catch (ClientAbortException cae) {
                        log.error("Caught exception", cae);
                        throw cae;
                    } catch (Exception e) {
                        log.error("Caught exception", e);
                    }
                }
            }
        };
    }

    public static class RandomInputStream extends InputStream {
        private final int capacity;

        private int readBytes = 0;

        public RandomInputStream(int capacity) {
            this.capacity = capacity;
        }

        @Override
        public int read() throws IOException {
            if (readBytes < capacity) {
                readBytes++;
                return ThreadLocalRandom.current().nextInt();
            }
            return -1;
        }
    }
}
