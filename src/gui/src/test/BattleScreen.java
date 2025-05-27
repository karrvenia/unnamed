package test;

import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.List;

public class BattleScreen extends JPanel {
    private Image backgroundImage;
    private JLabel turnLabel;
    private JLabel hpLabel;
    private int turn = 1;
    private int hp = 100;

    // 기본 생성자
    public BattleScreen() {
        initBattleScreen();
    }

    // 선택된 유닛 받는 생성자
    public BattleScreen(List<String> selectedUnits) {
        initBattleScreen();

        JLabel unitsLabel = new JLabel("Selected units: " + selectedUnits.toString());
        unitsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        unitsLabel.setForeground(Color.WHITE);
        unitsLabel.setBounds(200, 10, 400, 30);
        add(unitsLabel);
    }

    private void initBattleScreen() {
        setPreferredSize(new Dimension(800, 600));
        try {
            backgroundImage = ImageIO.read(getClass().getResource("battlescreen.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("배경 이미지 로딩 실패");
        }

        setLayout(null);

        turnLabel = new JLabel("턴: " + turn);
        turnLabel.setFont(new Font("Arial", Font.BOLD, 20));
        turnLabel.setForeground(Color.RED);
        turnLabel.setBounds(50, 50, 300, 30);
        add(turnLabel);

        hpLabel = new JLabel("HP: " + hp);
        hpLabel.setFont(new Font("Arial", Font.BOLD, 20));
        hpLabel.setForeground(Color.WHITE);
        hpLabel.setBounds(50, 90, 300, 30);
        add(hpLabel);
    }

    // 배경 이미지
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

