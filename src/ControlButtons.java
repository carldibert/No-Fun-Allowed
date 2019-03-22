import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class ControlButtons extends JPanel {
	private JToolBar toolbar;
	private JButton start;
	private JButton stopEarly;
	private JButton whitelist;
	private JButton resetPassword;
	private Runner run1;
	private ScheduledExecutorService ex;
	private SystemProcesses sys;

	public ControlButtons() {
		toolbar = new JToolBar();
		start = new JButton();
		stopEarly = new JButton();
		whitelist = new JButton();
		resetPassword = new JButton();
		run1 = new Runner();
		ex = Executors.newSingleThreadScheduledExecutor();

		try {
			sys = new SystemProcesses();
		} catch (IOException e) {

			e.printStackTrace();
		}

		start.setText("Start");
		stopEarly.setText("Stop Early");
		whitelist.setText("Open Whitelist");
		resetPassword.setText("Reset Password");
		

		toolbar.add(start);
		toolbar.add(stopEarly);
		toolbar.add(whitelist);
		toolbar.add(resetPassword);

		stopEarly.setVisible(false);

		toolbar.setFloatable(false);

		this.add(toolbar);
	}

	// toggles the start and stop buttons
	public void toggleStartStop() {
		if (start.isVisible() == true) {
			// toggles visibility of buttons
			start.setVisible(false);
			stopEarly.setVisible(true);

			// closes processes that are open every 10 seconds
			ex = Executors.newSingleThreadScheduledExecutor();
			ex.scheduleAtFixedRate(run1, 0, 60, TimeUnit.SECONDS);
		} else {
			// toggles visibility of buttons
			start.setVisible(true);
			stopEarly.setVisible(false);

			// cancels the shutdown of processes
			ex.shutdown();
		}
	}

	private void showPros() {

		// gets processes from saved processes file
		ArrayList<String> processes = new ArrayList<String>();
		File tempFile = new File("C:\\Productivity\\processes.pw");
		try {
			Scanner sc = new Scanner(tempFile);
			while (sc.hasNext()) {
				processes.add(sc.next());
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// grabs current processes that are running
		String[] currentPros = new String[sys.getSystemProcessesNoDuplicates().size()];
		for (int i = 0; i < sys.getSystemProcessesNoDuplicates().size(); i++) {
			currentPros[i] = sys.getSystemProcessesNoDuplicates().get(i);
		}

		// gets a list of processes to be ended
		ArrayList<String> prosToBeEnded = new ArrayList<String>();
		for (int i = 0; i < currentPros.length; i++) {
			if (!processes.contains(currentPros[i])) {
				if(!currentPros[i].contains(" ")) {
					prosToBeEnded.add(currentPros[i]);
				}
			}
		}

		// kills all processes that are not within the whitelist
		for (int i = 0; i < prosToBeEnded.size(); i++) {
			String tempS = "taskkill /F /IM " + prosToBeEnded.get(i);
			System.out.println(tempS);
			try {
				Runtime.getRuntime().exec(tempS);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	// runs the process stopper
	private class Runner extends Thread {
		public void run() {
			showPros();

		}
	}

	public void toggleWhitelist() {
		if (whitelist.isVisible()) {
			whitelist.setVisible(false);
		} else {
			whitelist.setVisible(true);
		}
	}

	public JToolBar getToolbar() {
		return toolbar;
	}

	public JButton getStart() {
		return start;
	}

	public JButton getStopEarly() {
		return stopEarly;
	}

	public JButton getWhitelist() {
		return whitelist;
	}

	public JButton getResetPassword() {
		return resetPassword;
	}

}
