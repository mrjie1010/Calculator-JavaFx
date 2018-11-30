package edu.sjsu.cs151.spr2018.javafxlab;

import java.awt.Color;

import edu.sjsu.cs151.spr2018.javafxlab.model.CalculatorModel;
import edu.sjsu.cs151.spr2018.javafxlab.model.CalculatorModel.ModelListener;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

// TODO(you): make Main implement ModelListener
public class Main extends Application implements EventHandler<ActionEvent>, ModelListener{
	CalculatorModel model = new CalculatorModel();
	Label result = new Label();

	@Override
	public void start(Stage primaryStage) {
		try {

			String[][] keys = {
					{"AC", "±", "%", "/"},
					{"7", "8", "9", "*"},
					{"4", "5", "6", "-"},
					{"1", "2", "3", "+"},
					{"0", "0", ".", "="}
			};

			result.setMinWidth(200);
			result.setMinHeight(50);
			result.setAlignment(Pos.CENTER_RIGHT);

			// TODO(you): attach this class as a listener for Model so you can update the result
			model.attach(this);

			GridPane keypad = new GridPane();
			for(int row = 0; row < keys.length; row++) {
				for (int col = 0; col < keys[row].length; col++) {
					Button b = new Button(keys[row][col]);
					GridPane.setColumnIndex(b, col);
					GridPane.setRowIndex(b, row);

					b.setMinWidth(50);

					// TODO(you): 0 key is special - make it wider !
					// HINT: use GridPane.setColumnSpan and b.setMinWidth
					if ("0".equals(b.getText())) {
						GridPane.setColumnSpan(b, row);
						b.setMinWidth(100);
						col++;
					}

					// TODO(you): make "this" the actionListener/eventHandler for button b.
					b.setOnAction(this);
					keypad.getChildren().add(b);

				}
			}

			// TODO(you): Create a new Vertical Box (@VBox)
			// TODO(you): Add the result and keypad to the vbox.
			// and replace the keypad with the vbox in the scene below
			VBox vb = new VBox(40);
			Scene scene = new Scene(vb);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			vb.getChildren().addAll(result,keypad);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Calculator");
			primaryStage.hide();
			primaryStage.show();


		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() instanceof Button) {
			Button b = (Button) event.getSource();
			model.handleInput(b.getText());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void update(double r	) {

		result.setFont(new Font("Cambria", 30));
		String s = String.valueOf((int)model.getop1());
		this.result.setText(s);

		if(model.getOPorNot() == true)
		{

			this.result.setText(s + " " + model.getOperator());

		}
		if (model.getop2() != -1)
		{

			this.result.setText(s + " " + model.getOperator() + " " + (int)model.getop2());
		}


		String op2 = String.valueOf((int)model.getop2());
		if (CalculatorModel.equal == true)
		{
			String result = String.valueOf(model.getResult());
			//System.out.println(model.getOperator() + " " + model.getop1() + " " + model.getop2( )+ "!!!!" + model.getResult());
			this.result.setText(result);
			if (model.count >0 && model.getOPorNot())
			{
				this.result.setText(model.getop1()+ " " + model.getOperator());
			}
			if(model.count >0 && model.getOPorNot() && model.getop2() != -1 )
			{
				this.result.setText(model.getop1() + " " + model.getOperator() + " " + model.getop2());
			}
		}

	}
}
