package com.residencia.ecommerce.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageFilesService {
	@Value("${files.folder.path}")
	private Path path;

	public String getFilePathAsString(String fileName) throws IOException {
		try {
			return path.resolve(fileName).toRealPath().toString();
		} catch (IOException e) {
			throw new IOException("Erro ao gerar o nome de caminho do arquivo.");
		}
	}

	public void saveFile(String fileName, MultipartFile file) throws IOException {
		try {
			Files.copy(file.getInputStream(), path.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new IOException("Não foi possível salvar o arquivo.");
		}
	}

	public Resource getFile(String fileName) throws IOException {
		try {
			return new InputStreamResource(new FileInputStream(path.resolve(fileName).toAbsolutePath().toString()));
		} catch (IOException e) {
			throw new IOException("Não foi possível resgatar o arquivo.");
		}
	}
}
