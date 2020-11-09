package com.sanevich.api;

import com.sanevich.service.ZipDownloadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequiredArgsConstructor
public class DownloadController {
    private final ZipDownloadService zipDownloadService;

    @GetMapping("batchdownload/{num}")
    public ResponseEntity<StreamingResponseBody> downloadAsZip(@PathVariable int num) {
        var srb = zipDownloadService.downloadAsZip(num);
        return ResponseEntity.status(200)
                .contentType(MediaType.parseMediaType("application/zip"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"some.zip\"")
                .body(srb);
    }

}
