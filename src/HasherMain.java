import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;

/**
 * A very simple HMAC-SHA1 Hex Hasher GUI using the built in java Mac class
 * @author Jonathan Dilks
 */
public class HasherMain
{

    JFrame jFrame;
    Container container;
    JLabel messageLabel;
    JTextField messageTextBox;
    JLabel keyLabel;
    JTextField keyTextBox;
    JLabel outputLabel;
    JTextField outputTextBox;
    private static final String HMAC_SHA1_STRING = "HmacSHA1";

    public HasherMain()
    {
        //Set-up GUI
        jFrame = new JFrame("HMAC-SHA1 Hasher");
        container = new Container();

        messageLabel = new JLabel("Message:");
        messageTextBox = new JTextField();
        messageLabel.setLabelFor(messageTextBox);

        keyLabel = new JLabel("Key:");
        keyTextBox = new JTextField();
        keyLabel.setLabelFor(keyTextBox);

        outputLabel = new JLabel("Output Hash:");
        outputTextBox = new JTextField();
        outputTextBox.setEditable(false);
        outputLabel.setLabelFor(outputTextBox);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        setGUITheme();
        new HasherMain().showAndRun();
    }

    private void showAndRun()
    {
        messageTextBox.addKeyListener(new UpdateHashKeyListener());
        keyTextBox.addKeyListener(new UpdateHashKeyListener());

        jFrame.setContentPane(container);
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        container.add(messageLabel);
        container.add(messageTextBox);
        container.add(keyLabel);
        container.add(keyTextBox);
        container.add(outputLabel);
        container.add(outputTextBox);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setMinimumSize(new Dimension(700, 150));
        jFrame.setVisible(true);
    }

    private static void setGUITheme()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            /*try
            {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            {
            if ("Nimbus".equals(info.getName()))
            {
            UIManager.setLookAndFeel(info.getClassName());
            break;
            }
            }
            }
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e2)
            {
            }
            /**/
        }
    }

    private class UpdateHashKeyListener implements KeyListener
    {

        @Override
        public void keyTyped(KeyEvent e)
        {
            updateOnKeyPress();
        }

        @Override
        public void keyPressed(KeyEvent e)
        {
            updateOnKeyPress();
        }

        @Override
        public void keyReleased(KeyEvent e)
        {
            updateOnKeyPress();
        }

        private void updateOnKeyPress()
        {
            try
            {
                outputTextBox.setText(getHash(keyTextBox.getText(), messageTextBox.getText()));
            }
            catch (Exception ex)
            {
                System.err.println(ex);
            }
        }
    }

    private String getHash(String keyText, String messageText) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException
    {
        Mac mac = Mac.getInstance(HMAC_SHA1_STRING);
        SecretKeySpec signingKey;

        if (keyText.isEmpty())
        {
            byte[] emptyByte =
            {
                0x00
            };
            signingKey = new SecretKeySpec(emptyByte, HMAC_SHA1_STRING);
        }
        else
        {
            signingKey = new SecretKeySpec(keyText.getBytes(), HMAC_SHA1_STRING);
        }
        
        mac.init(signingKey);
        byte[] result = mac.doFinal(messageText.getBytes());
        return hashByteToHexString(result);
    }

    private String hashByteToHexString(byte hash[])
    {
        String string = "";

        for (int i = 0; i < hash.length; i++)
        {
            String temp = Integer.toHexString(hash[i] & 0xFF);
            while (temp.length() < 2)
            {
                temp = "0" + temp;
            }
            string += temp;
        }
        return string;
    }
}
