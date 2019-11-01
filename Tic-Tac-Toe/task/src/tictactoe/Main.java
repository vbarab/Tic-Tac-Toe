package tictactoe;

import tictactoe.Version.Board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        Board board = new Board(); // creating new board
        board.boardEngine();

    }
}
