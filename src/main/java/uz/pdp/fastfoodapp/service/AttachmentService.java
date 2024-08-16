package uz.pdp.fastfoodapp.service;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.dto.request.FileUploadRequest;
import uz.pdp.fastfoodapp.model.Attachment;

import java.util.UUID;

@Service
public interface AttachmentService {
    Attachment toEntity(UUID id, FileUploadRequest request);
    Attachment toFileUploadResponse(Attachment attachment);

    Attachment upload(FileUploadRequest request);
    void getById(UUID id, HttpServletResponse resp);
    Attachment getByName(String name);
}
