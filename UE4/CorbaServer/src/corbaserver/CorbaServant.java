/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corbaserver;

import corbacookie.Cookie.cookieserverPOA;
import java.io.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.ORB;

/**
 *
 * @author Tobias
 */
public class CorbaServant extends cookieserverPOA {

    private ORB orb;
    BufferedReader reader = null;
    String result = null;

    public void setORB(ORB orb_val) {
        orb = orb_val;
    }

    @Override
    public String printCookieNum(int i) {
        BufferedReader reader = null;
        String result = null;
        try {
            int j = 0;
            reader = new BufferedReader(new InputStreamReader(CorbaServant.class.getResourceAsStream("/fortunes/fortunes.txt")));
            while (j < i) {
                result = reader.readLine();
                j++;
            }
            reader.close();
            return result;
        } catch (IOException ex) {
            Logger.getLogger(CorbaServant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public String printCookie() {
        Random generator = new Random();
        int i = generator.nextInt(16304);
        int j = 0;
        reader = new BufferedReader(new InputStreamReader(CorbaServant.class.getResourceAsStream("/fortunes/fortunes.txt")));

        while (j < i) {
            try {
                result = reader.readLine();
                j++;
            } catch (IOException ex) {
                Logger.getLogger(CorbaServant.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(CorbaServant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}