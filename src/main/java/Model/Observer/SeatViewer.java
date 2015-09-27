package Model.Observer;

/**
 * Created by Коля on 14.09.2015.
 */
public class SeatViewer {
    private long seatId;
    private int seatNum;

    public SeatViewer(long seatId, int seatNum) {
        this.seatId = seatId;
        this.seatNum = seatNum;
    }

    public long getSeatId() {
        return seatId;
    }

    public int getSeatNum() {
        return seatNum;
    }
}
