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
        System.out.println("Type Exit to close!");
        System.out.println("Entre Fixture ID:");
        String id = sc.nextLine();
        while(!id.toLowerCase().equals("exit")){
            
            PastGameApp task = new PastGameApp(id);
            Thread T1 = new Thread(task);
            T1.start();
            Thread.sleep(1000);
            System.out.println("Entre Fixture ID:");
            id = sc.nextLine();
        }
        
    }
}
