import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class Driver extends JFrame {

	private ControlButtons controlButtons;

	public Driver() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		this.setTitle("Stop Procrastinating");

		this.controlButtons = new ControlButtons();

		this.add(controlButtons);

		// sizing the window
		pack();

		// program will start out at half the size of the display by default
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);

		// allows the window to be seen and resized
		setVisible(true);
		setResizable(true);

		// listeners
		this.controlButtons.getStart().addActionListener(new StartListener());
		this.controlButtons.getStopEarly().addActionListener(new StopListener());
		this.controlButtons.getWhitelist().addActionListener(new WhitelistListener());
		this.controlButtons.getResetPassword().addActionListener(new ResetPasswordListener());
	}

	public static void main(String[] args) {
		new Driver();
	}

	private class ResetPasswordListener implements ActionListener {

		private Password password;
		@SuppressWarnings("unused")
		private String pass;

		@Override
		public void actionPerformed(ActionEvent arg0) {

			File tempFile = new File("C:\\Productivity\\storage.pw");

			// checks to see if a password exists in the first place
			if (!tempFile.exists()) {
				JOptionPane.showMessageDialog(null, "No password detected. Please click start and create password");
			} else {
				JFrame frame = new JFrame("");
				String tempPass = JOptionPane.showInputDialog(frame, "Enter Password");

				password = new Password();
				pass = password.getEncrypt(tempPass);

				// checks for user to make sure that passwords do match
				try {
					Scanner sc = new Scanner(tempFile);
					String temp = password.getDecrypt(sc.next());

					if (!temp.contentEquals(tempPass)) {
						JOptionPane.showMessageDialog(null, "Password does not match please try again");
					} else {
						System.out.println("pass matches");
						enterNewPassword();
					}
					sc.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}

		private void enterNewPassword() {
			JFrame frame = new JFrame("");
			String firstPassword = JOptionPane.showInputDialog(frame, "Please enter new password");
			String secondPassword = JOptionPane.showInputDialog(frame, "Please enter password again");

			if (firstPassword.equals(secondPassword)) {
				// reads from file and clears current content of password
				File tempFile = new File("C:\\Productivity\\storage.pw");
				try {
					password = new Password();
					PrintWriter writer = new PrintWriter(tempFile);
					writer.print("");
					writer.print(password.getEncrypt(firstPassword));
					writer.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			} else {
				// warns user when passwords do not match
				JOptionPane.showMessageDialog(null, "Passwords do not match please try again");
			}
		}

	}

	private class WhitelistListener implements ActionListener {
		private Password password;
		SystemProcesses sysPros;
		private String pass;

		@Override
		public void actionPerformed(ActionEvent arg0) {
			File tempFile1 = new File("C:\\Productivity\\storage.pw");
			File tempFile2 = new File("C:\\Productivity\\processes.pw");
			File sysFolder = new File ("C:\\Productivity");
			
			if(!sysFolder.exists()) {
				sysFolder.mkdir();
			}
			
			if(!tempFile2.exists()) {
				try {
					tempFile2.createNewFile();
					initPopulateProcesses(tempFile2);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (!tempFile1.exists()) {
				JOptionPane.showMessageDialog(null, "No password detected. Please click start and create password");
			} else {
				JFrame frame = new JFrame("");
				String tempPass = JOptionPane.showInputDialog(frame, "Enter Password");
				
				password = new Password();
				pass = password.getEncrypt(tempPass);
				try {
					Scanner sc = new Scanner(tempFile1);
					String encryptPass = sc.next();
					
					//if passwords match than it will allow user to add programs to the whitelist
					if (!encryptPass.contentEquals(pass)) {
						JOptionPane.showMessageDialog(null, "Password does not match please try again");
					} else {
						System.out.println("pass matches");
						addToWhitelist();
					}
					sc.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}			
		}
		
		private void initPopulateProcesses (File tempFile) {
			FileWriter fw;
			try {
				fw = new FileWriter(tempFile.getAbsoluteFile(), true);
				fw.append("taskhostw.exe");
				fw.append("\n");
				fw.append("NvTelemetryContainer.exe");
				fw.append("\n");
				fw.append("SearchUI.exe");
				fw.append("\n");
				fw.append("iTunesHelper.exe");
				fw.append("\n");
				fw.append("LogiRegistryService.exe");
				fw.append("\n");
				fw.append("Rainmeter.exe");
				fw.append("\n");
				fw.append("MBAMService.exe");
				fw.append("\n");
				fw.append("SearchProtocolHost.exe");
				fw.append("\n");
				fw.append("NahimicVRSvc32.exe");
				fw.append("\n");
				fw.append("lsass.exe");
				fw.append("\n");
				fw.append("dasHost.exe");
				fw.append("\n");
				fw.append("IpOverUsbSvc.exe");
				fw.append("\n");
				fw.append("NVDisplay.Container.exe");
				fw.append("\n");
				fw.append("svchost.exe");
				fw.append("\n");
				fw.append("IAStorDataMgrSvc.exe");
				fw.append("\n");
				fw.append("fontdrvhost.exe");
				fw.append("\n");
				fw.append("NahimicVRSvc64.exe");
				fw.append("\n");
				fw.append("IPROSetMonitor.exe");
				fw.append("\n");
				fw.append("sihost.exe");
				fw.append("\n");
				fw.append("EyeRest.exe");
				fw.append("\n");
				fw.append("MSI_LED.exe");
				fw.append("\n");
				fw.append("RuntimeBroker.exe");
				fw.append("\n");
				fw.append("GamingHotkey_Service.exe");
				fw.append("\n");
				fw.append("CorsairLink4.exe");
				fw.append("\n");
				fw.append("SearchFilterHost.exe");
				fw.append("\n");
				fw.append("AppleMobileDeviceHelper.exe");
				fw.append("\n");
				fw.append("mbamtray.exe");
				fw.append("\n");
				fw.append("WmiPrvSE.exe");
				fw.append("\n");
				fw.append("MSASCuiL.exe");
				fw.append("\n");
				fw.append("CtHdaSvc.exe");
				fw.append("\n");
				fw.append("EPCP.exe");
				fw.append("\n");
				fw.append("PfuSsMon.exe");
				fw.append("\n");
				fw.append("GamingHotkey.exe");
				fw.append("\n");
				fw.append("GamingHotkey.exe");
				fw.append("\n");
				fw.append("OfficeClickToRun.exe");
				fw.append("\n");
				fw.append("MicrosoftEdgeCP.exe");
				fw.append("\n");
				fw.append("CorsairLink4.Service.exe");
				fw.append("\n");
				fw.append("iPodService.exe");
				fw.append("\n");
				fw.append("Agent.exe");
				fw.append("\n");
				fw.append("smartscreen.exe");
				fw.append("\n");
				fw.append("AppleMobileDeviceService.exe");
				fw.append("\n");
				fw.append("dllhost.exe");
				fw.append("\n");
				fw.append("EEventManager.exe");
				fw.append("\n");
				fw.append("smss.exe");
				fw.append("\n");
				fw.append("System Idle Process");
				fw.append("\n");
				fw.append("escsvc64.exe");
				fw.append("\n");
				fw.append("eclipse.exe");
				fw.append("\n");
				fw.append("ICCProxy.exe");
				fw.append("\n");
				fw.append("iTunes.exe");
				fw.append("\n");
				fw.append("SettingSyncHost.exe");
				fw.append("\n");
				fw.append("IAStorIcon.exe");
				fw.append("\n");
				fw.append("NahimicMonitor.exe");
				fw.append("\n");
				fw.append("tasklist.exe");
				fw.append("\n");
				fw.append("sqlwriter.exe");
				fw.append("\n");
				fw.append("TriggerModeMonitor.exe");
				fw.append("\n");
				fw.append("ApplicationFrameHost.exe");
				fw.append("\n");
				fw.append("distnoted.exe");
				fw.append("\n");
				fw.append("jucheck.exe");
				fw.append("\n");
				fw.append("foobar2000.exe");
				fw.append("\n");
				fw.append("SBZ.exe");
				fw.append("\n");
				fw.append("browser_broker.exe");
				fw.append("\n");
				fw.append("ImeBroker.exe");
				fw.append("\n");
				fw.append("spoolsv.exe");
				fw.append("\n");
				fw.append("javaw.exe");
				fw.append("\n");
				fw.append("csrss.exe");
				fw.append("\n");
				fw.append("winlogon.exe");
				fw.append("\n");
				fw.append("MsiGamingOSD_x86.exe");
				fw.append("\n");
				fw.append("explorer.exe");
				fw.append("\n");
				fw.append("VideoCardMonitorII.exe");
				fw.append("\n");
				fw.append("wallpaper32.exe");
				fw.append("\n");
				fw.append("ctfmon.exe");
				fw.append("\n");
				fw.append("System");
				fw.append("\n");
				fw.append("MicrosoftEdge.exe");
				fw.append("\n");
				fw.append("RtkNGUI64.exe");
				fw.append("\n");
				fw.append("wininit.exe");
				fw.append("\n");
				fw.append("E_IATIJAE.EXE");
				fw.append("\n");
				fw.append("armsvc.exe");
				fw.append("\n");
				fw.append("ShellExperienceHost.exe");
				fw.append("\n");
				fw.append("MsMpEng.exe");
				fw.append("\n");
				fw.append("mDNSResponder.exe");
				fw.append("\n");
				fw.append("NisSrv.exe");
				fw.append("\n");
				fw.append("SearchIndexer.exe");
				fw.append("\n");
				fw.append("muachost.exe");
				fw.append("\n");
				fw.append("LCore.exe");
				fw.append("\n");
				fw.append("SecurityHealthService.exe");
				fw.append("\n");
				fw.append("CTAudSvc.exe");
				fw.append("\n");
				fw.append("Core Temp.exe");
				fw.append("\n");
				fw.append("services.exe");
				fw.append("\n");
				fw.append("MsiGamingOSD_x64.exe");
				fw.append("\n");
				fw.append("audiodg.exe");
				fw.append("\n");
				fw.append("conhost.exe");
				fw.append("\n");
				fw.append("SystemSettingsAdminFlows.exe");
				fw.append("\n");
				fw.append("GamingApp_Service.exe");
				fw.append("\n");
				fw.append("MSI_ActiveX_Service.exe");
				fw.append("\n");
				fw.append("jusched.exe");
				fw.append("\n");
				fw.append("deluge.exe");
				fw.append("\n");
				fw.append("fontdrvhost.exe");
				fw.append("\n");
				fw.append("IAStorDataMgrSvc.exe");
				fw.append("\n");
				fw.append("svchost.exe");
				fw.append("\n");
				fw.append("NVDisplay.Container.exe");
				fw.append("\n");
				fw.append("IpOverUsbSvc.exe");
				fw.append("\n");
				fw.append("dasHost.exe");
				fw.append("\n");
				fw.append("dwm.exe");
				fw.append("\n");
				fw.append("lsass.exe");
				fw.append("\n");
				fw.append("NahimicVRSvc32.exe");
				fw.append("\n");
				fw.append("MBAMService.exe");
				fw.append("\n");
				fw.append("chrome.exe");
				fw.append("\n");
				fw.append("AppleMobileDeviceService.exe");
				fw.append("\n");
				fw.append("System Idle Process");
				fw.append("\n");
				fw.append("TorGuardDesktopQt.exe");
				fw.append("\n");
				fw.append("openvpn_v2_4.exe");
				fw.append("\n");
				fw.append("Core Temp.exe");
				fw.append("\n");
				fw.append("AppleMobileDeviceService.");
				fw.append("\n");
				fw.append("System Idle Process");
				fw.append("\n");
				fw.append("notepad++.exe");
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
		
		//adds programs to the whitelist and appends them to the end of the processes file
		private void addToWhitelist() {
			File tempFile = new File("C:\\Productivity\\processes.pw");
			try {
				sysPros = new SystemProcesses();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			// gets processes from saved processes file
			ArrayList<String> processes = new ArrayList<String>();
			try {
				Scanner sc = new Scanner(tempFile);
				while (sc.hasNext()) {
					processes.add(sc.next());
				}
				sc.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			// arrays for removing the extra applications
			ArrayList<String> newPros = sysPros.getSystemProcessesNoDuplicates();
			ArrayList<String> noDupes = new ArrayList<String>();

			for (int i = 0; i < newPros.size(); i++) {
				if (!processes.contains(newPros.get(i))) {
					if(!newPros.get(i).contains(" ")) {
						noDupes.add(newPros.get(i));
					}
				}
			}

			String[] noDupesArray = noDupes.toArray(new String[0]);

			@SuppressWarnings({ "unchecked", "rawtypes" })
			JList list = new JList(noDupesArray);
			JOptionPane.showMessageDialog(null, list, "Multi-Select Example", JOptionPane.PLAIN_MESSAGE);

			// this is where I will add the selection to the processes whitelist file after
			// password verification
			if(!(list.getSelectedValue() == null)) {
				try {
					FileWriter fw = new FileWriter(tempFile.getAbsoluteFile(), true);
					fw.append("\n" + (CharSequence) list.getSelectedValue());
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}

	}

	private class StartListener implements ActionListener {
		private Password password;
		private String pass;

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// creates file
			File tempFile = new File("C:\\Productivity\\storage.pw");
			File tempDir = new File("C:\\\\Productivity");

			// base processes from my own machine
			addBaseProcesses();

			// creates directory if it does not exist
			tempDir.mkdir();

			// creates file if it does not exist
			if (!tempFile.exists()) {
				try {
					tempFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			// checks if file is empty if does asks user for an initial password
			if (tempFile.length() == 0) {
				JFrame frame = new JFrame("");
				String tempPass = JOptionPane.showInputDialog(frame, "No password detected please enter your password");
				password = new Password();
				pass = password.getEncrypt(tempPass);
				enterPassword();
			}

			// this is where I start to killing the processes

			// toggles the button
			controlButtons.toggleWhitelist();
			controlButtons.toggleStartStop();
			

		}

		// writes to file for a new password and asks user to re enter their password to
		// secure that it is typed in properly
		private void enterPassword() {
			File tempFile = new File("C:\\Productivity\\storage.pw");
			try {
				FileWriter fileWriter = new FileWriter(tempFile);
				PrintWriter printWriter = new PrintWriter(fileWriter);
				printWriter.print(pass);
				printWriter.close();
				fileWriter.close();
				try {
					Scanner sc = new Scanner(tempFile);
					String readpw = password.getDecrypt(sc.next());
					JFrame frame = new JFrame("");
					String securePass = JOptionPane.showInputDialog(frame, "Please enter password again");
					if (!readpw.equals(securePass)) {
						enterPassword();
					}
					sc.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private void addBaseProcesses() {
			String pros[] = new String[105];
			pros[0] = "taskhostw.exe";
			pros[1] = "MicrosoftEdgeCP.exe";
			pros[2] = "OfficeClickToRun.exe";
			pros[3] = "GamingHotkey.exe";
			pros[4] = "PfuSsMon.exe";
			pros[5] = "EPCP.exe";
			pros[6] = "CtHdaSvc.exe";
			pros[7] = "MSASCuiL.exe";
			pros[8] = "WmiPrvSE.exe";
			pros[9] = "mbamtray.exe";
			pros[10] = "AppleMobileDeviceHelper.exe";
			pros[11] = "SearchFilterHost.exe";
			pros[12] = "CorsairLink4.exe";
			pros[13] = "GamingHotkey_Service.exe";
			pros[14] = "RuntimeBroker.exe";
			pros[15] = "MSI_LED.exe";
			pros[16] = "EyeRest.exe";
			pros[17] = "sihost.exe";
			pros[18] = "IPROSetMonitor.exe";
			pros[19] = "NahimicVRSvc64.exe";
			pros[20] = "fontdrvhost.exe";
			pros[21] = "IAStorDataMgrSvc.exe";
			pros[22] = "svchost.exe";
			pros[23] = "NVDisplay.Container.exe";
			pros[24] = "IpOverUsbSvc.exe";
			pros[25] = "dasHost.exe";
			pros[26] = "dwm.exe";
			pros[27] = "lsass.exe";
			pros[28] = "NahimicVRSvc32.exe";
			pros[29] = "SearchProtocolHost.exe";
			pros[20] = "MBAMService.exe";
			pros[21] = "Rainmeter.exe";
			pros[22] = "LogiRegistryService.exe";
			pros[23] = "iTunesHelper.exe";
			pros[24] = "SearchUI.exe";
			pros[25] = "NvTelemetryContainer.exe";
			pros[26] = "CorsairLink4.Service.exe";
			pros[27] = "iPodService.exe";
			pros[28] = "Agent.exe";
			pros[29] = "smartscreen.exe";
			pros[20] = "AppleMobileDeviceService.exe";
			pros[31] = "dllhost.exe";
			pros[32] = "EEventManager.exe";
			pros[33] = "smss.exe";
			pros[34] = "System Idle Process";
			pros[35] = "escsvc64.exe";
			pros[36] = "eclipse.exe";
			pros[37] = "ICCProxy.exe";
			pros[38] = "iTunes.exe";
			pros[39] = "SettingSyncHost.exe";
			pros[40] = "IAStorIcon.exe";
			pros[41] = "NahimicMonitor.exe";
			pros[42] = "tasklist.exe";
			pros[43] = "sqlwriter.exe";
			pros[44] = "TriggerModeMonitor.exe";
			pros[45] = "ApplicationFrameHost.exe";
			pros[46] = "distnoted.exe";
			pros[47] = "jucheck.exe";
			pros[48] = "foobar2000.exe";
			pros[49] = "SBZ.exe";
			pros[50] = "browser_broker.exe";
			pros[51] = "ImeBroker.exe";
			pros[52] = "spoolsv.exe";
			pros[53] = "javaw.exe";
			pros[54] = "csrss.exe";
			pros[55] = "winlogon.exe";
			pros[56] = "MsiGamingOSD_x86.exe";
			pros[57] = "explorer.exe";
			pros[58] = "VideoCardMonitorII.exe";
			pros[59] = "wallpaper32.exe";
			pros[60] = "ctfmon.exe";
			pros[61] = "System";
			pros[62] = "MicrosoftEdge.exe";
			pros[63] = "RtkNGUI64.exe";
			pros[64] = "wininit.exe";
			pros[65] = "E_IATIJAE.EXE";
			pros[66] = "armsvc.exe";
			pros[67] = "ShellExperienceHost.exe";
			pros[68] = "MsMpEng.exe";
			pros[69] = "mDNSResponder.exe";
			pros[70] = "NisSrv.exe";
			pros[71] = "SearchIndexer.exe";
			pros[72] = "muachost.exe";
			pros[73] = "LCore.exe";
			pros[74] = "SecurityHealthService.exe";
			pros[75] = "CTAudSvc.exe";
			pros[76] = "Core Temp.exe";
			pros[77] = "services.exe";
			pros[78] = "MsiGamingOSD_x64.exe";
			pros[79] = "audiodg.exe";
			pros[80] = "conhost.exe";
			pros[81] = "SystemSettingsAdminFlows.exe";
			pros[82] = "GamingApp_Service.exe";
			pros[83] = "MSI_ActiveX_Service.exe";
			pros[84] = "jusched.exe";
			pros[85] = "deluge.exe";
			pros[86] = "fontdrvhost.exe";
			pros[87] = "IAStorDataMgrSvc.exe";
			pros[88] = "svchost.exe";
			pros[89] = "NVDisplay.Container.exe";
			pros[90] = "IpOverUsbSvc.exe";
			pros[91] = "dasHost.exe";
			pros[92] = "dwm.exe";
			pros[93] = "lsass.exe";
			pros[94] = "NahimicVRSvc32.exe";
			pros[95] = "MBAMService.exe";
			pros[96] = "chrome.exe";
			pros[97] = "AppleMobileDeviceService.exe";
			pros[98] = "System Idle Process";
			pros[99] = "TorGuardDesktopQt.exe";
			pros[100] = "openvpn_v2_4.exe";
			pros[101] = "Core Temp.exe";
			pros[102] = "AppleMobileDeviceService.";
			pros[103] = "System Idle Process";
			pros[104] = "notepad++.exe";

			File tempFile = new File("C:\\Productivity\\processes.pw");
			if (tempFile.length() == 0) {
				try {
					FileWriter fileWriter = new FileWriter(tempFile);
					PrintWriter printWriter = new PrintWriter(fileWriter);
					for (int i = 0; i < pros.length; i++) {
						if (i == pros.length - 1) {
							printWriter.write(pros[i]);
						} else {
							printWriter.write(pros[i] + "\n");
						}
					}
					printWriter.close();
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private class StopListener implements ActionListener {
		private Password password;

		@Override
		public void actionPerformed(ActionEvent arg0) {

			// brings up password field and if passwords match it cancels the processes from
			// being blocked if not they keep being blocked
			JPasswordField pf = new JPasswordField();
			int okCxl = JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE);
			if (okCxl == JOptionPane.OK_OPTION) {
				String pass = new String(pf.getPassword());
				File tempFile = new File("C:\\Productivity\\storage.pw");
				Scanner sc;
				try {
					password = new Password();
					sc = new Scanner(tempFile);
					String readpw = password.getDecrypt(sc.next());
					if (readpw.equals(pass)) {
						// this is where I stop the processes from being blocked

						controlButtons.toggleStartStop();
						controlButtons.toggleWhitelist();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

			}

		}
	}

}
