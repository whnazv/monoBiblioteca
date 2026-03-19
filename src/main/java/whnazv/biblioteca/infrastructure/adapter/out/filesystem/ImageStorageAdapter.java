package whnazv.biblioteca.infrastructure.adapter.out.filesystem;

import lombok.RequiredArgsConstructor;
import whnazv.biblioteca.application.port.out.ImageStoragePort;
import whnazv.biblioteca.domain.exception.ImageUploadException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@RequiredArgsConstructor
public class ImageStorageAdapter implements ImageStoragePort {

@Override
public String storeImage(MultipartFile file) {
    return storeBookImage(0L, file);
}


    @Override
    public String storeBookImage(Long bookId, MultipartFile file) {
        try {
            String folderPath = "uploads/images/";
            String fileName = "book_" + bookId + ".jpg";
            Path filePath = Paths.get(folderPath + fileName);

            Files.createDirectories(Paths.get(folderPath));
            Files.write(filePath, file.getBytes());

            return "/images/" + fileName;

        } catch (IOException e) {
            throw new ImageUploadException("Error al subir la imagen", e);
        }
    }
}
