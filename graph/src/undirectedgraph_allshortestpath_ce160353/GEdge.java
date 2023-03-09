package undirectedgraph_allshortestpath_ce160353;

import java.awt.*;

/**
 *
 * @author CE160353 PhamThanhNghiem
 */
public class GEdge {

    public static final int PADDING_X = 12;

    public static final int PADDING_Y = 8;

    public static final int LABEL_W = PADDING_X * 2;

    public static final int LABEL_H = PADDING_Y * 2;

    public static final int LABEL_R = 10;

    public static final int FONT_SIZE = 10;

    public static final Font FONT = new Font("Arial", Font.PLAIN, FONT_SIZE);
    private int value;
    private GVertex start;
    private GVertex end;
    private boolean isSelected = false;

    private int x, y;

    public GEdge(int value, GVertex start, GVertex end) {
        this.value = value;
        this.start = start;
        this.end = end;
        calculateCenterLocation();
    }

    private void calculateCenterLocation() {
        this.x = (start.getX() + end.getX()) / 2;
        this.y = (start.getY() + end.getY()) / 2;
    }

    public double getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public GVertex getStart() {
        return start;
    }

    public void setStart(GVertex start) {
        this.start = start;
    }

    public GVertex getEnd() {
        return end;
    }

    public void setEnd(GVertex end) {
        this.end = end;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isInside(int x, int y) {
        int x1 = this.x - PADDING_X;
        int y1 = this.y - PADDING_Y;
        int x2 = this.x + PADDING_X;
        int y2 = this.y + PADDING_Y;
        return x1 <= x && x <= x2 && y1 <= y && y <= y2;
    }

    public void draw(Graphics2D g) {
        g.setColor(isSelected ? Color.red : Color.BLACK);
        g.drawLine(start.getX(), start.getY(), end.getX(), end.getY());
        calculateCenterLocation();

        g.setColor(isSelected ? Color.yellow : Color.WHITE);
        g.fillRoundRect(this.x - PADDING_X, this.y - PADDING_Y, LABEL_W, LABEL_H, LABEL_R, LABEL_R);

        g.setColor(isSelected ? Color.red : Color.black);
        GVertex.drawCtString(g, value + "",
                new Rectangle(this.x - PADDING_X, this.y - PADDING_Y, LABEL_W, LABEL_H), FONT);
    }
}
