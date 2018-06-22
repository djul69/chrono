package fr.wildcodeschool.quetes.chrono;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Chrono {
	public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("EEEE d MMMM uuuu",
			Locale.FRENCH);
	public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
	private JFrame frame;
	private final TimeProvider timeProvider;
	private boolean rolling;

	private JPanel mainPanel;
	private JButton startStopButton;
	private JButton resetButton;
	private JLabel chronoCount;
	private JLabel countedLabel;
	private JLabel hoursLabel;
	private JLabel minutesLabel;
	private JLabel secondsLabel;
	private JLabel hoursCount;
	private JLabel minutesCount;
	private JLabel secondsCount;
	private JLabel dateLabel;
	private JLabel dateText;
	private JLabel timeLabel;
	private JLabel timeText;
	private JPanel buttonsPanel;
	private JPanel centerPanel;
	private JPanel currentTimePanel;

	public Chrono(TimeProvider timeProvider) {
		this.timeProvider = timeProvider;

		startStopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startStop();
			}
		});

		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});

		mainPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1) { // Left click
					startStop();
				} else if (e.getButton() == 3) { // Right click
					reset();
				} else {
					super.mouseClicked(e);
				}
			}
		});
	}

	private void startStop() {
		timeProvider.startStop();
		frame.repaint();
	}

	private void reset() {
		timeProvider.reset();
		frame.repaint();
	}

	private void hookKeyboard() {
		// TODO
	}

	@SuppressWarnings("serial")
	private void display() throws InterruptedException {
		frame = new JFrame("Chrono") {
			@Override
			public void dispose() {
				rolling = false;
				super.dispose();
			}
		};

		frame.setContentPane(mainPanel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}


	public synchronized void roll() throws InterruptedException {
		if (!rolling) {
			rolling = true;

			hookKeyboard();
			display();

			refreshCounters(); // in case we're not initialized at 0 seconds

			while (rolling) {
				refreshCounters();
				refreshCurrentDateTime();

				frame.repaint();
				Thread.sleep(1000L);
			}
		}
	}

	private void refreshCurrentDateTime() {
		LocalDateTime now = LocalDateTime.now();
		dateText.setText(DATE_FORMATTER.format(now));
		timeText.setText(TIME_FORMATTER.format(now));
	}

	private void refreshCounters() {
		chronoCount.setText(String.format("%d secondes", timeProvider.getSecondsTotalRuntime()));
		hoursCount.setText(String.format("%02d", timeProvider.getHoursRuntime()));
		minutesCount.setText(String.format("%02d", timeProvider.getMinutesRuntime()));
		secondsCount.setText(String.format("%02d", timeProvider.getSecondsRuntime()));
	}

	{
		// GUI initializer generated by IntelliJ IDEA GUI Designer
		// >>> IMPORTANT!! <<<
		// DO NOT EDIT OR ADD ANY CODE HERE!
		$$$setupUI$$$();
	}

	/**
	 * Method generated by IntelliJ IDEA GUI Designer >>> IMPORTANT!! <<< DO NOT
	 * edit this method OR call it in your code!
	 *
	 * @noinspection ALL
	 */
	private void $$$setupUI$$$() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0, 0));
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
		startStopButton = new JButton();
		startStopButton.setText("Démarrer / Arrêter");
		buttonsPanel.add(startStopButton);
		resetButton = new JButton();
		resetButton.setText("Remise à zéro");
		buttonsPanel.add(resetButton);
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridBagLayout());
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), null));
		final JPanel spacer1 = new JPanel();
		GridBagConstraints gbc;
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		centerPanel.add(spacer1, gbc);
		hoursLabel = new JLabel();
		Font hoursLabelFont = this.$$$getFont$$$(null, Font.BOLD, 14, hoursLabel.getFont());
		if (hoursLabelFont != null)
			hoursLabel.setFont(hoursLabelFont);
		hoursLabel.setText("Heures:");
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.WEST;
		centerPanel.add(hoursLabel, gbc);
		final JPanel spacer2 = new JPanel();
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.VERTICAL;
		centerPanel.add(spacer2, gbc);
		hoursCount = new JLabel();
		Font hoursCountFont = this.$$$getFont$$$(null, Font.BOLD, 14, hoursCount.getFont());
		if (hoursCountFont != null)
			hoursCount.setFont(hoursCountFont);
		hoursCount.setText("00");
		gbc = new GridBagConstraints();
		gbc.gridx = 4;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.WEST;
		centerPanel.add(hoursCount, gbc);
		minutesLabel = new JLabel();
		Font minutesLabelFont = this.$$$getFont$$$(null, Font.BOLD, 14, minutesLabel.getFont());
		if (minutesLabelFont != null)
			minutesLabel.setFont(minutesLabelFont);
		minutesLabel.setText("Minutes:");
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.WEST;
		centerPanel.add(minutesLabel, gbc);
		minutesCount = new JLabel();
		Font minutesCountFont = this.$$$getFont$$$(null, Font.BOLD, 14, minutesCount.getFont());
		if (minutesCountFont != null)
			minutesCount.setFont(minutesCountFont);
		minutesCount.setText("00");
		gbc = new GridBagConstraints();
		gbc.gridx = 4;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.WEST;
		centerPanel.add(minutesCount, gbc);
		final JPanel spacer3 = new JPanel();
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 5;
		gbc.fill = GridBagConstraints.VERTICAL;
		centerPanel.add(spacer3, gbc);
		secondsLabel = new JLabel();
		Font secondsLabelFont = this.$$$getFont$$$(null, Font.BOLD, 14, secondsLabel.getFont());
		if (secondsLabelFont != null)
			secondsLabel.setFont(secondsLabelFont);
		secondsLabel.setText("Secondes:");
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 6;
		gbc.anchor = GridBagConstraints.WEST;
		centerPanel.add(secondsLabel, gbc);
		secondsCount = new JLabel();
		Font secondsCountFont = this.$$$getFont$$$(null, Font.BOLD, 14, secondsCount.getFont());
		if (secondsCountFont != null)
			secondsCount.setFont(secondsCountFont);
		secondsCount.setText("00");
		gbc = new GridBagConstraints();
		gbc.gridx = 4;
		gbc.gridy = 6;
		gbc.anchor = GridBagConstraints.WEST;
		centerPanel.add(secondsCount, gbc);
		final JPanel spacer4 = new JPanel();
		gbc = new GridBagConstraints();
		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		centerPanel.add(spacer4, gbc);
		countedLabel = new JLabel();
		Font countedLabelFont = this.$$$getFont$$$(null, Font.BOLD, 36, countedLabel.getFont());
		if (countedLabelFont != null)
			countedLabel.setFont(countedLabelFont);
		countedLabel.setForeground(new Color(-34237));
		countedLabel.setHorizontalAlignment(10);
		countedLabel.setText("J'ai déjà compté:");
		countedLabel.setVerticalTextPosition(0);
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		gbc.anchor = GridBagConstraints.SOUTH;
		centerPanel.add(countedLabel, gbc);
		chronoCount = new JLabel();
		Font chronoCountFont = this.$$$getFont$$$(null, Font.BOLD, 36, chronoCount.getFont());
		if (chronoCountFont != null)
			chronoCount.setFont(chronoCountFont);
		chronoCount.setForeground(new Color(-34237));
		chronoCount.setText("0 secondes");
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		gbc.anchor = GridBagConstraints.NORTH;
		centerPanel.add(chronoCount, gbc);
		currentTimePanel = new JPanel();
		currentTimePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		mainPanel.add(currentTimePanel, BorderLayout.NORTH);
		dateLabel = new JLabel();
		dateLabel.setText("Nous sommes le");
		currentTimePanel.add(dateLabel);
		dateText = new JLabel();
		dateText.setText("date here");
		currentTimePanel.add(dateText);
		timeLabel = new JLabel();
		timeLabel.setHorizontalAlignment(2);
		timeLabel.setText("et il est");
		currentTimePanel.add(timeLabel);
		timeText = new JLabel();
		timeText.setText("time here");
		currentTimePanel.add(timeText);
	}

	/**
	 * @noinspection ALL
	 */
	private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
		if (currentFont == null)
			return null;
		String resultName;
		if (fontName == null) {
			resultName = currentFont.getName();
		} else {
			Font testFont = new Font(fontName, Font.PLAIN, 10);
			if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
				resultName = fontName;
			} else {
				resultName = currentFont.getName();
			}
		}
		return new Font(resultName, style >= 0 ? style : currentFont.getStyle(),
				size >= 0 ? size : currentFont.getSize());
	}
}