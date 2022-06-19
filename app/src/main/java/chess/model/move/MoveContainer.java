package chess.model.move;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import chess.model.Board;
import chess.model.Square;
import chess.model.piece.Bishop;
import chess.model.piece.IPiece;
import chess.model.piece.King;
import chess.model.piece.Knight;
import chess.model.piece.Pawn;
import chess.model.piece.Queen;
import chess.model.piece.Rook;
import chess.model.piece.properties.Color;
import chess.model.piece.properties.Type;

public class MoveContainer {

    private Map<Square, Map<Square, Move>> moves;
    private Map<Square, Map<Square, Map<IPiece, Capture>>> captures;
    private Map<Square, Map<Square, EnPassant>> enpassant;
    private Map<Square, Map<Square, Map<IPiece, Promotion>>> promotions;
    private Map<Square, Map<Square, Castle>> castles;
    private Map<Square, Map<Square, Map<IPiece, Map<IPiece, PromotionCapture>>>> promotionCaptures;

    private IPiece[] pieces = {
            new Pawn(Color.WHITE), new Pawn(Color.BLACK),
            new Queen(Color.WHITE), new Queen(Color.BLACK),
            new Rook(Color.WHITE), new Rook(Color.BLACK),
            new Bishop(Color.WHITE), new Bishop(Color.BLACK),
            new Knight(Color.WHITE), new Knight(Color.BLACK),
            new King(Color.WHITE), new King(Color.BLACK) };

    private Board board;

    public MoveContainer(Board board) {

        this.board = board;
        this.moves = new HashMap<>();
        this.captures = new HashMap<>();
        this.enpassant = new HashMap<>();
        this.promotions = new HashMap<>();
        this.castles = new HashMap<>();
        this.promotionCaptures = new HashMap<>();

        precalculateAllMoves();
    }

    private void precalculateAllMoves() {
        precalculateMoves();
        precalculateCaptures();
        precalculateEnpassant();
        precalculatePromotions();
        precalculateCastles();
        precalculatePromotionCaptures();
    }

    private void precalculateMoves() {
        for (int rankSrc = 0; rankSrc < Board.NUMBER_OF_RANKS; rankSrc++) {
            for (int fileSrc = 0; fileSrc < Board.NUMBER_OF_FILES; fileSrc++) {

                Square source = board.getSquare(fileSrc, rankSrc);
                moves.put(source, new HashMap<>());

                for (int rankDes = 0; rankDes < Board.NUMBER_OF_RANKS; rankDes++) {
                    for (int fileDes = 0; fileDes < Board.NUMBER_OF_FILES; fileDes++) {

                        Square destination = board.getSquare(fileSrc, rankSrc);
                        moves.get(source).put(destination, new Move(source, destination));
                    }
                }
            }
        }
    }

    private void precalculateCaptures() {
        for (int rankSrc = 0; rankSrc < Board.NUMBER_OF_RANKS; rankSrc++) {
            for (int fileSrc = 0; fileSrc < Board.NUMBER_OF_FILES; fileSrc++) {

                Square source = board.getSquare(fileSrc, rankSrc);
                captures.put(source, new HashMap<>());

                for (int rankDes = 0; rankDes < Board.NUMBER_OF_RANKS; rankDes++) {
                    for (int fileDes = 0; fileDes < Board.NUMBER_OF_FILES; fileDes++) {

                        Square destination = board.getSquare(fileSrc, rankSrc);
                        captures.get(source).put(destination, new HashMap<IPiece, Capture>());

                        for (IPiece piece : pieces) {
                            captures.get(source).get(destination).put(piece, new Capture(source, destination, piece));
                        }
                    }
                }
            }
        }
    }

    private void precalculateEnpassant() {
        for (int fileSrc = 0; fileSrc < Board.NUMBER_OF_FILES; fileSrc++) {
            for (Integer rankSrc : new int[] { 3, 4 }) {

                Square source = board.getSquare(fileSrc, rankSrc);
                enpassant.put(source, new HashMap<>());

                int rankDes = rankSrc == 3 ? rankSrc - 1 : rankSrc + 1;
                Color color = rankSrc == 3 ? Color.BLACK : Color.WHITE;

                for (Integer fileDes : new int[] { fileSrc - 1, fileSrc + 1 }) {
                    Square destination = board.getSquare(fileDes, rankDes);
                    enpassant.get(source).put(destination,
                            new EnPassant(source, destination, getPiece(Type.PAWN, color)));
                }
            }
        }
    }

    private void precalculatePromotions() {
        for (int fileSrc = 0; fileSrc < Board.NUMBER_OF_FILES; fileSrc++) {
            for (Integer rankSrc : new int[] { 1, 6 }) {

                Square source = board.getSquare(fileSrc, rankSrc);
                promotions.put(source, new HashMap<>());

                int rankDes = rankSrc == 1 ? rankSrc - 1 : rankSrc + 1;
                Color color = rankSrc == 1 ? Color.WHITE : Color.BLACK;

                for (Type type : new Type[] { Type.QUEEN, Type.ROOK, Type.BISHOP, Type.KNIGHT }) {
                    new Promotion(source, board.getSquare(fileSrc, rankDes), getPiece(type, color));
                }
            }
        }
    }

    private void precalculateCastles() {
        int kingFileSrc = 4; // file of the king
        for (Integer kingRankSrc : new int[] { 0, 7 }) {

            Square kingSource = board.getSquare(kingFileSrc, kingRankSrc);
            castles.put(kingSource, new HashMap<>());

            int kingRankDes = kingRankSrc;
            for (Integer kingFileDes : new int[] { kingFileSrc - 2, kingFileSrc + 2 }) {

                Square kingDestination = board.getSquare(kingFileDes, kingRankDes);

                int rookFileSrc = kingFileDes == 2 ? 0 : 7;
                int rookRankSrc = kingRankSrc;
                int rookFileDes = rookFileSrc == 0 ? 3 : 5;
                int rookRankDes = kingRankDes;

                Square rookSource = board.getSquare(rookFileSrc, rookRankSrc);
                Square rookDestination = board.getSquare(rookFileDes, rookRankDes);
                castles.get(kingSource).put(kingDestination,
                        new Castle(kingSource, kingDestination, rookSource, rookDestination));
            }

        }
    }

    private void precalculatePromotionCaptures() {
        for (int fileSrc = 0; fileSrc < Board.NUMBER_OF_FILES; fileSrc++) {
            for (Integer rankSrc : new int[] { 1, 6 }) {

                Square source = board.getSquare(fileSrc, rankSrc);
                enpassant.put(source, new HashMap<>());

                int rankDes = rankSrc == 1 ? rankSrc - 1 : rankSrc + 1;
                Color color = rankSrc == 1 ? Color.WHITE : Color.BLACK;

                for (Integer fileDes : new int[] { fileSrc - 1, fileSrc + 1 }) {
                    Square destination = board.getSquare(fileDes, rankDes);
                    promotionCaptures.get(source).put(destination, new HashMap<>());

                    for (Type type : new Type[] { Type.QUEEN, Type.ROOK, Type.BISHOP, Type.KNIGHT }) {
                        promotionCaptures.get(source).get(destination).put(getPiece(type, color), new HashMap<>());

                        for (Type capturedtype : new Type[] { Type.QUEEN, Type.ROOK, Type.BISHOP, Type.KNIGHT }) {

                            Color capturedColor = color == Color.WHITE ? Color.BLACK : Color.WHITE;

                            promotionCaptures.get(source).get(destination).get(getPiece(type, color)).put(
                                    getPiece(capturedtype, capturedColor),
                                    new PromotionCapture(source, destination, getPiece(type, color),
                                            getPiece(capturedtype, capturedColor)));
                        }
                    }
                }
            }
        }
    }

    private IPiece getPiece(Type type, Color color) {
        int index = switch (type) {
            case PAWN -> 0;
            case QUEEN -> 1;
            case ROOK -> 2;
            case BISHOP -> 3;
            case KNIGHT -> 4;
            case KING -> 5;
            default -> throw new IllegalArgumentException("Invalid piece type");
        };
        return pieces[index + (color == Color.WHITE ? 0 : 1)];
    }

    public Move getMove(Square source, Square destination) {
        return moves.get(source).get(destination);
    }

    public Capture getCapture(Square source, Square destination, IPiece piece) {
        return captures.get(source).get(destination).get(piece);
    }

    public EnPassant getEnPassant(Square source, Square destination, IPiece piece) {
        return enpassant.get(source).get(destination);
    }

    public Promotion getPromotion(Square source, Square destination, IPiece piece) {
        return promotions.get(source).get(destination).get(piece);
    }

    public Castle getCastle(Square source, Square destination, Square rookSource, Square rookDestination) {
        return castles.get(source).get(destination);
    }

    public PromotionCapture getPromotionCapture(Square source, Square destination, IPiece piece, IPiece capturedpiece) {
        return promotionCaptures.get(source).get(destination).get(piece).get(capturedpiece);
    }

}
