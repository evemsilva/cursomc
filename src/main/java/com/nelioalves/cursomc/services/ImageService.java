package com.nelioalves.cursomc.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nelioalves.cursomc.services.exceptions.FileException;

@Service
public class ImageService {

	public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {

		String extension = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());

		if (!"png".equals(extension) && !"jpg".equals(extension)) {
			throw new FileException("Somente imagens no formato PNG e JPG sao permitidas");
		}

		try {
			BufferedImage img = ImageIO.read(uploadedFile.getInputStream());
			if (!"png".equals(extension)) {
				img = pngToJpg(img);
			}
			return img;
		} catch (IOException e) {
			throw new FileException("Erro ao ler arquivo. " + e.getMessage());
		}
	}

	public InputStream getInputStream(BufferedImage img, String extension) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, extension, os);
			return new ByteArrayInputStream(os.toByteArray());
		} catch (IOException e) {
			throw new FileException("Erro ao ler o arquivo. " + e.getMessage());
		}
	}

	public BufferedImage cropSquare(BufferedImage sourceImg){
	    int min = (sourceImg.getHeight() <= sourceImg.getWidth()) ? sourceImg.getHeight() : sourceImg.getWidth();
	    return Scalr.crop(
			    sourceImg,
			    (sourceImg.getWidth() / 2) - (min / 2),
			    (sourceImg.getHeight() / 2) - (min / 2),
			    min,
			    min
	    );
	}

	public BufferedImage resize(BufferedImage sourceImg, int size){
	    return Scalr.resize(sourceImg, Scalr.Method.ULTRA_QUALITY, size);
	}

	private BufferedImage pngToJpg(BufferedImage img) {
		BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
		return jpgImage;
	}

}
