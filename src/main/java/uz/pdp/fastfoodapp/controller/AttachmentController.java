package uz.pdp.fastfoodapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.fastfoodapp.dto.request.FileUploadRequest;
import uz.pdp.fastfoodapp.model.Attachment;
import uz.pdp.fastfoodapp.service.AttachmentService;

import java.io.File;
import java.nio.file.*;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attachment")
public class AttachmentController {
    private final AttachmentService attachmentService;

    @SneakyThrows
    @GetMapping("/get/{id}")
    public ResponseEntity<?> loadImage(@PathVariable UUID id) {
        Attachment attachment = attachmentService.getById(id);
        Path path = Path.of(Paths.get(System.getProperty("user.home") + "/" + "fast-food-app"+ File.separator + "attachments").toAbsolutePath() + File.separator + attachment.getName());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        InputStreamResource resource = new InputStreamResource(Files.newInputStream(path));
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    @PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> upload(
            @RequestBody() MultipartFile file,
            @RequestParam("name") String name
    ) {
        FileUploadRequest fileUploadRequest = new FileUploadRequest(name, file);
        return ResponseEntity.ok(attachmentService.upload(fileUploadRequest));
    }

}
