package chess;

import chess.model.move.Move;

import static chess.model.piece.Piece.*;
import static chess.model.piece.properties.Color.*;

import chess.model.Position;

public class Main {

    public static void main(String[] args) {
    
    Position position = new Position();
    String fen = "r1b2rk1/pp2ppbp/2n3p1/2p5/2BPP3/1QP1B3/q3NPPP/2R2RK1 b - - 1 12";
    position.fromFen(fen);
    System.out.println(fen);
    System.out.println(position.toFen());

        int move0 = Move.defaultMove(WHITE, 0, 0, 3, 3);
        int move1 = Move.promotionCapture(WHITE, 1, 5, 2, 7, create(WHITE, QUEEN), create(BLACK, ROOK));
        int move2 = Move.castling(WHITE, 4, 7, 6, 7, 7, 7, 5, 7);
        int currentMove = move2;
        String string = Integer.toBinaryString(currentMove);
        int l = string.length();
        for (int index = 0; index < 32 - l; index++) {
            string = "0" + string;

        }
        System.out.println(string);
        System.out.println(Move.getType(currentMove));
        System.out.println(Move.getColor(currentMove));
        System.out.println(Move.getPromotedPiece(currentMove));
    }

}
