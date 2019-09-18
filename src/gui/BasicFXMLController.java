package gui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javafx.fxml.FXML;
//import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import key.Loader;
import solve.Driver;
import solve.Scan;
import sqlwebconnection.SQLDriver;

public class BasicFXMLController {

	@FXML
	private Button loadHTML;
	//private Button saveVocab;
	//private Button stopWebDriver;
	//private Button startWebDriver;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private TextField url;
	@FXML
	private CheckBox activeServer;
	private final int wait = 10000;
	Loader load;
	Driver driver;

	// Add a public no-args constructor

	public BasicFXMLController() {
	}

	@FXML
	public void initialize() {
		// TODO
		load = new Loader();
		driver = new Driver();
	}

	@FXML
	private void LoadHTML() {
		load.LoadHTML();
	}

	@FXML
	private void SaveVocab() {
		load.SaveVocab();
	}

	@FXML
	private void LoadVocab() {
		load.LoadVocab();
	}

	@FXML
	private void StartHack() {

		try {
			driver.getDriver().get(url.getText());
			Scan hack = new Scan(url.getText(), username.getText(), password.getText(), driver.getDriver());
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					System.out.println(hack.getcount());
					System.out.println(load.getQuestions().size());
					System.out.println(load.getQuestions().get(1));
					while (hack.getcount() <= load.getQuestions().size()) {
						hack.Fill(load.getQuestions(), load.getAnswers(), driver.getDriver());
					}
				}
			});
			thread.start();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@FXML
	private void StartServer() {
		activeServer.setSelected(true);
		SQLDriver sql = new SQLDriver();
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (activeServer.isSelected()) {
					if (sql.isData()) {
						try {
							driver.getDriver();
						} catch (Exception e) {
							driver.start();
							e.printStackTrace();
						}
						Scan hack = new Scan(sql.getTerm()[2], sql.getTerm()[0], sql.getTerm()[1], driver.getDriver());

						// System.out.println(hack.getcount());
						// System.out.println(load.getQuestions().size());
						// System.out.println(load.getQuestions().get(1));
						while (hack.getcount() < load.getQuestions().size()) {
							hack.Fill(load.getQuestions(), load.getAnswers(), driver.getDriver());
						}
						try {
							Thread.sleep(wait);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}
						WebElement enter = driver.getDriver().findElement(By.xpath("//*[@id=\"quizContent\"]/form/table[4]/tbody/tr/td/input"));
						enter.click();
						sql.markTopComplete();
						sql.deleteCompleted();
						
					} else {
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}
					}
				}
			}
		});
		thread.start();
	}

	@FXML
	public void StartWebDriver() {
		driver.start();
	}

	@FXML
	public void StopWebDriver() {
		driver.stop();
	}

	@FXML
	public void Quit() {
		System.out.println("CLOSING");
		System.exit(0);
	}
}