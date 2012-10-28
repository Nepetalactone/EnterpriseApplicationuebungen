/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Interface.ISquare;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.net.util.IPAddressUtil;

/**
 *
 * @author Tobias
 */
public class Client {

    private static ISquare stub = null;

    private Client() {
    }

    ;
    
    public static void main(String[] args) throws IOException {

        /*
         * try{
         *
         * }catch(Exception e){ System.out.println("Malformed input");
        }
         */

        try {//Parse IP-address
            String address = args[0];   
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while (!IPAddressUtil.isIPv4LiteralAddress(address)) {
                System.out.print("Malformed input. Enter proper IPv4-address");
                address = in.readLine();
            }
            Registry registry = LocateRegistry.getRegistry(address, 33333);
            stub = (ISquare) registry.lookup("Square");
        } catch (NotBoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AccessException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {//Parse number
            boolean parse = false;
            int number = 0;
            String tempNumber;
            
            try{
                number = Integer.parseInt(args[1]);
            } catch (Exception e){
            
            System.out.println("Malformed Input. Enter proper integer");
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            tempNumber = in.readLine();
            
            while (parse == false) {
                try {
                    number = Integer.parseInt(tempNumber);
                    parse = true;
                } catch (Exception ex) {
                        System.out.println("Malformed Input. Enter proper integer");
                        tempNumber = in.readLine();
                    }
                }
            }
            System.out.println(stub.calculateSquare(number)); //Send number to server, output result
        } catch (RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
