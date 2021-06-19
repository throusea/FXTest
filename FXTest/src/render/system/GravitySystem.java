package render.system;

import render.Particle;
import render.Vector2d;

public class GravitySystem extends DSystem {

    Vector2d gravity = new Vector2d(0, 98);

    public GravitySystem() {
        super();
        a = gravity;
    }

    public void affect(Particle particle) {
        particle.getAccelerate().add(a);
    }
}
