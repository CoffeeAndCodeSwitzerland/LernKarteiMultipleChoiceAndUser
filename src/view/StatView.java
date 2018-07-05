package view;

import modul.GetStats;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StatView {
	GetStats gs = new GetStats();
	
	public void start() {
		//Initializes the menu buttons
		Button btnTimePlayed = new Button();
		Button btnWinLoss = new Button();
		Button btnCloseStats = new Button();
		
		//Sets the text for all those buttons
		btnWinLoss.setText("Gewinn/Verlust-Statistik");
		btnTimePlayed.setText("Spielzeit");
		btnCloseStats.setText("Statistiken schliessen");
		
		//creates and styles the menu box(the pink one on the top)
		HBox hb = new HBox();
		hb.getChildren().addAll(btnWinLoss,btnTimePlayed,btnCloseStats);
		hb.setStyle("-fx-background-color: #ff69b4;    -fx-padding: 15;\r\n" + 
					"    -fx-spacing: 10;");
		//Creates a borderpane and sets the menu box to the top
		BorderPane bp = new BorderPane();
		bp.setTop(hb);
		//sets the win loss to the center as it is the standart 
		bp.setCenter(createWinLoss());
		//Stage and stats
		Stage stats = new Stage();
		stats.setFullScreen(true);
		stats.setTitle("Statistiken");
		stats.setScene(new Scene(bp, 900, 900));
		stats.show();
		
		//Button to close the view
		btnCloseStats.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stats.close();
			}			
		});
		//Button to set the center to the win loss ratio
		btnWinLoss.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				bp.setCenter(createWinLoss());
			}			
		});
		//Button to set the center to the playtime 
		btnTimePlayed.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				bp.setCenter(createPlaytime());
			}			
		});
	}
	//creates win/loss view
	public GridPane createWinLoss() {
		GridPane gp = createView("Punkte erhalten/Punkte möglich:");
		Integer wins = gs.getPoints();
		Integer losses = gs.getPointsTotal();
		
		Text games = new Text("Test durchgeführt gesamt: " + Integer.toString(gs.getPlays()));
		
		games.setFont(Font.font("Verdana",50));
		
		//Creates the pie chart
		ObservableList<PieChart.Data> pieChartDataWinLoss =
                FXCollections.observableArrayList(
                new PieChart.Data("Punkte geholt",wins),
                new PieChart.Data("Punkte nicht geholt", losses - wins));
		
		PieChart chart = new PieChart(pieChartDataWinLoss);
		chart.setTitle("Punkte erhalten/Punkte möglich");
		chart.setLegendSide(Side.RIGHT);
		chart.setLabelsVisible(false);
	
		gp.add(chart, 1, 2);
		gp.add(games, 2, 2);
		return gp;
	}
	//creates the playtime box
	public GridPane createPlaytime() {
		GridPane gp = createView("Spielzeit:");
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		//Adds the line chart
		yAxis.setLabel("Spielzeit in Stunden");
		LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Spielzeit in den letzten drei Monaten");
        //defining a series
        XYChart.Series series = new XYChart.Series();	
        series.setName("My portfolio");
        //populating the series with data
        series.getData().add(new XYChart.Data(0, 3));
        series.getData().add(new XYChart.Data(1, 14));
        series.getData().add(new XYChart.Data(2, 5));
        lineChart.getData().add(series);
        
        lineChart.getXAxis().setTickLabelsVisible(false);
        Text time = new Text("Spielzeit: 123"); //TODO: add real value
        time.setFont(Font.font("Verdana",50));
        
        gp.add(time, 2, 2);
        gp.add(lineChart, 1, 2);
		return gp;
	}
	
	//Creates the basic Panel with the title as argument
	public GridPane createView(String title) {
		GridPane gp = new GridPane();
		gp.setHgap(50);
		gp.setVgap(50);
		
		Text titleGP = new Text(title);
		titleGP.setFont(Font.font ("Verdana", FontPosture.ITALIC, 75));
		
		gp.add(titleGP, 1,1);
		return gp;
	}
}
