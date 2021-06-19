package render.system;

import render.Particle;

public class HitSystem extends DSystem{

    public HitSystem() {
        super();
    }

    @Override
    public void affect(Particle particle) {
        super.affect(particle);
        particle.hit();
    }
}
