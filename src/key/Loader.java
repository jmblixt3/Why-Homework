package key;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import solve.Scan;
import ui.FileOpener;
import ui.FileSaver;

public class Loader {

	String[][] solution = null;
	List<String> solutionLines = new ArrayList<>();
	// List<String> solLines = new ArrayList<>();
	List<Integer> SolutionToLineNumber = new ArrayList<>();// This is a way to match up solutions to answers
	List<String> questions = new ArrayList<>();
	List<String> answers = new ArrayList<>();

	public List<String> getQuestions() {
		return questions;
	}

	public List<String> getAnswers() {
		return answers;
	}

	//FileReader fr = null;
	//BufferedReader br = null;
	String[] tempsol = new String[2];
	List<String> lines = new ArrayList<>();
	int solutionCounter = 1;
	int countLine = 0;
	String s = "";
	String stringLine = "";

	public Loader() {
		solutionCounter = 1;
		countLine = 0;

	}

	public void LoadHTML() {

		String s = "";
		String stringLine = "";
		String wordToFind = "<li><div><span class=";

		FileWorker fileLoad = new FileWorker();
		lines.add("null:null");
		try {
			/*
			 * fr = new FileReader(fileLoad.FileReader().getAbsolutePath()); br = new
			 * BufferedReader(fr);
			 */
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileLoad.FileReader().getAbsolutePath()), "UTF-8"));
			while ((s = in.readLine()) != null) {
				stringLine = stringLine + s;
				stringLine = stringLine + " ";/* Add space */
				countLine++;
				Pattern word = Pattern.compile(wordToFind);
				Matcher match = word.matcher(s);
				// System.out.println(s);
				while (match.find()) {
					String txt = Normalizer.normalize(s, Normalizer.Form.NFC);
					solutionLines.add(txt);
					// System.out.print(stringLine.charAt(match.start() - 1));
					// System.out.print(" " + wordToFind + " ");
					// System.out.println(s.charAt(match.end()));
					if (match.find() == true)
						;
					SolutionToLineNumber.add(solutionCounter);

				}
				solutionCounter++;
			}
			solution = new String[solutionCounter][2];
			System.out.println(SolutionToLineNumber.toString());
			// System.out.println(stringLine);
			// text = stringLine;

			String l, l2, l3, l4, l5, l6 = null;
			l4 = null;

			for (int i = 0; i < SolutionToLineNumber.size(); i++) {

				l = solutionLines.get(i);
				l2 = l.replaceFirst("<li><div><span class=\"quia_standard\">", "");
				l3 = l2.split("&")[0];
				try {
					l4 = l2.split("acceptable:</b><br><span class=\"fians\">")[1];
				} catch (ArrayIndexOutOfBoundsException e1) {
					try {

						l6 = l2.split("</b><br><table><tbody><tr><td class=\"quia_standard\">")[1];
						l4 = l6.split("</td><td class=\"quia_standard\"><span class=\"fians\">")[1];
					} catch (IllegalAccessError e2) {

					}
				}
				l5 = l4.split("</span>")[0];
				tempsol[0] = l3;
				questions.add(l3);
				tempsol[1] = l5;
				answers.add(l5);
				System.out.println("out:" + tempsol[0] + ", " + tempsol[1]);

				solution[i][0] = tempsol[0];
				solution[i][1] = tempsol[1];
			}

			System.out.println(solution[0][0]);

			for (int i = 0; i < solution.length; i++) {
				try {
					lines.add(solution[i][0].trim() + ":" + solution[i][1].trim());
				} catch (Exception e) {
					System.out.println(e);
					lines.add(solution[i][0].trim() + ":" + solution[i][1].trim());
				}
				if (solution[i][0].trim() == "null" && solution[i + 1][0].trim() == "null") {
					break;
				}
			}
			/*
			 * File file1 = FileChooserDemo.FileChoser(); while (file1 == null) { file1 =
			 * FileChooserDemo.getf(); }
			 */
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// String wordToFind = "<li><div><span class=";
		lines.add("null:null");
	}// End of main() method

	public void SaveVocab() {

		FileWorker filesave = new FileWorker();
		filesave.FileWriter(lines);
		// String wordToFind = "<li><div><span class=";

	}

	public void LoadVocab() {

		FileWorker fileVocab = new FileWorker();
		// file = new
		// File("C:\\Users\\User\\eclipse-workspace\\QUIA8\\src\\key\\the-file-name.txt");
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileVocab.FileReader().getAbsolutePath()), "UTF-8"));

			while ((s = in.readLine()) != null) {
				stringLine = CharacterNormalizer.Norm(stringLine + s);
				stringLine = CharacterNormalizer.Norm(stringLine + " ");/* Add space */
				questions.add(s.split(":")[0]);
				answers.add(s.split(":")[1]);
			}
			in.close();
			//br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.print(questions.size());
	}

	/*
	 * public String Normtxt(String txt) { String x =txt; String y = x; y =
	 * Normalizer.normalize(x.replaceAll("í", "�"), Normalizer.Form.NFC); x=y; y =
	 * Normalizer.normalize(x.replaceAll("¡", "�"), Normalizer.Form.NFC); x=y; y =
	 * Normalizer.normalize(x.replaceAll("é", "�"), Normalizer.Form.NFC); x=y; y =
	 * Normalizer.normalize(x.replaceAll("é", "�"), Normalizer.Form.NFC); x=y; y =
	 * Normalizer.normalize(x.replaceAll("á", "�"), Normalizer.Form.NFC); x=y; y =
	 * Normalizer.normalize(x.replaceAll("ó", "�"), Normalizer.Form.NFC); x=y; y =
	 * Normalizer.normalize(x.replaceAll("¿", "�"), Normalizer.Form.NFC); x=y; y =
	 * Normalizer.normalize(x.replaceAll("ñ", "�"), Normalizer.Form.NFC); x=y; y =
	 * Normalizer.normalize(x.replaceAll("ú", "�"), Normalizer.Form.NFC); x=y; y =
	 * Normalizer.normalize(x.replaceAll("’", "'"), Normalizer.Form.NFC); x=y; //’ y
	 * = x.trim(); x=y; return x; }
	 */
}