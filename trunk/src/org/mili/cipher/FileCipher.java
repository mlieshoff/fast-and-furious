package org.mili.cipher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class FileCipher {

	public static void main(String[] args) throws Throwable {
		if (args == null || args.length != 4) {
			showHelp();
		}
		String key = args[0];
		File out = new File(args[1]);
		out.mkdirs();
		File file = new File(args[3]);
		FileInputStream fileInputStream = new FileInputStream(file);
		FileOutputStream fileOutputStream = new FileOutputStream(new File(out, file.getName()));
		if ("code".equals(args[2])) {
			encrypt(key, fileInputStream, fileOutputStream);
		} else if ("decode".equals(args[2])) {
			decrypt(key, fileInputStream, fileOutputStream);
		} else {
			showHelp();
		}
		System.exit(0);
	}

	private static void showHelp() {
		System.err.println("Usage:");
		System.err.println("    FileCipher [key] [outdir] [code|decode] [filename]");
		System.exit(1);
	}

	private static void encrypt(String key, InputStream inputStream, OutputStream outputStream) throws Throwable {
		encryptOrDecrypt(key, Cipher.ENCRYPT_MODE, inputStream, outputStream);
	}

	private static void decrypt(String key, InputStream inputStream, OutputStream outputStream) throws Throwable {
		encryptOrDecrypt(key, Cipher.DECRYPT_MODE, inputStream, outputStream);
	}

	private static void encryptOrDecrypt(String key, int mode, InputStream inputStream, OutputStream outputStream) 
			throws Throwable {
		DESKeySpec dks = new DESKeySpec(key.getBytes());
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey desKey = skf.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");
		if (mode == Cipher.ENCRYPT_MODE) {
			cipher.init(Cipher.ENCRYPT_MODE, desKey);
			copy(new CipherInputStream(inputStream, cipher), outputStream);
		} else if (mode == Cipher.DECRYPT_MODE) {
			cipher.init(Cipher.DECRYPT_MODE, desKey);
			copy(inputStream, new CipherOutputStream(outputStream, cipher));
		}
	}

	private static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
		byte[] bytes = new byte[64];
		int size;
		while ((size = inputStream.read(bytes)) != -1) {
			outputStream.write(bytes, 0, size);
		}
		outputStream.flush();
		outputStream.close();
		inputStream.close();
	}
	
}
