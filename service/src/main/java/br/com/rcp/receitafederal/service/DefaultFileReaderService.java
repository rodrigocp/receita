package br.com.rcp.receitafederal.service;

import br.com.rcp.receitafederal.domain.service.FileReaderService;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

@Service
public class DefaultFileReaderService implements FileReaderService {
    @Override
    public Optional<String> read(String name) {
        File file = new File(name);
        StringBuilder builder = new StringBuilder();

        try (BufferedInputStream buffered = new BufferedInputStream(new FileInputStream(file))) {
            while (buffered.available() > 0) {
                builder.append((char) buffered.read());
            }
        } catch (IOException e) {
            return Optional.empty();
        }

        return Optional.of(builder.toString());
    }
}
