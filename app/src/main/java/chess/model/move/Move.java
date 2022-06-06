package chess.model.move;

import chess.model.piece.Piece;
import chess.model.piece.properties.Color;

import static chess.model.move.MoveType.*;

public class Move {

    /*
     * 1. move type
     * 2. source square
     * 3. destination square
     * 4. meta data
     */

    private static final int TYPE_MASK = 0b111;
    private static final int COLOR_MASK = 0b11111;
    private static final int COORDINATE_MASK = 0b111;
    private static final int PIECE_MASK = 0b11111;

    private static final int TYPE_SHIFT = 3;
    private static final int COLOR_SHIFT = 5;
    private static final int COORDINATE_SHIFT = 3;
    private static final int PIECE_SHIFT = 5;

    public static int defaultMove(int color, int sourceFile, int sourceRank, int destinationFile,
            int destinationRank) {
        return createMove(DEFAULT, color, sourceFile, sourceRank, destinationFile, destinationRank, 0);
    }

    public static int doublePawnPush(int color, int sourceFile, int sourceRank, int destinationFile,
            int destinationRank) {
        return createMove(DOUBLE_PAWN_PUSH, color, sourceFile, sourceRank, destinationFile, destinationRank, 0);
    }

    public static int promotion(int color, int sourceFile, int sourceRank, int destinationFile, int destinationRank,
            int promotedPiece) {
        return createMove(PROMOTION, color, sourceFile, sourceRank, destinationFile, destinationRank, promotedPiece);
    }

    public static int castling(int color, int sourceFile, int sourceRank, int destinationFile, int destinationRank,
            int rookSourceFile, int rookSourceRank, int rookDestinationFile, int rookDestinationRank) {
        int meta = rookDestinationRank;
        meta = meta << COORDINATE_SHIFT | rookDestinationFile;
        meta = meta << COORDINATE_SHIFT | rookSourceRank;
        meta = meta << COORDINATE_SHIFT | rookSourceFile;
        return createMove(CASTLING, color, sourceFile, sourceRank, destinationFile, destinationRank, meta);
    }

    public static int capture(int color, int sourceFile, int sourceRank, int destinationFile, int destinationRank,
            int capturedPiece) {
        return createMove(CAPTURE, color, sourceFile, sourceRank, destinationFile, destinationRank,
                capturedPiece << 7);
    }

    public static int enPassant(int color, int sourceFile, int sourceRank, int destinationFile, int destinationRank,
            int capturedPieceFile, int capturedPieceRank, int capturedPiece) {
        int meta = capturedPieceRank;
        meta = meta << COORDINATE_SHIFT | capturedPieceFile;
        meta |= capturedPiece << 7;
        return createMove(EN_PASSANT, color, sourceFile, sourceRank, destinationFile, destinationRank, meta);
    }

    public static int promotionCapture(int color, int sourceFile, int sourceRank, int destinationFile,
            int destinationRank, int promotedPiece, int capturedPiece) {
        int meta = promotedPiece;
        meta |= capturedPiece << 7;
        return createMove(PROMOTION_CAPTURE, color, sourceFile, sourceRank, destinationFile, destinationRank, meta);
    }

    private static int createMove(MoveType type, int color, int sourceFile, int sourceRank, int destinationFile,
            int destinationRank, int meta) {
        int move = meta;
        move = move << COORDINATE_SHIFT | destinationRank;
        move = move << COORDINATE_SHIFT | destinationFile;
        move = move << COORDINATE_SHIFT | sourceRank;
        move = move << COORDINATE_SHIFT | sourceFile;
        move = move << COLOR_SHIFT | color;
        move = move << TYPE_SHIFT | type.ordinal();
        return move;
    }

    public static MoveType getType(int move) {
        return MoveType.values()[move & TYPE_MASK];
    }

    public static int getColor(int move) {
        return move >> 3 & COLOR_MASK;
    }

    public static int getSourceFile(int move) {
        return move >> 8 & COORDINATE_MASK;
    }

    public static int getSourceRank(int move) {
        return move >> 11 & COORDINATE_MASK;
    }

    public static int getDestinationFile(int move) {
        return move >> 14 & COORDINATE_MASK;
    }

    public static int getDestinationRank(int move) {
        return move >> 17 & COORDINATE_MASK;
    }

    public static int getPromotedPiece(int move) {
        return move >> 20 & PIECE_MASK;
    }

    public static int getRookSourceFile(int move) {
        return move >> 20 & COORDINATE_MASK;
    }

    public static int getCapturedPieceFile(int move) {
        return move >> 20 & COORDINATE_MASK;
    }

    public static int getRookSourceRank(int move) {
        return move >> 20 & COORDINATE_MASK;
    }

    public static int getCapturedPieceRank(int move) {
        return move >> 23 & COORDINATE_MASK;
    }

    public static int getRookDestinationFile(int move) {
        return move >> 26 & COORDINATE_MASK;
    }

    public static int getRookDestinationRank(int move) {
        return move >> 29 & COORDINATE_MASK;
    }

    public static int getCapturedPiece(int move) {
        return move >> 27 & PIECE_MASK;
    }

}
