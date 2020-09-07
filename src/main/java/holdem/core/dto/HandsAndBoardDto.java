package holdem.core.dto;

import java.util.ArrayList;
import java.util.Objects;

public class HandsAndBoardDto {
    private ArrayList<Hand> hands = new ArrayList<>();
    private Board board;

    public HandsAndBoardDto() {
    }

    public void addHand(Hand newHand) {
        hands.add(newHand);
    }

    public ArrayList<Hand> getHands() {
        return hands;
    }

    public void setHands(ArrayList<Hand> hands) {
        this.hands = hands;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HandsAndBoardDto that = (HandsAndBoardDto) o;
        return Objects.equals(hands, that.hands) &&
                Objects.equals(board, that.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hands, board);
    }

    @Override
    public String toString() {
        return "HandsBoardDto{" +
                "hands=" + hands +
                ", board=" + board +
                '}';
    }
}
