package com.watch.store.mapper;

import com.watch.store.dto.WatchDto;
import com.watch.store.entity.FileCover;
import com.watch.store.entity.Watch;
import com.watch.store.repository.FileCoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Component
public class WatchMapper {
    private static String DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/img/";
    @Autowired
    private FileCoverRepository fileCoverRepository;



    public Watch watchMapper(WatchDto watchDto, MultipartFile file) throws IOException {
        Path fileName = Paths.get(DIRECTORY);
        FileCover fileCover = new FileCover();
        fileCover.setPath(fileName.toFile().getPath());
        FileCover fileSaved = fileCoverRepository.save(fileCover);
        final String fileExtension = Optional.ofNullable(file.getOriginalFilename())
                .flatMap(WatchMapper::getFileExtension)
                .orElse("");

        final String targetFileName = fileSaved.getId() + "." + fileExtension;
        final Path targetPath = fileName.resolve(targetFileName);

        File f = new File(String.valueOf(targetPath));
        file.transferTo(f);
        Watch watch = new Watch();
        watch.setTitle(watchDto.getTitle());
        watch.setDescription(watchDto.getDescription());
        watch.setQuantity(Integer.valueOf(watchDto.getQuantity()));
        watch.setPrice(Double.parseDouble(watchDto.getPrice()));
        watch.setQuantity(Integer.parseInt(watchDto.getQuantity()));
        watch.setFileCover(fileSaved);
        return watch;
    }
    private static Optional<String> getFileExtension(String fileName) {
        final int indexOfLastDot = fileName.lastIndexOf('.');

        if (indexOfLastDot == -1) {
            return Optional.empty();
        } else {
            return Optional.of(fileName.substring(indexOfLastDot + 1));
        }
    }
}
