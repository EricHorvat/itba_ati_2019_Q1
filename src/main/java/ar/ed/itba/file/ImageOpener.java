package ar.ed.itba.file;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public /*abstract*/ class ImageOpener {
	private final String filePath = "./resources/BARCO.RAW";
	private final String infoFilePath = "./resources/BARCO.txt";
	
	public BufferedImage open(){
		try {
			byte[] imageArray = Files.readAllBytes(Paths.get(filePath));
			File infoFile = new File(infoFilePath);
			BufferedReader br = new BufferedReader(new FileReader(infoFile));
			String[] st = br.readLine().split(",");
			int width = Integer.parseInt(st[0]);
			int height = Integer.parseInt(st[1]);
			byte[][] imageMatrix = transform(imageArray,width);
			int a = 5;
		} catch (IOException e){
			int b = 10;
		}
		return null;
	}
	
	private static byte[][] transform(byte[] arr, int N) {
		int M = (arr.length + N - 1) / N;
		byte[][] mat = new byte[M][];
		int start = 0;
		for (int r = 0; r < M; r++) {
			int L = Math.min(N, arr.length - start);
			mat[r] = java.util.Arrays.copyOfRange(arr, start, start + L);
			start += L;
		}
		return mat;
	}
}
