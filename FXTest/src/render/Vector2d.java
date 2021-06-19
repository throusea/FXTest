package render;

import com.sun.javafx.geom.Vec2d;

import java.awt.*;

public class Vector2d extends Vec2d {

    public Vector2d(double x, double y) {
        super(x,y);
    }

    public Vector2d() {
        x = y = 0;
    }

    public Vector2d(Point p) { super(p.x, p.y);}

    public Vector2d(int module) {
    }

    public void add(Vector2d vector2d) {
        x += vector2d.x;
        y += vector2d.y;
    }

    public Vector2d multiple(double t) {
        return new Vector2d(x * t, y * t);
    }

    public double cross(Vector2d vector2d) {
        return x * vector2d.y - y * vector2d.x;
    }

    public double dot(Vector2d vector2d) {
        return x * vector2d.x + y * vector2d.y;
    }

    public Point getPoint() {
        return new Point((int) x,(int) y);
    }

    public void set(Point p) {
        x = p.x;
        y = p.y;
    }
}
