package tanque;

import java.util.Random;

public class Tank implements Runnable {
    private static final int MAX_HEALTH = 100;
    private static final int FIELD_SIZE = 20;

    private String name;
    private int x;
    private int y;
    private int health;
    private Battlefield battlefield;
    private Random random;

    public Tank(String name, Battlefield battlefield) {
        this.name = name;
        this.battlefield = battlefield;
        this.health = MAX_HEALTH;
        this.random = new Random();
        // Posición inicial aleatoria
        this.x = random.nextInt(FIELD_SIZE);
        this.y = random.nextInt(FIELD_SIZE);
    }

    public String getName() {
        return name;
    }

    public synchronized int getHealth() {
        return health;
    }
    
    public int getX() {
    	return x;
    }
    
    public int getY() {
    	return y;
    }

    public synchronized void reduceHealth(int amount) {
        health -= amount;
        if (health < 0) health = 0;
    }

    public void run() {
        while (health > 0 && battlefield.isGameRunning()) {
            move();
            attack();
            try {
                Thread.sleep(500); // Simula el tiempo entre acciones
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(name + " ha sido destruido.");
        battlefield.removeTank(this);
    }

    private void move() {
        // Movimiento aleatorio
        x += random.nextInt(3) - 1; // -1, 0 o 1
        y += random.nextInt(3) - 1;

        // Asegurar que el tanque se mantiene dentro del campo
        x = Math.max(0, Math.min(x, FIELD_SIZE - 1));
        y = Math.max(0, Math.min(y, FIELD_SIZE - 1));
    }

    private void attack() {
        // Buscar tanques enemigos en el campo de batalla
        for (Tank enemy : battlefield.getTanks()) {
            if (enemy != this && isInRange(enemy)) {
                synchronized (enemy) {
                    enemy.reduceHealth(10);
                    System.out.println(name + " ataca a " + enemy.getName() + " (Salud restante: " + enemy.getHealth() + ")");
                    if (enemy.getHealth() <= 0) {
                        System.out.println(enemy.getName() + " ha sido destruido por " + name);
                    }
                }
            }
        }
    }

    private boolean isInRange(Tank enemy) {
        // Determina si el enemigo está en un rango de ataque (por ejemplo, 3 unidades)
        int distance = Math.abs(x - enemy.x) + Math.abs(y - enemy.y);
        return distance <= 3;
    }
}