package key;

import java.text.Normalizer;

public class CharacterNormalizer {
	public static String Norm(String text) {
		
		if(System.getProperty("os.name").contains("Linux")) {
				return Linux(text);
		}else if(System.getProperty("os.name").contains("Windows")) {
				return Windows(text);
		}else {
			System.out.println("what os is this?");	
			System.exit(0);
			return text;
		}
		
	}
	static String Windows(String text) {
		String x =text;
		String y = x;
		y = Normalizer.normalize(x.replaceAll("í", "�"), Normalizer.Form.NFC);
		x=y;
		y = Normalizer.normalize(x.replaceAll("¡", "�"), Normalizer.Form.NFC);
		x=y;
		y = Normalizer.normalize(x.replaceAll("é", "�"), Normalizer.Form.NFC);
		x=y;
		y = Normalizer.normalize(x.replaceAll("é", "�"), Normalizer.Form.NFC);
		x=y;
		y = Normalizer.normalize(x.replaceAll("á", "�"), Normalizer.Form.NFC);
		x=y;
		y = Normalizer.normalize(x.replaceAll("ó", "�"), Normalizer.Form.NFC);
		x=y;
		y = Normalizer.normalize(x.replaceAll("¿", "�"), Normalizer.Form.NFC);
		x=y;
		y = Normalizer.normalize(x.replaceAll("ñ", "�"), Normalizer.Form.NFC);
		x=y;
		y = Normalizer.normalize(x.replaceAll("ú", "�"), Normalizer.Form.NFC);
		x=y;
		y = Normalizer.normalize(x.replaceAll("’", "'"), Normalizer.Form.NFC);
		x=y;
		//’
		y = x.trim();
		x=y;
		return x;
	}
	static String Linux(String text) {
		text = text.trim();
		return text;
	}
}
