import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyPanel extends JPanel implements ActionListener, MouseMotionListener, MouseListener {

    final int PANEL_WIDTH = 800;
    final int PANEL_HEIGHT = 600;
    final int size = 30;
    private int x, y;
    private int dx;
    Timer timer;
    Flame flame;

    MyPanel(){
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.darkGray);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        timer = new Timer(1, this);
        timer.start();
        flame = new Flame(PANEL_WIDTH/2, PANEL_HEIGHT/2, size);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.setStroke(new BasicStroke(5));
        g2d.fillRect(PANEL_WIDTH/2-size*2, PANEL_HEIGHT/2+size, 4*size, PANEL_HEIGHT/2-size);
        g2d.setColor(Color.WHITE);
        g2d.drawPolygon(new int[]{PANEL_WIDTH/2-size*2, PANEL_WIDTH/2-size*2, PANEL_WIDTH/2+size*2, PANEL_WIDTH/2+size*2},
                new int[]{PANEL_HEIGHT/2+size, PANEL_HEIGHT, PANEL_HEIGHT, PANEL_HEIGHT/2+size}, 4);
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillOval(PANEL_WIDTH/2-size*2, PANEL_HEIGHT/2+size/2, 4*size, size);
        g2d.setColor(Color.WHITE);
        g2d.drawOval(PANEL_WIDTH/2-size*2, PANEL_HEIGHT/2+size/2, 4*size, size);
        g2d.setColor(new Color(170, 80, 0));
        g2d.fillRect(PANEL_WIDTH/2-1, PANEL_HEIGHT/2-1, 2, size);
        flame.paint(g2d);
        g2d.drawString("ЛКМ и ПКМ имитирует дуновение ветра. Колёсико заново поджигает свечу", PANEL_WIDTH/4, PANEL_HEIGHT/8);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        flame.animation(dx/3);
        repaint();
        dx *= 0.99;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == 2){
            flame.reset();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        dx = e.getX() - x;
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
