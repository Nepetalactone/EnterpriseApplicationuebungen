/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corbaclient;

import corbacookie.Cookie.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
/**
 *
 * @author Tobias
 */
public class CorbaClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            String name = "Cookie";
            cookieserver servant = cookieserverHelper.narrow(ncRef.resolve_str(name));
            
            System.out.println("Obtained handle on server object" + servant.toString());
            
            int i = Integer.parseInt(args[4]);
            System.out.println(servant.printCookieNum(i));
            System.out.println(servant.printCookie());
        } catch (NotFound ex) {
            Logger.getLogger(CorbaClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CannotProceed ex) {
            Logger.getLogger(CorbaClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (org.omg.CosNaming.NamingContextPackage.InvalidName ex) {
            Logger.getLogger(CorbaClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidName ex) {
            Logger.getLogger(CorbaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
