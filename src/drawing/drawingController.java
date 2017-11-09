package drawing;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class drawingController 
{
	@FXML
	Canvas canvas;
	
	public void initialize()
	{
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		canvas.setOnMouseDragged(e -> {
			double size = 4;
			double x = e.getX() - size/2;
			double y = e.getY() - size/2;
			
			//gc.setFill(p);
			gc.fillRect(x, y, size, size);
		});
	}

	public void start(Stage stage) {
		// TODO Auto-generated method stub
		
	}
}
