/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corbaserver;


import corbacookie.Cookie.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;
/**
 *
 * @author Tobias
 */
public class CorbaServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws org.omg.CORBA.ORBPackage.InvalidName {
        try {
            ORB orb = ORB.init(args, null);
            
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();
            
            CorbaServant servant = new CorbaServant();
            servant.setORB(orb);
            
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(servant);
            cookieserver href = cookieserverHelper.narrow(ref);
            
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            
            String name = "Cookie";
            NameComponent path[] = ncRef.to_name( name );
            ncRef.rebind(path, href);
            
            System.out.println("Server up and running");
            orb.run();
        } catch (NotFound ex) {
            Logger.getLogger(CorbaServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CannotProceed ex) {
            Logger.getLogger(CorbaServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidName ex) {
            Logger.getLogger(CorbaServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServantNotActive ex) {
            Logger.getLogger(CorbaServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WrongPolicy ex) {
            Logger.getLogger(CorbaServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AdapterInactive ex) {
            Logger.getLogger(CorbaServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
