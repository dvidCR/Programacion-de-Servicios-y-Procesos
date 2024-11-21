package tanque;

import java.util.Scanner;

public class Juego {
    public static void main(String[] args) {
        Battlefield battlefield = new Battlefield();
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Que nombre le quieres poner al primer tanque: ");
        String nombre1 = sc.nextLine();
        
        System.out.print("Que nombre le quieres poner al segundo tanque: ");
        String nombre2 = sc.nextLine();
        
        System.out.print("Que nombre le quieres poner al tercer tanque: ");
        String nombre3 = sc.nextLine();
        
        sc.close();
        
        // Crear tanques
        Tank tank1 = new Tank(nombre1, battlefield);
        Tank tank2 = new Tank(nombre2, battlefield);
        Tank tank3 = new Tank(nombre3, battlefield);

        // Añadir tanques al campo de batalla
        battlefield.addTank(tank1);
        battlefield.addTank(tank2);
        battlefield.addTank(tank3);

        // Iniciar hilos
        Thread t1 = new Thread(tank1);
        Thread t2 = new Thread(tank2);
        Thread t3 = new Thread(tank3);

        t1.start();
        t2.start();
        t3.start();

        // Esperar a que termine el juego
        while (battlefield.isGameRunning()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Fin del juego.");
    }
}