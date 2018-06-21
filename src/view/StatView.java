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
		//Menu buttons
		Button btnTimePlayed = new Button();
		Button btnWinLoss = new Button();
		Button btnCloseStats = new Button();
		
		btnWinLoss.setText("Gewinn/Verlust-Statistik");
		btnTimePlayed.setText("Spielzeit");
		btnCloseStats.setText("Statistiken schliessen");
		//Menu box
		HBox hb = new HBox();
		hb.getChildren().addAll(btnWinLoss,btnTimePlayed,btnCloseStats);
		hb.setStyle("-fx-background-color: #ff69b4;    -fx-padding: 15;\r\n" + 
					"    -fx-spacing: 10;");
		
		BorderPane bp = new BorderPane();
		
		bp.setTop(hb);
		//Stage and stats
		Stage stats = new Stage();
		stats.setFullScreen(true);
		stats.setTitle("Statistiken");
		stats.setScene(new Scene(bp, 900, 900));
		stats.show();
		
		btnCloseStats.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stats.close();
			}			
		});
		
		btnWinLoss.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				bp.setCenter(createWinLoss());
			}			
		});
		
		btnTimePlayed.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				bp.setCenter(createPlaytime());
			}			
		});
	}
	
	public GridPane createWinLoss() {
		GridPane gp = createView("Gewinn/Verlust:");
		Integer wins = gs.getWins();
		Integer losses = gs.getLosses();
		
		Text games = new Text("Spiele gesamt:" + (wins + losses));
		
		games.setFont(Font.font("Verdana",50));
		
		
		ObservableList<PieChart.Data> pieChartDataWinLoss =
                FXCollections.observableArrayList(
                new PieChart.Data("Siege",wins),
                new PieChart.Data("Niederlagen", losses));
		
		PieChart chart = new PieChart(pieChartDataWinLoss);
		chart.setTitle("Sieg/Verlust-Verhältnis");
		chart.setLegendSide(Side.RIGHT);
		chart.setLabelsVisible(false);
	
		gp.add(chart, 1, 2);
		gp.add(games, 2, 2);
		return gp;
	}
	
	public GridPane createPlaytime() {
		GridPane gp = createView("Spielzeit:");
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		
		Text time = new Text("Gesamthaft gespielt: 213h"); //TODO: add real value
		time.setFont(Font.font("Verdana",50));
		
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
