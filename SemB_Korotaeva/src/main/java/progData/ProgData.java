package progData;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import spravaOblasti.SpravaOblasti;

public class ProgData extends Application {
    private static final SpravaOblasti spravaOblasti = new SpravaOblasti();
    private ControlPanelHBox hBox;
    
    private static final int ROOT_WIDTH = 700;
    private static final int ROOT_HEIGHT = 500;
    private static final int SPACING = 10;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hbox = new HBox();
        hbox.setSpacing(SPACING);
        hbox.setFillHeight(true);
        hBox = new ControlPanelHBox(hbox, spravaOblasti);
        
        Scene scene = new Scene(hbox,ROOT_WIDTH,ROOT_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
