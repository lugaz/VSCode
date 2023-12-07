package CS180HW12;

import javax.swing.*;

import java.awt.*;

import java.awt.event.*;

import java.util.Random;

public class PaintTool extends JComponent implements Runnable {
    Random random = new Random();
    int upperbound = 255;
    Color color;
        //generate random values from 0-24
     
    Image image; // the canvas
    Graphics2D graphics2D;  // this will enable drawing
    int curX; // current mouse x coordinate
    int curY; // current mouse y coordinate
    int oldX; // previous mouse x coordinate
    int oldY; // previous mouse y coordinate

    JButton clearButton; // button to enter information
    //JTextField strTextField; // text field for input
    JButton eraseButton;

    JButton fillButton;

    JButton randomButton;

    JButton redButton;
    JTextField redTextField;
    JButton blueButton;
    JTextField blueTextField;
    JButton greenButton;
    JTextField greenTextField;
    JButton hexButton;
    JTextField hexTextField;
    


    PaintTool paint;// variable of the type SimplePaint
    int red;
    int green;
    int blue;
    String hex;
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new PaintTool());

    }

    public void run(){

        JFrame frame = new JFrame("Simple Paint Walkthrough");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        paint = new PaintTool();
        content.add(paint, BorderLayout.CENTER);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        //strTextField = new JTextField(10); how to do it
        clearButton = new JButton("Clear");
        clearButton.addActionListener(actionListener);
        fillButton = new JButton("Fill");
        fillButton.addActionListener(actionListener);
        eraseButton = new JButton("Erase");
        eraseButton.addActionListener(actionListener);
        randomButton = new JButton("Random");
        randomButton.addActionListener(actionListener);
        //South
        redTextField = new JTextField(5);
        redButton = new JButton("Red");
        blueTextField = new JTextField(5);
        blueButton = new JButton("Blue");
        greenTextField = new JTextField(5);
        greenButton = new JButton("Green");
        hexTextField = new JTextField("#",10);
        hexButton = new JButton("Hex");
        
        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();
        //panel.add(strTextField); how to do it 
        northPanel.add(clearButton);
        northPanel.add(fillButton);
        northPanel.add(eraseButton);
        northPanel.add(randomButton);
        
        
        southPanel.add(redTextField);
        southPanel.add(redButton);
        southPanel.add(blueTextField);
        southPanel.add(blueButton);
        southPanel.add(greenTextField);
        southPanel.add(greenButton);
        southPanel.add(hexTextField);
        southPanel.add(hexButton);
        
        
        content.add(southPanel, BorderLayout.SOUTH);
        content.add(northPanel, BorderLayout.NORTH);

         

       

    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearButton) {
                paint.clearScreen();
                redTextField.setText("");
                blueTextField.setText("");
                greenTextField.setText("");
                hexTextField.setText("#");
            }
            if (e.getSource() == eraseButton){
                paint.eraseScreen();
            }
            if (e.getSource() == fillButton){
                paint.fillScreen();
                redTextField.setText("");
                blueTextField.setText("");
                greenTextField.setText("");
                hexTextField.setText("#");
            }
            if (e.getSource() == randomButton){
                paint.randomScreen();
            }
            if (e.getSource() == redButton){
                paint.redHex();
                if (redTextField.getText().equals("")){
                    redTextField.setText("0");
                }
            }
            if (e.getSource() == blueButton){
                blueHex();
                if (blueTextField.getText().equals("")){
                    blueTextField.setText("0");
                }
            }
            if (e.getSource() == greenButton){
                greenHex();
                if (greenTextField.getText().equals("")){
                    greenTextField.setText("0");
                }
            }

            
           

        }
    };



    public void redHex(){//FOR RED BUTTON 
        red = Integer.valueOf(redTextField.getText());
        hex = String.format("#%02x%02x%02x", red, green, blue);
        color = Color.decode(hex);
        graphics2D.setPaint(color);
        graphics2D.setStroke(new BasicStroke(5));
        repaint();
    }

    public void blueHex(){//FOR blue BUTTON 
        blue = Integer.valueOf(redTextField.getText());
        hex = String.format("#%02x%02x%02x", red, green, blue);
        color = Color.decode(hex);
        graphics2D.setPaint(color);
        graphics2D.setStroke(new BasicStroke(5));
        repaint();
    }
    public void greenHex(){//FOR green BUTTON 
        green = Integer.valueOf(redTextField.getText());
        hex = String.format("#%02x%02x%02x", red, green, blue);
        color = Color.decode(hex);
        graphics2D.setPaint(color);
        graphics2D.setStroke(new BasicStroke(5));
        repaint();
    }

    //TODO write hex text feild





    public void randomScreen(){//FOR RANDOM COLOR   
        red = random.nextInt(upperbound);
        green = random.nextInt(upperbound);
        blue = random.nextInt(upperbound);
        hex = String.format("#%02x%02x%02x", red, green, blue);
        color = Color.decode(hex);
        graphics2D.setPaint(color);
        graphics2D.setStroke(new BasicStroke(5));
        repaint();

    }
    public void fillScreen(){//TODO set to current pen color 
        graphics2D.setPaint(color);
        graphics2D.fillRect(0, 0, getSize().width, getSize().height);
        graphics2D.setPaint(Color.black);
        graphics2D.setStroke(new BasicStroke(5));
        repaint(); 
    }
    public void eraseScreen(){//TODO set to current backgroud color
        graphics2D.setPaint(Color.white);
        graphics2D.setStroke(new BasicStroke(5));
        repaint(); 
    }
    public void clearScreen() {//TODO Add erase text bod 
        graphics2D.setPaint(Color.white);
        graphics2D.fillRect(0, 0, getSize().width, getSize().height);
        graphics2D.setPaint(Color.black);
        graphics2D.setStroke(new BasicStroke(5));
        repaint();
  
    }



    public void draw(int size) {

        graphics2D.setStroke(new BasicStroke(size));

    }



    protected void paintComponent(Graphics g) {

        if (image == null) { //first time, image will be null.  This is to set create and set defautls

            image = createImage(getSize().width, getSize().height);
            // this lets us draw on the image (ie. the canvas)

            graphics2D = (Graphics2D) image.getGraphics();
            // gives us better rendering quality for the drawing lines
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
             RenderingHints.VALUE_ANTIALIAS_ON);

            // set canvas to white with default paint color
            graphics2D.setPaint(Color.white);
            graphics2D.fillRect(0, 0, getSize().width, getSize().height);
            graphics2D.setPaint(Color.black);
            graphics2D.setStroke(new BasicStroke(5));  //Here we set the default for the line

            repaint();

        }

        g.drawImage(image, 0, 0, null);

}

 

public PaintTool() {

    addMouseListener(new MouseAdapter() {

        @Override

        public void mousePressed(MouseEvent e) {

            // set oldX and oldY coordinates to beginning mouse press

            oldX = e.getX();

            oldY = e.getY();

        }

    });

 

    addMouseMotionListener(new MouseMotionAdapter() {

        @Override

        public void mouseDragged(MouseEvent e) {

            // set current coordinates to where mouse is being dragged
            curX = e.getX();
            curY = e.getY();

            // draw the line between old coordinates and new ones
            //graphics2D.setStroke(new BasicStroke(30)); //Set line width defautl when we start
            graphics2D.drawLine(oldX, oldY, curX, curY);

            // refresh frame and reset old coordinates

            repaint();
            oldX = curX;
            oldY = curY;

        }

    });

}  

}
