package  Atm;

import java.util.*;
public class Main {

    static Scanner sc = new Scanner(System.in);
    static JavaDatabase db = new JavaDatabase();

    public static void functions(int user){
        while(true){
            System.out.println("\n\nEnter numbers accordingly\n1. Transaction History \n2. Withdraw \n3. Deposit \n4. Transfer \n5.Quit\n");
            System.out.print("Enter : ");
            int move = sc.nextInt();
            if(move == 1){
                db.hist(user);
            }
            if(move == 2){
                System.out.print("Enter amount to withdraw : ");
                int red = sc.nextInt();
                db.getsam(user,red);
            }
            if(move == 3){
                System.out.print("Enter amount to put : ");
                int red = sc.nextInt();
                db.semsam(user,red);
            }
            if(move == 4){
                System.out.print("Enter amount to transfer : ");
                int red = sc.nextInt();
                System.out.print("Enter user_id of whom to transfer : ");
                int tf = sc.nextInt();
                db.getsam(user,red);
                db.semsam(tf,red);
                System.out.print("Transfered Sucessfully !");
            }
            if(move == 5){
                break;
            }
        }
    }

    public static void main(String[] args ){
        db.connect_db();
        while(true){
            System.out.println("Enter Id :");
            int user_id = sc.nextInt();
            System.out.println("Enter Password :");
            int password = sc.nextInt();
            if(db.check(user_id,password)){
                System.out.println("You Logined !!!\n\n");
                functions(user_id);
                System.out.println("Thank you !!");
                break;
            }
        }

    }
}