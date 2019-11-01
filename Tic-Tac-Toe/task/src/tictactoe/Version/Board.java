package tictactoe.Version;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Board {
    String[] board;

    // Create empty board
    public Board() throws IOException {
        this.board = new String[]{" ", " ", " ", " ", " ", " ", " ", " ", " "};
    }

    //Print current board status
    public void boardPrint() throws IOException {

        String edge = "---------";
        System.out.println(edge);
        System.out.println("| " + board[0] + " " + board[1] + " " + board[2] + " |");
        System.out.println("| " + board[3] + " " + board[4] + " " + board[5] + " |");
        System.out.println("| " + board[6] + " " + board[7] + " " + board[8] + " |");
        System.out.println(edge);
    }


    //check board for winner/error/draw/finished
    public String boardStatus() throws IOException {

        String result = "";
        ArrayList arrayList = new ArrayList();
        if (board[0].equals("X") && board[1].equals("X") && board[2].equals("X")
                || board[3].equals("X") && board[4].equals("X") && board[5].equals("X")
                || board[6].equals("X") && board[7].equals("X") && board[8].equals("X")
                || board[0].equals("X") && board[3].equals("X") && board[6].equals("X")
                || board[1].equals("X") && board[4].equals("X") && board[7].equals("X")
                || board[2].equals("X") && board[5].equals("X") && board[8].equals("X")
                || board[0].equals("X") && board[4].equals("X") && board[8].equals("X")
                || board[2].equals("X") && board[4].equals("X") && board[6].equals("X")
        ) {
            arrayList.add("X wins");
        }
        if (board[0].equals("O") && board[1].equals("O") && board[2].equals("O")
                || board[3].equals("O") && board[4].equals("O") && board[5].equals("O")
                || board[6].equals("O") && board[7].equals("O") && board[8].equals("O")
                || board[0].equals("O") && board[3].equals("O") && board[6].equals("O")
                || board[1].equals("O") && board[4].equals("O") && board[7].equals("O")
                || board[2].equals("O") && board[5].equals("O") && board[8].equals("O")
                || board[0].equals("O") && board[4].equals("O") && board[8].equals("O")
                || board[2].equals("O") && board[4].equals("O") && board[6].equals("O")) {
            arrayList.add("O wins");
        }
        if (arrayList.size() == 2) {
            result = "Impossible";
        }
        if (arrayList.size() == 1) {
            //Если победитель найден один
            result = String.valueOf(arrayList.get(0));
        }
        if (arrayList.size() == 0) {
            //Если нет победителя
            StringBuilder stringBuilderX = new StringBuilder();
            StringBuilder stringBuilderO = new StringBuilder();
            StringBuilder stringBuilderEmpty = new StringBuilder();
            String line = "";
            //Если каких то объектов больше чем должно быть
            Arrays.asList(board).forEach(s -> {
                        if (s.equals("X")) {
                            stringBuilderX.append(s);
                        } else if (s.equals("O")) {
                            stringBuilderO.append(s);
                        } else if (s.equals(" ")) {
                            stringBuilderEmpty.append(s);
                        }
                    }

            );


            if ((Math.abs((stringBuilderX.length() - stringBuilderO.length())) > 1) && (stringBuilderEmpty.length() >= 1)) {
                result = "Impossible";
            }
            if (Math.abs((stringBuilderX.length() - stringBuilderO.length())) > 1) {
                result = "Impossible";
            }

            if (stringBuilderX.length() == 5 && stringBuilderO.length() == 4) {
                result = "Draw";
            }
            if (stringBuilderEmpty.length() >= 1) {
                if (result.isBlank()) {
                    result = "Game not finished";
                }
            }

        }
        return result;

    }


    //check cell inputted coordinates and return it's index
    public int getCellIndex() {
        int index = 0;
        System.out.println("Enter the coordinates: ");
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                if (x <= 3 && x > 0 && y <= 3 && y > 0) {
                    String s = x + " " + y;
                    switch (s) {
                        case "1 3":
                            index = 0;
                            break;
                        case "2 3":
                            index = 1;
                            break;
                        case "3 3":
                            index = 2;
                            break;
                        case "1 2":
                            index = 3;
                            break;
                        case "2 2":
                            index = 4;
                            break;
                        case "3 2":
                            index = 5;
                            break;
                        case "1 1":
                            index = 6;
                            break;
                        case "2 1":
                            index = 7;
                            break;
                        case "3 1":
                            index = 8;
                            break;
                    }
                    break;
                }
                if (x > 3 || x < 0 || y > 3 || y < 0) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    System.out.println("Enter the coordinates: ");
                }

            } catch (InputMismatchException ex) {
                System.out.println("You should enter numbers!");
                System.out.println("Enter the coordinates: ");
            }
        }
        return index;
    }

    //place cell
    public void makeMove(String player) {
        try {
            boardPrint();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            int cell = getCellIndex();
            if (board[cell].equals("X")) {
                System.out.println("This cell is occupied! Choose another one!");
            }
            if (board[cell].equals("O")) {
                System.out.println("This cell is occupied! Choose another one!");
            }
            if (board[cell].isBlank()) {
                board[cell] = player;
                break;
            }
        }

    }

    @SuppressWarnings("Duplicates")
    public void boardEngine() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input first player: X or O");
        String firstPlayer, secondPlayer = "";
        firstPlayer = bufferedReader.readLine();
        if (firstPlayer.equals("X")) {
            secondPlayer = "O";
        }
        if (firstPlayer.equals("O")) {
            secondPlayer = "X";
        }
        /*String firstPlayer = "X", secondPlayer = "O";*/
        while (true) {
            makeMove(firstPlayer);
            if (boardStatus().equals("Draw") || boardStatus().equals("O wins") || boardStatus().equals("X wins")) {
                boardPrint();
                System.out.println(boardStatus());
                break;
            }
            makeMove(secondPlayer);
            if (boardStatus().equals("Draw") || boardStatus().equals("O wins") || boardStatus().equals("X wins")) {
                boardPrint();
                System.out.println(boardStatus());
                break;
            }
        }
    }


}

