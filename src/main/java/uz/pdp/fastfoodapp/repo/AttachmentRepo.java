package uz.pdp.fastfoodapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.fastfoodapp.exception.NotFoundException;
import uz.pdp.fastfoodapp.model.Attachment;

import java.util.UUID;

@Repository
public interface AttachmentRepo extends JpaRepository<Attachment, UUID> {
    default Attachment getById(UUID id) {
        return findById(id).orElseThrow(() -> new NotFoundException("Attachment not found by id -> " + id));
    }
}
