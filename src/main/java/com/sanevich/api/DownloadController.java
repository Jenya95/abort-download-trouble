package com.sanevich.api;

import com.sanevich.service.ZipDownloadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class DownloadController {
    private final ZipDownloadService zipDownloadService;

    @GetMapping("batchdownload/{num}")
    public ResponseEntity<StreamingResponseBody> downloadAsZip(final HttpServletResponse response, @PathVariable int num) {
        response.setContentType("application/zip");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"some.zip\"");
        var srb = zipDownloadService.downloadAsZip(num);
        return ResponseEntity.ok(srb);
    }

}
