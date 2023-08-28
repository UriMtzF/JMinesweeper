import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int mOpt;
        int opt;
        int bombs;
        do {
            System.out.println("Selecciona una opción");
            System.out.println("1. Jugar");
            System.out.println("2. Salir");
            mOpt = sc.nextInt();
            if (mOpt == 1){
                do {
                    System.out.println("Escribe el tamaño de la cuadrícula");
                    opt = sc.nextInt();
                }while(opt <= 0 || opt > 10);
                do {
                    System.out.println("Escribe la cantidad de bombas");
                    System.out.println("Si escribes 0 se pondrá una dificultad normal");
                    bombs = sc.nextInt();
                }while (bombs>Math.pow(opt,2));
                if (bombs == 0){
                    bombs = opt * 2;
                }
                Minesweeper minesweeper = new Minesweeper(opt,bombs);
                minesweeper.startGame();
            }
        }while(mOpt !=2);
    }
}