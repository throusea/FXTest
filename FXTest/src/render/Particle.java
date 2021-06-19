package render;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Particle extends Circle implements Runnable {

    static int RANDOM = 1;

    private Random random = new Random();

    ParticleListener listener;

    ArrayList<Object> arrayList = new ArrayList<>();

    private double mass;

    private Vector2d s, v, a;

    private int remainingTime;

    private Point position;

    private Timeline timeline;

    //private boolean

    public Particle(double mass, Point point) {
        super(point.x, point.y, 1);
        this.mass = mass;
        this.position = point;
    }

    public Particle() {
        super(0,0, 1);
        this.position = new Point(0,0);
        this.remainingTime = 10;
        v = new Vector2d(1, 1);
    }

    public Particle(int Module) {
        super();
        if(Module == RANDOM) {
            this.position = new Point(0,0);
            this.remainingTime = 50;
            a = new Vector2d(0,0);
            double r = random.nextDouble() * 200 - 100;
            double d = random.nextDouble() * 2 * Math.PI;
            v = new Vector2d(r * Math.cos(d),r * Math.sin(d));
            s = new Vector2d(position);
            setCenterX(position.x);
            setCenterY(position.y);
            setRadius(2);
            setOpacity(0);
        }
    }

    public Particle(Vector2d a, Vector2d v, Vector2d s) {
        super();
        this.a = a;
        this.v = v;
        this.s = s;
        this.position = new Point((int)s.x, (int)s.y);
        setRadius(2);
        setOpacity(0);
    }

    public Vector2d getAccelerate() {
        return a;
    }

    public Timeline getTimeline() { return timeline; }

    public void setInitPosition(Point p) {
        position.setLocation(p);
        s.set(p);
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public void setAccelerate(Vector2d a) {
        this.a = a;
    }

    public void setRemainingTime(int remainingTime) { this.remainingTime = remainingTime; }

    public void setInitFrame(int x, int y) {
        addKeyFrame(Duration.ZERO, new KeyValue(this.translateXProperty(), x));
        addKeyFrame(Duration.ZERO, new KeyValue(this.translateYProperty(), y));
        addKeyFrame(Duration.ZERO, new KeyValue(this.opacityProperty(), 1));
    }

    private void setFrame() {
        Vector2d dv = new Vector2d(), dx = new Vector2d();
        int t = remainingTime;
        while(t-- >= 0) {
            int t0 = remainingTime - t;
            dv.set(a.multiple(0.1f));
            v.add(dv);
            dx.set(v.multiple(0.1f));
            s.add(dx);
            position.setLocation(s.getPoint());
            arrayList.forEach(listener -> drawPath(listener, t0 * 100));
        }
    }

    public void setEndFrame() {
        addKeyFrame(timeline.getTotalDuration(), new KeyValue(this.opacityProperty(), 1));
        addKeyFrame(timeline.getTotalDuration().add(new Duration(1)), new KeyValue(this.opacityProperty(), 0));
    }

    public void addListener(Object o) {
        arrayList.add(o);
        if(o instanceof Pane) {
            ((Pane) o).getChildren().add(this);
        }
    }

    public void drawPath(Object o, int millis) {
        if(o instanceof Pane) {
            KeyValue keyValueX = new KeyValue(this.translateXProperty(), position.x);
            KeyValue keyValueY = new KeyValue(this.translateYProperty(), position.y);
            addKeyFrame(millis, keyValueX, keyValueY);
        }
    }

    private void addKeyFrame(int millis, KeyValue keyValue) {
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(millis), keyValue));
    }

    private void addKeyFrame(int millis, KeyValue keyValue1, KeyValue keyValue2) {
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(millis), keyValue1, keyValue2));
    }

    private void addKeyFrame(Duration duration, KeyValue keyValue) {
        addKeyFrame((int)duration.toMillis(), keyValue);
    }

    private void addKeyFrame(Duration duration, KeyValue keyValue1, KeyValue keyValue2) {
        addKeyFrame((int) duration.toMillis(), keyValue1, keyValue2);
    }

    public void hit() {
        listener.hit();
    }

    @Override
    public void run() {
        timeline = new Timeline();
        setInitFrame(position.x, position.y);
        setFrame();
        setEndFrame();
    }

    void register(ParticleListener listener) {
        this.listener = listener;
    }
}
