package uz.pdp.fastfoodapp.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.dto.request.FileUploadRequest;
import uz.pdp.fastfoodapp.exception.NotFoundException;
import uz.pdp.fastfoodapp.model.Attachment;
import uz.pdp.fastfoodapp.repo.AttachmentRepo;
import uz.pdp.fastfoodapp.service.AttachmentService;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {
    private static final String BASE_URL = "http://45.138.158.240:8080/api/v1/attachment/get/";
    private static final Path UPLOAD_DIR = Path.of(System.getProperty("user.home")+ "/" + "fast-food-app"+ File.separator + "attachments" + File.separator);

    private final AttachmentRepo attachmentRepo;

    @Override
    public Attachment toEntity(UUID id, FileUploadRequest request,String contentType) {
        return Attachment.builder()
                .id(id)
                .name(id + contentType)
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
        String contentType = request.getFile().getContentType();
        if (contentType != null) {
            contentType = "." + contentType.substring(contentType.indexOf("/")+1);
            System.out.println(contentType);
        }else {
            contentType = ".jpg";
        }
        Attachment attachment = toEntity(UUID.randomUUID(), request,contentType);
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
    public Attachment getById(UUID id) {
        return attachmentRepo.findById(id).orElseThrow( ()-> new NotFoundException("File"));
    }
}
