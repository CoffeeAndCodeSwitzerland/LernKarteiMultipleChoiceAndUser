package application;

import java.awt.event.ActionEvent;

import com.sun.glass.ui.Accessible.EventHandler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {
	
	private Main tmain;
	
	public void setMain(Main main) {
		this.tmain = main;
	}
	
	@FXML
	Button btnts;
}
