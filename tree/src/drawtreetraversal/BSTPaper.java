package drawtreetraversal;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author CE160353 PhamThanhNghiem
 */
public class BSTPaper extends JPanel {
    private BSTree tree;
    private Graphics g;

    public BSTPaper(BSTree tree) {
        this.tree = tree;
    }

//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
//        this.g = g;
//    }
    
    public void addNode(int newNodeData) {
        this.tree.addNode(newNodeData);
        repaint();
    }   
    
    public void drawNode(BSTNode node) {
       if (node != null) {
           int x = node.getX();
           int y = node.getY();
           int r = 10;
           
           g.setColor(Color.black);
           if (node.hasLeftChild()) {
               g.drawLine(x, y, node.getLeftChild().getX(), node.getLeftChild().getY());
           }
           
           if (node.hasRightChild()) {
               g.drawLine(x, y, node.getRightChild().getX(), node.getRightChild().getY());
           }
           
           g.setColor(Color.white);
           g.fillOval(x - r, y - r, r*2, r*2);
           
           
           g.setColor(Color.black);
           g.drawOval(x - r, y - r, r*2, r*2);
          
           drawCenteredString(g, node.getData() + "", new Rectangle(x - r, y - r, r * 2, r * 2), new Font("Arial", Font.PLAIN, 12));
           drawCenteredString(g, "c=" + node.getCount(), new Rectangle(x - r, y + r, r * 2, r * 2), new Font("Arial", Font.PLAIN, 12));
           drawNode(node.getLeftChild());
           drawNode(node.getRightChild());
       }
    }
    
    /**
    * Draw a String centered in the middle of a Rectangle.
    *
    * @param g The Graphics instance.
    * @param text The String to draw.
    * @param rect The Rectangle to center the text in.
    */
    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
       // Get the FontMetrics
       FontMetrics metrics = g.getFontMetrics(font);
       // Determine the X coordinate for the text
       int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
       // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
       int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
       // Set the font
       g.setFont(font);
       // Draw the String
       g.drawString(text, x, y);
    }

    public void drawPath() {
        ArrayList<BSTNode> path = this.tree.getPath();
        BSTNode node, node1, node2;
        int x, y, r;
        if (path.size() > 0) {
            g.setColor(Color.red);
            for (int i = 0; i < path.size() - 1; i++ ) {
                node1 = path.get(i);
                node2 = path.get(i + 1);
                g.drawLine(node1.getX(), node1.getY(), node2.getX(), node2.getY());
            } 
            for (int i = 0; i < path.size(); i++) {
                node = path.get(i);
                x = node.getX();
                y = node.getY();
                r = 10;
                
                g.setColor(Color.YELLOW);
                g.fillOval(x - r, y - r, r*2, r*2);


                g.setColor(Color.RED);
                g.drawOval(x - r, y - r, r*2, r*2);

                drawCenteredString(g, node.getData() + "", new Rectangle(x - r, y - r, r * 2, r * 2), new Font("Arial", Font.PLAIN, 12));
                drawCenteredString(g, "c=" + node.getCount(), new Rectangle(x - r, y + r, r * 2, r * 2), new Font("Arial", Font.PLAIN, 12));
            } 
        }
    }
   
    public void drawPathOfNode() {
        repaint();
    }
    
    public void drawDeleteNode(int data) {
        this.tree.deleteNode(data);
        this.tree.clearPath();
        repaint();
    }
   
    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        this.g = g;
        drawNode(this.tree.getRoot());
        drawPath();
    }
   
    
    public void refresh() {
        repaint();
    }

    public void setTree(BSTree tree) {
        this.tree = tree;
    }
    
    
}
