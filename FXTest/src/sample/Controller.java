package sample;

import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import render.Particle;
import render.ParticleGroup;
import render.Vector2d;
import render.system.GravitySystem;
import render.system.HitSystem;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Controller implements Initializable {

    @FXML
    public Button myButton;

    @FXML
    public TextField myTextField;

    @FXML
    public AnchorPane pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void showParticleGroup(MouseEvent event) {
        ParticleGroup group0 = new ParticleGroup(0, pane);
        group0.addParticle(new Particle(new Vector2d(0,100), new Vector2d(0,-200), new Vector2d(0, 200)));
        group0.setTime(20);
        group0.run();
        ParticleGroup group = new ParticleGroup(100, pane);
        group.addSystem(new GravitySystem());
        group.addSystem(new HitSystem());
        group.run();
        group0.addGroup(group, 2);
        group0.play();
        //TODO: st.getChildren().add();
    }

    public void showDateTime(MouseEvent event) {
        Date now = new Date();

        DateFormat df = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
        String dateTimeString = df.format(now);

        myTextField.setText(dateTimeString);
    }
}
