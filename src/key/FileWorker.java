package key;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import ui.FileOpener;
import ui.FileSaver;

public class FileWorker {
	
	public FileWorker() {
		
	}

	public File FileReader() {
		FileOpener fileopener = new FileOpener();
		fileopener.FileChoser();
		while (fileopener.getf() == null)
			try {
				Thread.sleep(10);
			} catch (InterruptedException e3) {
				// TODO Auto-generated catch block
				System.out.println("Can't sleep?");
				e3.printStackTrace();
			}
		fileopener.closeFrame();
		return fileopener.getf();
	}

	public void FileWriter(List<String> lines) {
		FileSaver filesaver = new FileSaver();
		filesaver.FileChoser();
		while (filesaver.getf() == null)
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		filesaver.closeFrame();
		
		System.out.println(filesaver.getf().getAbsolutePath());
		Path key = Paths.get(filesaver.getf().getAbsolutePath());
		try {
			Files.write(key, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Can't save");
		}
//System.out.println("The number of Line is " + countLine);

		
	}
}
