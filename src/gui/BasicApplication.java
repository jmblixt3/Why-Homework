package gui;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFrame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class BasicApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    	System.out.println(System.getProperty("os.name"));		/*
		 * URL url = BasicApplication.class.getClassLoader().getResource("gui.fxml");
		 * System.out.println(url); if (url == null) {
		 * System.out.println("Can't load FXML file"); Platform.exit(); }
		 */
    	URL location = getClass().getResource("gui.fxml");
    	//ResourceBundle resources = ResourceBundle.getBundle("gui");
    	FXMLLoader fxmlLoader = new FXMLLoader(location);

    	BasicFXMLController controller = (BasicFXMLController)fxmlLoader.getController();
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override public void handle(WindowEvent t) {
            	System.out.println("CLOSING");
                System.exit(0);
            }
        });
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}