package uz.pdp.fastfoodapp.service.impl;


import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.dto.request.FileUploadRequest;
import uz.pdp.fastfoodapp.exception.NotFoundException;
import uz.pdp.fastfoodapp.model.Attachment;
import uz.pdp.fastfoodapp.repo.AttachmentRepo;
import uz.pdp.fastfoodapp.service.AttachmentService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {
    private static final String BASE_URL = "http://localhost:8080/api/v1/attachment/";
    private static final Path UPLOAD_DIR = Path.of(System.getProperty("user.home")+ "/" + "fast-food-app"+ File.separator + "attachments" + File.separator);

    private final AttachmentRepo attachmentRepo;

    @Override
    public Attachment toEntity(UUID id, FileUploadRequest request) {
        String fileNameWithId = request.getName() + "$" + id.toString();
        return Attachment.builder()
                .id(id)
                .name(fileNameWithId)
                .contentType(request.getFile().getContentType())
                .size(request.getFile().getSize())
                .contentUrl(BASE_URL + id)
                .build();
    }

    @Override
    public Attachment toFileUploadResponse(Attachment attachment) {
        return new Attachment(
                attachment.getId(),
                attachment.getName(),
                attachment.getContentType(),
                attachment.getSize(),
                attachment.getContentUrl()
        );
    }

    @Override
    @SneakyThrows
    public Attachment upload(FileUploadRequest request) {
        Attachment attachment = toEntity(UUID.randomUUID(), request);
        Path path = Paths.get(UPLOAD_DIR.toString(), attachment.getName());
        try (var inputStream = request.getFile().getInputStream()) {
            Files.createDirectories(UPLOAD_DIR);
            Files.copy(inputStream, path);
        }
        Files.write(path,request.getFile().getInputStream().readAllBytes());
        attachmentRepo.save(attachment);
        return toFileUploadResponse(attachment);
    }

    @Override
    public void getById(UUID id, HttpServletResponse resp) {

        Attachment attachment = attachmentRepo.findById(id).orElseThrow(() -> new NotFoundException("file"));
        try {
            Path path = Path.of(attachment.getContentUrl());
            Files.copy(path, resp.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Attachment getByName(String name) {
        return null;
    }


}
