package application;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage theStage) {
		theStage.setTitle("Hello, world!");
		theStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
