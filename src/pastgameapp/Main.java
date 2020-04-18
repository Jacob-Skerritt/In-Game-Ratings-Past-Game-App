/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pastgameapp;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Timer;
public class Main {
    
    public static void main(String[] args) throws InterruptedException, SQLException, PropertyVetoException{
        
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the In-Game Ratings Past Fixture App\nType Exit to close!");
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
            Timer timer = new Timer();
            PastGameApp pastGame = new PastGameApp(id,startTime, timer);
            
            timer.schedule(pastGame, 0, 1000);
            Thread.sleep(1000);
        }
        
    }
}
