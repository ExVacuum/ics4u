/**
 * Silas Bartha, Nov. 25, 2019
 * MigLayout Practice
 * Also OMOL Dummy Certificate Generation Tool (Do not abuse pls)
 * Based on similar Python program, created by Evan Pratten
 */

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

    //Renderer
    JPanel renderer;

    //URL Components
    String baseURL = "https://www.labour.gov.on.ca/scripts/mpdf/certificatev16.php?id=";
    String exampleCode = "eyJmaXJzdE5hbWUiOiJpbnNlcnRmaXJzdCIsImxhc3ROYW1lIjoiaW5zZXJ0bGFzdCIsImNvdXJzZSI6Imluc2VydGNvdXJzZSIsImZvcmNlZG93bmxvYWQiOjEsImxhbmd1YWdlIjoiZW4iLCJ1c2VybWFpbCI6Imluc2VydG1haWwifQ==";
    String exampleDecode = new String(Base64.getDecoder().decode(exampleCode));

    String finalURL;

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

        //Layout
        MigLayout layout = new MigLayout("wrap 6, align center");

        //Header
        JLabel headerLabel = new JLabel("Definitely Legitimate Certificate Issuing Program");

        //First Name
        JLabel firstLabel = new JLabel("First Name: ");
        JTextField firstField = new JTextField(10);

        //Last Name
        JLabel surnameLabel = new JLabel("Surname: ");
        JTextField surnameField = new JTextField(10);

        //Course Name
        JLabel courseLabel = new JLabel("Course: ");
        JTextField courseField = new JTextField(30);

        //Email
        JLabel emailLabel = new JLabel("Email (Optional): ");
        JTextField emailField = new JTextField(30);

        //Generation Button
        JButton generateButton = new JButton("Generate!");
        generateButton.setBackground(Color.WHITE.darker());
        generateButton.setForeground(Color.RED);

        //Open in Browser Button
        JButton openButton = new JButton("Open in Browser");
        openButton.setBackground(Color.WHITE);
        openButton.setVisible(false);

        //Certificate Pane
        JEditorPane certPane = new JEditorPane(){
            @Override
            public void paintComponent(Graphics g){
                Graphics2D g2 = (Graphics2D) g;

                //Make Certificate Small
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

        //Renderer Panel
        renderer = new JPanel();
        renderer.setBackground(Color.WHITE);
        renderer.setLayout(layout);
        renderer.add(headerLabel, "span, gapbottom 10, align label");
        renderer.add(firstLabel, "span 1, gapy 20, align right");
        renderer.add(firstField, "span 2, grow, gapy 20");
        renderer.add(surnameLabel, "span 1, gap unrelated, gapy 20, align right");
        renderer.add(surnameField, "span 2, grow, gapy 20");
        renderer.add(courseLabel, "span 1, gapy 20, align right");
        renderer.add(courseField, "span 5, grow, gapy 20");
        renderer.add(emailLabel, "span 1, gap unrelated, gapy 20, align right");
        renderer.add(emailField, "span 5, grow, gapy 20");
        renderer.add(generateButton, "span 3, gapy 20");
        renderer.add(openButton, "span 3, gapy 20, wrap");
        renderer.add(certPane, "span 4, align center");

        //Generation Button Listener
        generateButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

                //Generate URL Using Specified Data
                finalURL = generateURL(buildRaw(firstField.getText(), surnameField.getText(), courseField.getText(), emailField.getText()));
                try {

                    //Add Preview in Certificate Pane
                    certPane.setPage(finalURL);

                    //Show "Open in Browser" button
                    openButton.setVisible(true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        //"Open in Browser" Button Listener
        openButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

                //Open Previously Generated URL in Browser
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

        //Setup Frame
        setTitle("Definitely Legitimate Certificate Issuing Program");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/icon.png")));
        setSize(500,500);
        add(renderer);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    void step(){

        //Refresh Screen, Used Mainly for Certificate Pane
        renderer.repaint();
    }

    //Create Plaintext URL Tail, Based on Input Data
    public String buildRaw(String first, String surname, String subject, String email){

        //Get decoded template
        String code = exampleDecode;

        //First Name
        code = code.replace("insertfirst", first);

        //Last Name
        code = code.replace("insertlast", surname);

        //Course Name
        code = code.replace("insertcourse", subject);

        //Email Address
        code = code.replace("insertmail", email);

        //Return Plaintext Tail
        return code;
    }

    //Generate Base64 URL Tail, and Append to Base URL
    public String generateURL(String code){
        return baseURL + Base64.getEncoder().encodeToString(code.getBytes());
    }
}
