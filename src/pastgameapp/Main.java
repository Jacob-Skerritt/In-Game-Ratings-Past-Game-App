/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pastgameapp;

import java.util.Scanner;
public class Main {
    
    public static void main(String[] args) throws InterruptedException{
        
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the In-Game Ratings Past Fixture App\n Type Exit to close!");
        String id = "";
        String startTime;
        while(!id.toLowerCase().equals("exit")){
            
            System.out.print("\nEntre Fixture ID: ");
            id = sc.nextLine();
            if (!id.matches("[0-9]+"))
                continue;
            
            System.out.print("\nEnter Start Time: ");
            startTime = sc.nextLine();
            if (!startTime.matches("[0-9]+"))
                continue;
            
            PastGameApp task = new PastGameApp(id, startTime);
            Thread T1 = new Thread(task);
            T1.start();
            Thread.sleep(1000);

            
            
        }
        
    }
}
