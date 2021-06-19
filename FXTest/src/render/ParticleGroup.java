package render;

import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import render.system.DSystem;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ParticleGroup implements Runnable, ParticleListener {
    static private final int PARALLEL_MODE = 1, SEQUENTIAL_MODE = 2;

    private List<Particle> particles = new ArrayList<>();

    private List<Object> arrayList = new ArrayList<>();

    private List<DSystem> systemList = new ArrayList<>();

    private Transition transition;

    private int transitionModule = 1;

    public ParticleGroup(int particleNumber, Object o) {
        for(int i = 0; i < particleNumber; i++)
            particles.add(new Particle(Particle.RANDOM));

        particles.forEach(particle -> particle.register(this));
        arrayList.add(o);
    }

    public Transition getTransition() { return transition; }

    public void setTime(int time) {
        particles.forEach(particle -> particle.setRemainingTime(time));
    }

    public void addParticle(Particle particle) { particles.add(particle); }

    public void addSystem(DSystem system) {
        systemList.add(system);
    }

    public boolean addGroup(ParticleGroup group, int transitionModule) {
        if(transition == null || group.transition == null) return false;
        Transition transition = new ParallelTransition();
        if(transitionModule == PARALLEL_MODE) {
            transition = new ParallelTransition();
            ((ParallelTransition) transition).getChildren().addAll(this.transition, group.transition);
        }
        if(transitionModule == SEQUENTIAL_MODE) {
            transition = new SequentialTransition();
            ((SequentialTransition) transition).getChildren().addAll(this.transition, group.transition);
        }
        this.transition = transition;

        particles.addAll(group.particles);
        return true;
    }

    public void setParticlePosition(Point point) {
        particles.forEach(particle -> particle.setInitPosition(point));
    }

    public void setTransition(int transitionModule) {
        this.transitionModule = transitionModule;
    }

    private void addEffect(DSystem system) {
        particles.forEach(particle -> system.affect(particle));
    }

    private void addParent(Object o) {
        if(o instanceof Pane) {
            particles.forEach(particle -> particle.addListener((Pane) o));
        }
    }

    private void createTimeline() {
        particles.forEach(particle -> particle.run());
    }

    private void createTransition() {
        if(transitionModule == PARALLEL_MODE) {
            transition = new ParallelTransition();
            particles.forEach(particle -> {
                ((ParallelTransition) transition).getChildren().add(particle.getTimeline());
            });
        }else if (transitionModule == SEQUENTIAL_MODE) {
            transition = new SequentialTransition();
            particles.forEach(particle -> {
                ((SequentialTransition) transition).getChildren().add(particle.getTimeline());
            });
        }
    }

    public void play() { transition.play(); }

    @Override
    public void run() {
        systemList.forEach(listener -> addEffect(listener));
        arrayList.forEach(listener -> addParent(listener));

        createTimeline();
        createTransition();
    }

    @Override
    public void hit() {
        //TODO: do it later;
    }
}
