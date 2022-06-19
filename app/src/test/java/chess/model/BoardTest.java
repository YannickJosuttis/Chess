package chess.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BoardTest {


    @Test
    public void createEmptyBoardTest() {
       
        Board board = new Board();

        for (int i = 0; i < Board.NUMBER_OF_RANKS; i++) {
            for (int j = 0; j < Board.NUMBER_OF_FILES; j++) {
                assertEquals(null, board.getSquare(j, i).getPiece());
            }
        }
    }
    
}
