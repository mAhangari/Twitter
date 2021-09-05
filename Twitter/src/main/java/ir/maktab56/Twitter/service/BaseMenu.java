package ir.maktab56.Twitter.service;

import java.util.*;

public class BaseMenu {
	
	static Scanner input = new Scanner(System.in);
	public static final String WRONG_NUMBER = "Invalid Number Insert!!";
	public static final String WELCOME = "Welcome to your Page";
	public static final String SUCCESS_OPERATION = "Operation Successed!!!";
	
	
	public static void singlePrintMessage(String message) {
		String[] messages = null;
		String text = "";
		if(message.length() > 37) {
			messages = message.split(" ");
			System.out.println("    +--------------------------------------------+");
			for(int i = 0; i < messages.length; i++) {
				if(text.length() + messages[i].length() < 37)
					text += messages[i] + " "; 
				else {
					System.out.printf("%5s     %-37s%3s\n", "|", text, "|");
					text = messages[i] + " ";
				}
			}
			if(!text.isBlank())
				System.out.printf("%5s     %-37s%3s\n", "|", text, "|");
			System.out.println("    +--------------------------------------------+");
		}
		else {
			System.out.println("    +--------------------------------------------+");
			System.out.printf("%5s     %-37s%3s\n", "|", message, "|");
			System.out.println("    +--------------------------------------------+");
		}
	}
	
	public static <T> void singlePrintMessage(String message, T args) {
		String[] messages = null;
		String text = "";
		if(message.length() > 37) {
			messages = message.split(" ");
			System.out.println("    +--------------------------------------------+");
			for(int i = 0; i < messages.length; i++) {
				if(text.length() + messages[i].length() < 37)
					text += messages[i] + " "; 
				else {
					System.out.printf("%5s     %-37s%3s\n", "|", text, "|");
					text = messages[i] + " ";
				}
			}
			if(!text.isBlank())
				System.out.printf("%5s     %-37s%3s\n", "|", text, "|");
			System.out.printf("%5s     %-37s%3s\n", "|", ("     " + args), "|");
			System.out.println("    +--------------------------------------------+");
		}
		else {
			System.out.println("    +--------------------------------------------+");
			System.out.printf("%5s     %-37s%3s\n", "|", message + ": " + args, "|");
			System.out.println("    +--------------------------------------------+");
		}
	}
	
	public static void singleSetMessage(String message) {
		String[] messages = null;
		String text = "";
		if(message.length() > 37) {
			messages = message.split(" ");
			System.out.println("    +--------------------------------------------+");
			for(int i = 0; i < messages.length; i++) {
				if(text.length() + messages[i].length() < 37)
					text += messages[i] + " "; 
				else {
					System.out.printf("%5s     %-37s%3s\n", "|", text, "|");
					text = messages[i] + " ";
				}
			}
			if(!text.isBlank())
				System.out.printf("%5s     %-37s%3s\n", "|", text, "|");
			System.out.println("    +--------------------------------------------+");
			System.out.print("           :: ");
		}
		else {
			System.out.println("    +--------------------------------------------+");
			System.out.printf("%5s     %-37s%3s\n", "|", message, "|");
			System.out.println("    +--------------------------------------------+");
			System.out.print("           :: ");
		}
	}
	
	public static void optionMessage(List<String> messages, boolean activeSelectOption) {
		System.out.println("    +----------------------------------------------+");
		for(int index = 0; index < messages.size(); index++)
			System.out.printf("%5s      %-37s%4s\n", "|", (index+1 + ". " + messages.get(index)), "|");
		System.out.println("    +----------------------------------------------+");
		if(activeSelectOption)
			System.out.print("       Select an Option: ");
	}
	
	public static void optionMessageWithTitle(List<String> messages, String title, boolean activeSelectOption) {
		
		int size;
		if(title.length() % 2 == 0) {
			size = 30 - title.length();
		}
		else {
			size = 30 - title.length();
			title += " ";
		}
		String str1 = "";
		for(int i = 0; i < size/2; i++)
			str1 += "-";
		title = String.format("+" + str1 + " %s " + str1 + "+", title);

		title = "    " + title;
		System.out.println(title);
		
		
		for(int index = 0; index < messages.size(); index++)
			System.out.printf("%5s      %-37s%3s\n", "|", (index+1 + ". " + messages.get(index)), "|");
		System.out.println("    +---------------------------------------------+");
		if(activeSelectOption)
			System.out.print("       Select an Option: ");
	}
	
}
