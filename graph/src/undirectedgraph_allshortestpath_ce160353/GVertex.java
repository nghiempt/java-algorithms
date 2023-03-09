package undirectedgraph_allshortestpath_ce160353;

import java.awt.*;

/**
 *
 * @author CE160353 PhamThanhNghiem
 */
public class GVertex {

    private int x, y;
    private int value;
    private boolean selected = false;
    private static final int RADIUS = 12;
    private static final int DIAMETER = RADIUS * 2;
    private static final Font FONT = new Font("Arial", Font.PLAIN, 12);

    public GVertex(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getLabel() {
        return value + "";
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public static double distance(int x1, int y1, int x2, int y2) {
        int x = x1 - x2;
        int y = y1 - y2;
        return Math.sqrt(x * x + y * y);
    }

    public boolean isInside(int mouseX, int mouseY) {
        return distance(x, y, mouseX, mouseY) <= RADIUS;
    }

    public static void drawCtString(Graphics g, String txt, Rectangle rect, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);

        int x = rect.x + (rect.width - metrics.stringWidth(txt)) / 2;

        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();

        g.setFont(font);

        g.drawString(txt, x, y);
    }

    public void draw(Graphics2D g) {
        g.setColor(selected ? Color.red : Color.WHITE);
        g.fillOval(x - RADIUS, y - RADIUS, DIAMETER, DIAMETER);

        g.setColor(selected ? Color.yellow : Color.black);
        g.drawOval(x - RADIUS, y - RADIUS, DIAMETER, DIAMETER);

        g.setColor(selected ? Color.yellow : Color.black);
        drawCtString(g, getLabel(),
                new Rectangle(x - RADIUS, this.y - RADIUS, DIAMETER, DIAMETER), FONT);
    }

}
