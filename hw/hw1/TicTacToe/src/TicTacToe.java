import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TicTacToe {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		welcomeMessage();
		char[][] gameBoard = new char[][] {{'+', '-', '-', '+', '-', '-', '+', '-', '-', '+'},
							               {'|', '1', ' ', '|', '2', ' ', '|', '3', ' ', '|'},
							               {'+', '-', '-', '+', '-', '-', '+', '-', '-', '+'},
							               {'|', '4', ' ', '|', '5', ' ', '|', '6', ' ', '|'},
							               {'+', '-', '-', '+', '-', '-', '+', '-', '-', '+'},
							               {'|', '7', ' ', '|', '8', ' ', '|', '9', ' ', '|'},
							               {'+', '-', '-', '+', '-', '-', '+', '-', '-', '+'}};
		int O = 0;
		int X = 0;
							      
		printGameBoard(gameBoard);
		clearGameBoard(gameBoard);
		playGame(gameBoard, O, X);
		return;
	}
	
	
	public static void playGame(char[][] gameBoard, int O, int X) {
		System.out.println("Please choose O or X plays first:");
		char player = playFirst();
		char winner = ' '; 
		int steps = 0;
		Scanner sc =  new Scanner(System.in);
		System.out.println("Player " + player + " plays first.");
		printGameBoard(gameBoard);
		
		//System.out.println("Player " + Character.toString(player) + " plays first. Please enter a 1-9 number:\n");
		
		while (winner == ' ') {
			int placeSuc = 0;
			System.out.println("Player " + Character.toString(player) + "'s turn. Please enter a 1-9 number:\n");
			int input;
			try {
				input = sc.nextInt();
				if(input <= 0 || input >= 10) {
					System.out.println("Invalid input. Please enter a number between 1-9:");
					continue;
				}
			} catch(InputMismatchException e) {
				System.out.println("Invalid input. Please enter a number between 1-9:");
				sc.next();
				continue;
			}
			placeSuc = placeInput(input, gameBoard, player);
			printGameBoard(gameBoard);
			if(placeSuc == 1) {
				steps += 1;
				winner = checkWinner(gameBoard, player, steps);
				player = switchPlayer(player);
			}
		}
		
		if(winner == 'd') {
			System.out.println("Game Over! It's a draw!");
			System.out.println("Player O's point:" + O);
			System.out.println("Player X's point:" + X);
		} else {
			if(winner == 'O') {
				O += 1;
			} else {
				X += 1;
			}
		System.out.println("Player " + winner +" wins! Congratulations!");
		System.out.println("Player O's point:" + O);
		System.out.println("Player X's point:" + X);
		}
		
		//check one more time
		if(oneMoreTime()) {
			clearGameBoard(gameBoard);
			playGame(gameBoard, O, X);
		} else {
			System.out.print("Game ends! ");
			if(O > X) {
				System.out.print("Player O is the final winner!");
			} else if (O < X) {
				System.out.print("Player X is the final winner!");
			} else {
				System.out.println("It's a draw! ");
			}
			System.out.println("Thanks for using TicTacToe! See you next time!");
			return;
		}
	}//playGame ends
	
	
	public static void welcomeMessage() {
		System.out.println("Welcome to TicTacToe! Have fun!");
		System.out.println("Here is the game board:");
	}
	
	public static void printGameBoard(char[][] gameBoard) {
		for(char[] row: gameBoard) {
			for(char c: row) {
				System.out.print(c);
			}
			System.out.println();
		}
				
	}
	
	public static void clearGameBoard(char[][] gameBoard) {
		for(int i = 1; i <= 5; i += 2) {
			for(int j = 1; j <= 7; j += 3) {
				gameBoard[i][j] = ' ';
			}
		}
	}
	
	public static char playFirst() {
		Scanner sc =  new Scanner(System.in);
		String input = null;
		while(true) {
			try {
				input = sc.next();
				if(!(input.equals(Character.toString('O')) || input.equals(Character.toString('X')))) {
					System.out.println("a Invalid input! Please enter 'O' or 'X':");
				    continue;
				}
			} catch (NoSuchElementException e) {
				System.out.println("Invalid input! Please enter 'O' or 'X':");
			}
			return input.charAt(0);
		}
	}
	
	public static int placeInput(int input, char[][] gameBoard, char player) {
		switch (input) {
		case 1:
			if(gameBoard[1][1] != ' ') {
				System.out.println("This slot is already occupied! Please re-enter a valid number:");
				return 0;
			} else {
				gameBoard[1][1] = player;
				return 1;
			}
		case 2:
			if(gameBoard[1][4] != ' ') {
				System.out.println("This slot is already occupied! Please re-enter a valid number:");
				return 0;
			} else {
				gameBoard[1][4] = player;
				return 1;
			}
		case 3:
			if(gameBoard[1][7] != ' ') {
				System.out.println("This slot is already occupied! Please re-enter a valid number:");
				return 0;
			} else {
				gameBoard[1][7] = player;
				return 1;
			}
		case 4:
			if(gameBoard[3][1] != ' ') {
				System.out.println("This slot is already occupied! Please re-enter a valid number:");
				return 0;
			} else {
				gameBoard[3][1] = player;
				return 1;
			}
		case 5:
			if(gameBoard[3][4] != ' ') {
				System.out.println("This slot is already occupied! Please re-enter a valid number:");
				return 0;
			} else {
				gameBoard[3][4] = player;
				return 1;
			}
		case 6:
			if(gameBoard[3][7] != ' ') {
				System.out.println("This slot is already occupied! Please re-enter a valid number:");
				return 0;
			} else {
				gameBoard[3][7] = player;
				return 1;
			}
		case 7:
			if(gameBoard[5][1] != ' ') {
				System.out.println("This slot is already occupied! Please re-enter a valid number:");
				return 0;
			} else {
				gameBoard[5][1] = player;
				return 1;
			}
		case 8:
			if(gameBoard[5][4] != ' ') {
				System.out.println("This slot is already occupied! Please re-enter a valid number:");
				return 0;
			} else {
				gameBoard[5][4] = player;
				return 1;
			}
		case 9:
			if(gameBoard[5][7] != ' ') {
				System.out.println("This slot is already occupied! Please re-enter a valid number:");
				return 0;
			} else {
				gameBoard[5][7] = player;
				return 1;
			}
		default: return 0;
		}
	}
	
	public static char switchPlayer(char player) {
		if(player == 'O') {
			player = 'X';
		} else {
			player = 'O';
		}
		return player;
	}
	
	public static char checkWinner(char[][] gameBoard, char player, int steps) {
		if ((gameBoard[1][1] == player && gameBoard[1][4] == player && gameBoard[1][7] == player) ||
			(gameBoard[1][1] == player && gameBoard[3][1] == player && gameBoard[5][1] == player) ||
			(gameBoard[1][1] == player && gameBoard[3][4] == player && gameBoard[5][7] == player) ||
			(gameBoard[3][1] == player && gameBoard[3][4] == player && gameBoard[3][7] == player) ||
			(gameBoard[5][1] == player && gameBoard[5][4] == player && gameBoard[5][7] == player) ||
			(gameBoard[1][4] == player && gameBoard[3][4] == player && gameBoard[5][4] == player) ||
			(gameBoard[1][7] == player && gameBoard[3][7] == player && gameBoard[5][7] == player) ||
			(gameBoard[1][7] == player && gameBoard[3][4] == player && gameBoard[5][1] == player)) {
			return player;
		} else if(steps == 9) {
			return 'd';
		} else {
			return ' ';
		}
	}
	
	public static boolean oneMoreTime() {
		System.out.println("One more time?(Y/N):");
		Scanner sc = new Scanner(System.in);
		String s = "";
		while(true) {
			try {
				s = sc.nextLine();
				if(!(s.equalsIgnoreCase("Yes") ||
				     s.equalsIgnoreCase("Y")   ||
				     s.equalsIgnoreCase("No")  ||
				     s.equalsIgnoreCase("N"))) {
					System.out.println("Invalid input! Please enter Y/N:");
					continue;
				}
			} catch (NoSuchElementException e) {
				System.out.println("Invalid input! Please enter Y/N:");
				continue;
			}
			if(s.equalsIgnoreCase("Yes") || s.equalsIgnoreCase("Y")) {
				return true;
			} else {
				return false;
			}
		}
		}

}
