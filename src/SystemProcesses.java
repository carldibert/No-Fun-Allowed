import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SystemProcesses {
	private ArrayList<String> systemProcessesNoDuplicates;
	private ArrayList<String> systemProcesses;

	// returning list of system processes with duplicates
	public ArrayList<String> getSystemProcesses() {
		return systemProcesses;
	}

	// returning non duplicate list of system processes
	public ArrayList<String> getSystemProcessesNoDuplicates() {
		return systemProcessesNoDuplicates;
	}

	public SystemProcesses() throws IOException {
		// initialization
		systemProcessesNoDuplicates = new ArrayList<String>();
		systemProcesses = new ArrayList<String>();

		// gathering system processes
		String taskListExe = System.getenv("windir") + "\\system32\\" + "tasklist.exe";
		Process p = Runtime.getRuntime().exec(new String[] { taskListExe, "/v" });

		// reading system processes
		BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
		Pattern pattern = Pattern.compile("(.*?)\\s+(\\d+).*(\\d+:\\d+:\\d+)\\s+(.*?)");
		String line;

		// checking strings if they match the exact executables names
		while ((line = input.readLine()) != null) {
			Matcher matcher = pattern.matcher(line);
			if (matcher.matches()) {
				systemProcesses.add(matcher.group(1).toString());
				systemProcessesNoDuplicates.add(matcher.group(1).toString());
			}
		}
		input.close();

		// removing any duplicate processes
		Set<String> set = new HashSet<>(systemProcessesNoDuplicates);
		systemProcessesNoDuplicates.clear();
		systemProcessesNoDuplicates.addAll(set);
	}
}