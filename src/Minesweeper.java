import java.util.Random;
import java.util.Scanner;

public class Minesweeper {
    private final int size;
    private final int[][] hiddenBoard;
    private final String[][] visibleBoard;
    private final int numMines;

    public Minesweeper(int size, int numMines) {
        this.size = size;
        this.hiddenBoard = new int[size][size];
        this.visibleBoard = new String[size][size];
        this.numMines = numMines;
        initializeBoard(numMines);
    }

    private void fillMatrix() {
        for (int i = 0; i < this.hiddenBoard.length; i++) {
            for (int j = 0; j < this.hiddenBoard[0].length; j++) {
                this.hiddenBoard[i][j] = -2;
            }
        }

        for (int i = 0; i < this.visibleBoard.length; i++) {
            for (int j = 0; j < this.visibleBoard[0].length; j++) {
                this.visibleBoard[i][j] = "?";
            }
        }
    }

    private void checkBombs() {
        for (int i = 0; i < this.hiddenBoard.length; i++) {
            for (int j = 0; j < this.hiddenBoard[0].length; j++) {
                if (this.hiddenBoard[i][j] != -1) {
                    this.hiddenBoard[i][j] = countAdjacentMines(i, j);
                }
            }
        }
    }

    private int countAdjacentMines(int row, int col) {
        int count = 0;

        int[] px = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] py = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < px.length; i++) {
            int newRow = row + px[i];
            int newCol = col + py[i];

            if (isValidPosition(newRow, newCol) && this.hiddenBoard[newRow][newCol] == -1) {
                count++;
            }
        }
        return count;
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < this.hiddenBoard.length && col >= 0 && col < this.hiddenBoard[0].length;
    }

    private void initializeBoard(int numMines) {
        int deployedMines = 0;
        int maxValue = this.size;
        Random random = new Random();
        fillMatrix();

        while (deployedMines < numMines) {
            int row = random.nextInt(maxValue);
            int col = random.nextInt(maxValue);

            if (this.hiddenBoard[row][col] == -2) {
                this.hiddenBoard[row][col] = -1;
                deployedMines++;
            }
        }

        checkBombs();
    }

    public void printHiddenBoard(){
        for (int[] ints : this.hiddenBoard) {
            for (int j = 0; j < this.hiddenBoard[0].length; j++) {
                String formattedElement = String.format("%2s", ints[j]);
                System.out.print(formattedElement);
                if (j < this.hiddenBoard[0].length - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    private void printBoard() {
        for (int i = 0; i < this.visibleBoard.length; i++) {
            for (int j = 0; j < this.visibleBoard[0].length; j++) {
                String formattedElement = String.format("%2s", this.visibleBoard[i][j]);
                System.out.print(formattedElement);

                if (j < this.visibleBoard[0].length - 1) {
                    System.out.print(" ");
                } else if (j == this.visibleBoard.length - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.print(i);
            System.out.println();
        }
        for (int i = 0; i < this.visibleBoard.length; i++) {
            String formattedElement = String.format("%2s", "-");
            System.out.print(formattedElement);
            if (i < this.visibleBoard.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
        for (int i = 0; i < this.visibleBoard.length; i++) {
            String formattedElement = String.format("%2s", i);
            System.out.print(formattedElement);
            if (i < this.visibleBoard.length - 1) {
                System.out.print(" ");
            }
        }
    }

    private boolean checkCoords(int x, int y){
        if (this.hiddenBoard[y][x] == -1){
            return true;
        }else {
            this.visibleBoard[y][x] = String.valueOf(this.hiddenBoard[y][x]);
            return false;
        }
    }
    public void startGame() {
        boolean isGameOver;
        Scanner sc = new Scanner(System.in);
        int xCoord;
        int yCoord;
        int correctAnswers = 0;
        do {
            for (int i = 0; i < (this.visibleBoard.length * 3) + 3; i++) {
                System.out.print("=");
            }
            System.out.println();
            printBoard();
            System.out.println();
            for (int i = 0; i < (this.visibleBoard.length * 3) + 3; i++) {
                System.out.print("=");
            }
            System.out.println();
            System.out.println("Escribe la coordenada x (horizontal)");
            xCoord = sc.nextInt();
            System.out.println("Escribe la coordenada y (vertical)");
            yCoord = sc.nextInt();

            correctAnswers++;
            isGameOver = checkCoords(xCoord,yCoord);
            if (isGameOver){
                System.out.println("Perdiste");
            } else if (correctAnswers == this.numMines) {
                System.out.println("Ganaste");
                isGameOver = true;
            }
        } while (!isGameOver);
    }
}
