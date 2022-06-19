package chess.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import chess.model.piece.properties.Color;
import chess.model.piece.utils.PieceUtils;

public class PositionTest {

    @Test
    public void startingPositionTest(){
        Position actualPosition = new Position(new Board());
        actualPosition.fromFEN(Position.STARTING_POSITION);

        Board expectedBoard = new Board();
        Position expectedPosition = new Position(expectedBoard);
       
        // Black pieces
        expectedBoard.getSquare(0, 0).setPiece(PieceUtils.fromChar('r'));
        expectedBoard.getSquare(1, 0).setPiece(PieceUtils.fromChar('n'));
        expectedBoard.getSquare(2, 0).setPiece(PieceUtils.fromChar('b'));
        expectedBoard.getSquare(3, 0).setPiece(PieceUtils.fromChar('q'));
        expectedBoard.getSquare(4, 0).setPiece(PieceUtils.fromChar('k'));
        expectedBoard.getSquare(5, 0).setPiece(PieceUtils.fromChar('b'));
        expectedBoard.getSquare(6, 0).setPiece(PieceUtils.fromChar('n'));
        expectedBoard.getSquare(7, 0).setPiece(PieceUtils.fromChar('r'));

        expectedBoard.getSquare(0, 1).setPiece(PieceUtils.fromChar('p'));
        expectedBoard.getSquare(1, 1).setPiece(PieceUtils.fromChar('p'));
        expectedBoard.getSquare(2, 1).setPiece(PieceUtils.fromChar('p'));
        expectedBoard.getSquare(3, 1).setPiece(PieceUtils.fromChar('p'));
        expectedBoard.getSquare(4, 1).setPiece(PieceUtils.fromChar('p'));
        expectedBoard.getSquare(5, 1).setPiece(PieceUtils.fromChar('p'));
        expectedBoard.getSquare(6, 1).setPiece(PieceUtils.fromChar('p'));
        expectedBoard.getSquare(7, 1).setPiece(PieceUtils.fromChar('p'));

        // White pieces
        expectedBoard.getSquare(0, 6).setPiece(PieceUtils.fromChar('P'));
        expectedBoard.getSquare(1, 6).setPiece(PieceUtils.fromChar('P'));
        expectedBoard.getSquare(2, 6).setPiece(PieceUtils.fromChar('P'));
        expectedBoard.getSquare(3, 6).setPiece(PieceUtils.fromChar('P'));
        expectedBoard.getSquare(4, 6).setPiece(PieceUtils.fromChar('P'));
        expectedBoard.getSquare(5, 6).setPiece(PieceUtils.fromChar('P'));
        expectedBoard.getSquare(6, 6).setPiece(PieceUtils.fromChar('P'));
        expectedBoard.getSquare(7, 6).setPiece(PieceUtils.fromChar('P'));

        expectedBoard.getSquare(0, 7).setPiece(PieceUtils.fromChar('R'));
        expectedBoard.getSquare(1, 7).setPiece(PieceUtils.fromChar('N'));
        expectedBoard.getSquare(2, 7).setPiece(PieceUtils.fromChar('B'));
        expectedBoard.getSquare(3, 7).setPiece(PieceUtils.fromChar('Q'));
        expectedBoard.getSquare(4, 7).setPiece(PieceUtils.fromChar('K'));
        expectedBoard.getSquare(5, 7).setPiece(PieceUtils.fromChar('B'));
        expectedBoard.getSquare(6, 7).setPiece(PieceUtils.fromChar('N'));
        expectedBoard.getSquare(7, 7).setPiece(PieceUtils.fromChar('R'));
        
        expectedPosition.setActiveColor(Color.WHITE);
        expectedPosition.setWhiteKingsideCastleAvailability(true);
        expectedPosition.setWhiteQueensideCastleAvailability(true);
        expectedPosition.setBlackKingsideCastleAvailability(true);
        expectedPosition.setBlackQueensideCastleAvailability(true);
        expectedPosition.setEnPassantTarget(null);
        expectedPosition.setHalfMoveClock(0);
        expectedPosition.setMoveNumber(1);

        assertEquals(expectedPosition, actualPosition);
    }

    @Test
    public void fromFENtoFENStartingPositionTest(){
        Position position = new Position(new Board());
        position.fromFEN(Position.STARTING_POSITION);
        String actualFEN = position.toFEN();
        assertEquals(Position.STARTING_POSITION, actualFEN);
    }

    @Test
    public void fromFENtoFENTest(){
        Position position = new Position(new Board());
        String fen1 = "rnb2rk1/pp2ppbp/6p1/q1p5/2BPP3/2P1B3/P3NPPP/R2Q1RK1 b - - 3 10";
        String fen2 = "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1";
        String fen3 = "r1bq1rk1/ppp1bp1p/5np1/4p3/4B1Q1/N1P1P3/PP1P2PP/R1B2RK1 w Qq - 0 1";

        String[] fens = {fen1, fen2, fen3};
        for(String fen : fens){
            position.fromFEN(fen);
            String actualFEN = position.toFEN();
            assertEquals(fen, actualFEN);
        }     
    }
}
