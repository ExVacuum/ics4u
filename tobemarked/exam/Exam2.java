import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//Silas Bartha, Exam Program 2, Jan 27, 2020

public class Exam2 extends JFrame{

    public static void main(String[] args) {
        new Exam2();
    }

    Rectangle ball = new Rectangle(0,200,50,50);
    Timer timer;
    JPanel mainPanel;
    JPanel buttonPanel;

    public Exam2(){
        init();
        while (true){
            mainPanel.repaint();
            buttonPanel.repaint();
        }
    }

    void init(){

        //Initialize window
        setVisible(false);
        setSize(600,600);
        setLocationRelativeTo(null);
        setTitle("Click on the ball");

        //Button Panel
        buttonPanel = new JPanel();
        JButton startBtn = new JButton("Start");
        startBtn.addActionListener(btnActionListener -> {
            timer = new Timer(10, tmrActionListener ->{
                moveBall();
                System.out.println("success");});
            timer.start();
        });
        buttonPanel.add(startBtn);
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(actionListener -> System.exit(0));
        buttonPanel.add(exitButton);
        buttonPanel.setBackground(Color.DARK_GRAY);
        add(buttonPanel, BorderLayout.NORTH);

        //Drawing Panel
        mainPanel = new MPanel();
        mainPanel.setBackground(Color.BLACK);
        add(mainPanel, BorderLayout.CENTER);

        validate();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //Move ball to right, looping around edge when passed
    void moveBall(){
        ball.x++;
        if(ball.x>getWidth()) ball.x= -ball.width;
    }


    class MPanel extends JPanel  implements MouseListener{

        {
            addMouseListener(this);
        }

        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            //Draw Ball
            g2.setColor(Color.GREEN);
            g2.fillOval(ball.x,ball.y,ball.width,ball.height);
        }


        @Override
        public void mouseClicked(MouseEvent e) {

            //If ball clicked, stop timer (if started in the first place) and set background color to white
            if(ball.contains(e.getPoint())){
                if(timer!=null)timer.stop();
                mainPanel.setBackground(Color.WHITE);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
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
    }
}
