package solve;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import key.CharacterNormalizer;

public class Scan {
	private int count = 1;

	public int getcount() {
		return count;
	}

	public Scan(String URL, String Susername, String Spassword, WebDriver driver) {

		// driver = new FirefoxDriver();

		try {
			driver.get(URL);
			System.out.println(driver.getTitle());
			WebElement username = driver.findElement(By.name("tag_quiz_username"));
			username.sendKeys(Susername);
			WebElement password = driver.findElement(By.name("tag_quiz_password"));
			password.sendKeys(Spassword);
			WebElement enter = driver.findElement(By.name("tagStartQuiz"));
			enter.click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void Fill(List<String> q, List<String> a, WebDriver driver) {
		int c = -1;
		WebElement question = null;
		try {
			question = driver.findElement(
					By.xpath("//*[@id=\"drawAllQuestionsTbl\"]/tbody/tr[" + String.valueOf(count) + "]/td/ol/li"));
		} catch (Exception e) {
			return;
		}
		String txt = CharacterNormalizer.Norm(question.getText().trim());
		System.out.println(txt.length());
		System.out.println(txt);
		for (int i = 0; i < q.size(); i++) {
			// question.findElement(By.xpath("//*[@id=\"drawAllQuestionsTbl\"]/tbody/tr[1]/td/ol/li/text()[1]")).getText();
			System.out.println(q.get(i));
			System.out.println(txt);
			// System.out.println(txt.compareToIgnoreCase(q.get(i)));
			// question.getText()
			// System.out.println(q.get(i).length());

			if (CharacterNormalizer.Norm(txt).compareToIgnoreCase(CharacterNormalizer.Norm(q.get(i))) == 0) {
				// System.out.println("Yessssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
				c = i;
				break;
			}
		}
		WebElement answer = driver.findElement(By.name("tq_g_" + (count - 1) + "_guess"));
		answer.sendKeys(CharacterNormalizer.Norm(a.get(c)));
		System.out.println(q.get(c) + ": " + a.get(c));
		count++;
		
		////*[@id="quizContent"]/form/table[4]/tbody/tr/td/input
		//// *[@id="drawAllQuestionsTbl"]/tbody/tr[1]/td/ol/li
		//// *[@id="drawAllQuestionsTbl"]/tbody/tr[1]/td/ol/li/text()[1]
		// *[@id="drawAllQuestionsTbl"]/tbody/tr[1]/td/ol/li/input
	}

	
}
