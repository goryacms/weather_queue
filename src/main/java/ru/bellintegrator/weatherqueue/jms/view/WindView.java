package ru.bellintegrator.weatherqueue.jms.view;

public class WindView {
    private int chill;

    private int direction;

    private int speed;

    public WindView(int chill, int direction, int speed) {
        this.chill = chill;
        this.direction = direction;
        this.speed = speed;
    }

    public WindView() {
    }

    public int getChill() {
        return chill;
    }

    public void setChill(int chill) {
        this.chill = chill;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
