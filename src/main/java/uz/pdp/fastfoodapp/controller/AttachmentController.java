package uz.pdp.fastfoodapp.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.fastfoodapp.dto.request.FileUploadRequest;
import uz.pdp.fastfoodapp.dto.response.SuccessResponse;
import uz.pdp.fastfoodapp.service.AttachmentService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attachment")
public class AttachmentController {
    private final AttachmentService attachmentService;

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable UUID id, HttpServletResponse resp) {
        attachmentService.getById(id,resp);
        return ResponseEntity.ok(new SuccessResponse("ok"));
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> upload(
            @RequestBody() MultipartFile file,
            @RequestParam("name") String name
    ) {
        FileUploadRequest fileUploadRequest = new FileUploadRequest(name, file);
        return ResponseEntity.ok(attachmentService.upload(fileUploadRequest));
    }
    @GetMapping("/id/{id}")
    public void getById(@PathVariable UUID id, HttpServletResponse response) {
        attachmentService.getById(id, response);
    }
}
