package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.imageio.ImageIO;

// 시작 화면: 배경 이미지 + START 버튼
class StartScreen extends JPanel {
    private Image backgroundImage;
    private GUI mainFrame;

    public StartScreen(GUI mainFrame) {
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

        startButton.addActionListener(e -> {
            mainFrame.showBattleScreen();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

// 전투 화면(임시)
class BattleScreen extends JPanel {
    public BattleScreen() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.DARK_GRAY);

        JLabel label = new JLabel("전투 화면입니다!");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 36));
        add(label);
    }
}

// 메인 프레임: 화면 전환 관리
public class GUI extends JFrame {
    private StartScreen startScreen;
    private BattleScreen battleScreen;

    public GUI() {
        setTitle("게임 실행 화면");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startScreen = new StartScreen(this);
        battleScreen = new BattleScreen();

        setContentPane(startScreen);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // 전투 화면으로 전환
    public void showBattleScreen() {
        setContentPane(battleScreen);
        revalidate();  // 레이아웃 다시 적용
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI());
    }
}

