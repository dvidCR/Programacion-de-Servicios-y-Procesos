package clase_25092024;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		String command1 = "notepad.exe";
		ProcessBuilder pb1 = new ProcessBuilder(command1);

		ProcessBuilder pb2 = new ProcessBuilder("cmd.exe", "/c", "dir", "/o");
		
		try {
			pb1.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			pb2.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ProcessHandle processHandle = ProcessHandle.current();
		ProcessHandle.Info processInfo = processHandle.info();

		System.out.println("PID: " + processHandle.pid());
		System.out.println("Arguments: " + processInfo.arguments());
		System.out.println("Command: " + processInfo.command());
		System.out.println("Instant: " + processInfo.startInstant());
		System.out.println("Total CPU duration: " + processInfo.totalCpuDuration());
		System.out.println("User: " + processInfo.user());

	}

}
