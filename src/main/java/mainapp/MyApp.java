// Main application file for "Git Revert the Changes" project
package mainapp;

import java.util.regex.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class MyApp {

    // Method to check if .git directory exists
    public static String directoryExists() {
        String gitDirectoryExists;
        try {
            System.out.println("Checking if .git directory exists...");
            gitDirectoryExists = executeCommand("git rev-parse --is-inside-work-tree").trim();
            System.out.println("Git Directory Exists: " + gitDirectoryExists);
            return gitDirectoryExists.equals("true") ? "true" : "false";
        } catch (Exception e) {
            System.out.println("Error in directoryExists method: " + e.getMessage());
            return "";
        }
    }

    // Method to check if there is at least one commit to revert
    public static String atleastOneCommitToRevert() {
        String atleastOneCommit;
        try {
            System.out.println("Checking if there is at least one commit to revert...");
            atleastOneCommit = executeCommand("git log --oneline").trim();
            System.out.println("At least one commit: " + atleastOneCommit);
            return !atleastOneCommit.isEmpty() ? "true" : "false";
        } catch (Exception e) {
            System.out.println("Error in atleastOneCommitToRevert method: " + e.getMessage());
            return "";
        }
    }

    // Method to check if any commit contains "Revert" in the commit message
    public static String revertSuccess() {
        String commitLog;
        try {
            System.out.println("Checking if any commit has 'Revert' in the message...");
            // Fetch the complete commit log
            commitLog = executeCommand("git log --oneline").trim();
            System.out.println("Commit log: " + commitLog);

            // Compile a pattern to search for the word "Revert" (case-insensitive)
            Pattern revertPattern = Pattern.compile("Revert", Pattern.CASE_INSENSITIVE);
            Matcher revertMatcher = revertPattern.matcher(commitLog);

            // Check if any commit message contains "Revert"
            if (revertMatcher.find()) {
                System.out.println("A commit with 'Revert' was found.");
                return "true";
            } else {
                System.out.println("No commit with 'Revert' found.");
                return "false";
            }
        } catch (Exception e) {
            System.out.println("Error in revertSuccess method: " + e.getMessage());
            return "";
        }
    }
    
    public static void main(String[] args) {
        try {
            // Check if .git directory exists
            String gitDirectoryExists = directoryExists();
            if (gitDirectoryExists.equals("true")) {
                System.out.println("Git repository initialized successfully.");
            } else {
                System.out.println("Git repository not initialized.");
                return;
            }

            // Check for at least one commit to revert
            String atleastOneCommit = atleastOneCommitToRevert();
            if (atleastOneCommit.equals("true")) {
                System.out.println("There are commits to revert.");
            } else {
                System.out.println("No commits to revert.");
                return;
            }

            // Check if revert was successful
            String revertSuccess = revertSuccess();
            if (revertSuccess.equals("true")) {
                System.out.println("Revert operation was successful.");
            } else {
                System.out.println("Revert operation failed.");
            }

        } catch (Exception e) {
            System.out.println("Error in main method: " + e.getMessage());
        }
    }

    private static String executeCommand(String command) throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.directory(new File("utils")); // Ensure this is correct
        processBuilder.command("bash", "-c", command);
        System.out.println("Executing command: " + command);
        Process process = processBuilder.start();

        StringBuilder output = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        int exitVal = process.waitFor();
        System.out.println("Command executed with exit code: " + exitVal);
        if (exitVal == 0) {
            return output.toString();
        } else {
            System.out.println("Command failed with exit code: " + exitVal);
            throw new RuntimeException("Failed to execute command: " + command);
        }
    }
}
