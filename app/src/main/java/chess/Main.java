package chess;

import chess.model.Board;
import chess.model.Position;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        Position position = new Position(board);
        String fen = "rnb2rk1/pp2ppbp/6p1/q1p5/2BPP3/2P1B3/P3NPPP/R2Q1RK1 b - - 3 10";
        System.out.println(fen);
        position.fromFEN(fen);
        System.out.println(position.toFEN());
        System.out.println(Position.STARTING_POSITION);
        position.fromFEN(Position.STARTING_POSITION);
        System.out.println(position.toFEN());
    }

}
