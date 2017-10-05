package modal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HighScorePost extends JFrame {

	private Player pl;
	private HighScore hs;
	JPanel panel = new JPanel();
	JLabel label1 = new JLabel();
	JTextField textfield = new JTextField(30);
	JButton button = new JButton("Save");

	public HighScorePost(Player pl, HighScore hs) {
		this.pl = pl;
		this.hs = hs;
	}

	public void window() {

		setTitle("Game Over");
		setSize(400, 175);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel.add(label1);
		label1.setText(
				"<html><div style='text-align: center;'>GAME OVER!<br/>Enter your name to save your highscore!</div></html>");
		panel.add(textfield);

		// toiminnallisuus enterille
		textfield.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = textfield.getText();

				if (input.length() > 0) {
					System.out.println("postattiin nimi: " + input + ", paivamaara: " + currentDate() + " ja pisteet: "
							+ pl.getScore());
					hs.post(pl.getScore(), input, currentDate(), "pacmanHighscore");
				}
			}
		});

		panel.add(button);
		// toiminnallisuus napille
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = textfield.getText();

				if (input.length() > 0) {
					System.out.println("postattiin nimi: " + input + ", paivamaara: " + currentDate() + " ja pisteet: "
							+ pl.getScore());
					hs.post(pl.getScore(), input, currentDate(), "pacmanHighscore");
				}
			}
		});

		add(panel);
		setVisible(true);
	}

	public String currentDate() {
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat time = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String currentDate = time.format(date);
		return currentDate;
	}

}