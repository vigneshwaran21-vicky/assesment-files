package com.example;
import java.util.Scanner;
public class App {
    public static String getEligibilityStatus(int age, boolean isCitizen, boolean hasValidID) {
        if (age < 18) return "Ineligible (Reason: Underage)";
        if (!isCitizen) return "Ineligible (Reason: Not a citizen)";
        if (!hasValidID) return "Ineligible (Reason: Invalid ID)";
        return "Eligible";
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("--- Voting Eligibility System ---");
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        System.out.print("Is Citizen (true/false): ");
        boolean citizen = sc.nextBoolean();
        System.out.print("Is ID Valid (true/false): ");
        boolean validID = sc.nextBoolean();
        String status = getEligibilityStatus(age, citizen, validID);
        System.out.println("\nVoter: " + name);
        System.out.println("Status: " + status);
        sc.close();
    }
}
