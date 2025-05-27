package test;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;

public class StartScreen extends JPanel {
    private Image backgroundImage;
    private GameGUI mainFrame;

    public StartScreen(GameGUI mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(null);
        setPreferredSize(new Dimension(800, 600));

        try {
            backgroundImage = ImageIO.read(getClass().getResource("universe.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JButton startButton = new JButton("START");
        startButton.setBounds(325, 300, 150, 50);
        startButton.setFont(new Font("Verdana", Font.BOLD, 24));
        add(startButton);

        // START 버튼 클릭 시 유닛 선택 화면으로 이동
        startButton.addActionListener(e -> mainFrame.showUnitSelectionScreen());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
