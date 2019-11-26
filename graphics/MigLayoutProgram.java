package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;


public class MigLayoutProgram extends JFrame {

    Renderer renderer;
    String baseURL = "https://www.labour.gov.on.ca/scripts/mpdf/certificatev16.php?id=";
    String exampleCode = "eyJmaXJzdE5hbWUiOiJpbnNlcnRmaXJzdCIsImxhc3ROYW1lIjoiaW5zZXJ0bGFzdCIsImNvdXJzZSI6Imluc2VydGNvdXJzZSIsImZvcmNlZG93bmxvYWQiOjEsImxhbmd1YWdlIjoiZW4iLCJ1c2VybWFpbCI6Imluc2VydG1haWwifQ==";
    String exampleDecode = new String(Base64.getDecoder().decode(exampleCode));

    String finalURL;

    public String buildRaw(String first, String surname, String subject, String email){
        String code = exampleDecode;
        code = code.replace("insertfirst", first);
        code = code.replace("insertlast", surname);
        code = code.replace("insertcourse", subject);
        code = code.replace("insertmail", email);
        return code;
    }

    public String generateURL(String code){
        return baseURL + Base64.getEncoder().encodeToString(code.getBytes());
    }

    public static void main(String[] args) {
        new MigLayoutProgram();
    }

    MigLayoutProgram(){
        init();

        while(true){
            step();
        }
    }

    void init(){
        setTitle("Definitely Legitimate Certificate Issuing Program");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/icon.png")));
        setSize(500,500);
        renderer = new Renderer();
        renderer.setBackground(Color.WHITE);
        MigLayout layout = new MigLayout("wrap 6, align center");
        renderer.setLayout(layout);
        JLabel headerLabel = new JLabel("Definitely Legitimate Certificate Issuing Program");
        renderer.add(headerLabel, "span, gapbottom 10, align label");
        JLabel firstLabel = new JLabel("First Name: ");
        JTextField firstField = new JTextField(10);
        renderer.add(firstLabel, "span 1, gapy 20, align right");
        renderer.add(firstField, "span 2, grow, gapy 20");
        JLabel surnameLabel = new JLabel("Surname: ");
        JTextField surnameField = new JTextField(10);
        renderer.add(surnameLabel, "span 1, gap unrelated, gapy 20, align right");
        renderer.add(surnameField, "span 2, grow, gapy 20");
        JLabel courseLabel = new JLabel("Course: ");
        JTextField courseField = new JTextField(30);
        renderer.add(courseLabel, "span 1, gapy 20, align right");
        renderer.add(courseField, "span 5, grow, gapy 20");
        JLabel emailLabel = new JLabel("Email (Optional): ");
        JTextField emailField = new JTextField(30);
        renderer.add(emailLabel, "span 1, gap unrelated, gapy 20, align right");
        renderer.add(emailField, "span 5, grow, gapy 20");
        JButton generateButton = new JButton("Generate!");
        generateButton.setBackground(Color.WHITE.darker());
        generateButton.setForeground(Color.RED);
        renderer.add(generateButton, "span 3, gapy 20");
        JButton openButton = new JButton("Open in Browser");
        openButton.setBackground(Color.WHITE);
        openButton.setVisible(false);
        renderer.add(openButton, "span 3, gapy 20, wrap");
        JEditorPane certPane = new JEditorPane(){
            @Override
            public void paintComponent(Graphics g){
                Graphics2D g2 = (Graphics2D) g;
                g2.scale(0.25, 0.25);
                super.paintComponent(g2);
            }
        };
        certPane.setContentType( "text/html" );
        certPane.setEditable( false );
        certPane.setSize(360,240);
        certPane.setMinimumSize(certPane.getSize());
        certPane.setMaximumSize(certPane.getSize());
        certPane.setHighlighter(null);
        certPane.setVisible(true);
        renderer.add(certPane, "span 4, align center");
        add(renderer);
        generateButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                finalURL = generateURL(buildRaw(firstField.getText(), surnameField.getText(), courseField.getText(), emailField.getText()));
                try {
                    certPane.setPage(finalURL);
                    openButton.setVisible(true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        openButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    try {
                        Desktop.getDesktop().browse(new URI(finalURL));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (URISyntaxException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        setLocationRelativeTo(null);
        setVisible(true);
    }

    void step(){
        renderer.repaint();
    }

    private class Renderer extends JPanel {

        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
        }
    }
}
