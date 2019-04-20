package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import key.Loader;
import solve.Driver;
import solve.Scan;

public class BasicFXMLController {

	@FXML
	private Button loadHTML;
	private Button saveVocab;
	private Button stopWebDriver;
	private Button startWebDriver;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private TextField url;

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
		/*
		 * try { driver.getDriver().get("google.com"); } catch (Exception e1) {
		 * Auto-generated catch block driver.start(); }
		 */

		try {
			driver.getDriver().get(url.getText());
			Scan hack = new Scan(url.getText(), username.getText(), password.getText(), driver.getDriver());
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					System.out.println(hack.getcount());
					System.out.println(load.getQuestions().size());
					System.out.println(load.getQuestions().get(1));
					while (hack.getcount() < load.getQuestions().size()) {
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