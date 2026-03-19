package whnazv.biblioteca.application.port.out;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStoragePort {

    String storeBookImage(Long bookId, MultipartFile file);

    String storeImage(MultipartFile file);
}
