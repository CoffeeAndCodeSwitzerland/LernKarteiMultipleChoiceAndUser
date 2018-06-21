package view;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainView {

	StatView sv;
	
	public void start(Stage primaryStage) {
		sv = new StatView();
		
		Text greetings = new Text("Willkommen *Fremdländer*");
		
		
		primaryStage.setTitle("Multiple Choice and Users");
		
		primaryStage.initStyle(StageStyle.UTILITY);
		
		Button btnStats = new Button();
		Button btnMultipleChoiceTest = new Button();
		Button btnMultipleChoiceAddQuestions = new Button();
		
		btnStats.setText("Statistiken");
		btnMultipleChoiceTest.setText("Test durchführen");
		btnMultipleChoiceAddQuestions.setText("Neuer Test erstellen");
		
		btnStats.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				sv.start();
			}			
		});
		
		btnMultipleChoiceTest.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.close();
			}			
		});

		btnMultipleChoiceAddQuestions.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.close();
			}			
		});
		
		VBox root = new VBox();
		root.setPadding(new Insets(100,100,100,100));
		root.setSpacing(10);
		
		
		
		root.getChildren().addAll(greetings,btnMultipleChoiceTest, btnMultipleChoiceAddQuestions, btnStats);
		       
		primaryStage.setScene(new Scene(root,500,500));
		primaryStage.show();
	}
}
