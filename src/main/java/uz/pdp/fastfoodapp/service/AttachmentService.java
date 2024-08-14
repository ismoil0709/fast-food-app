package uz.pdp.fastfoodapp.service;


import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.dto.request.FileUploadRequest;
import uz.pdp.fastfoodapp.model.Attachment;

import java.util.UUID;

@Service
public interface AttachmentService {
    Attachment toEntity(UUID id, FileUploadRequest request);
    Attachment toFileUploadResponse(Attachment attachment);

    Attachment upload(FileUploadRequest request);
    Attachment getById(UUID id);
    Attachment getByName(String name);
}
