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
    StringBuffer buffer = null;

    public void setORB(ORB orb_val) {
        orb = orb_val;
    }

    @Override
    public String printCookieNum(int i) {
        BufferedReader reader = null;
        String result = null;
        buffer = new StringBuffer();
        try {
            int j = 0;
            reader = new BufferedReader(new InputStreamReader(CorbaServant.class.getResourceAsStream("/fortunes/fortunes.txt")));
            result = reader.readLine();
            while (j < i) {
                result = reader.readLine();
                if (result.equalsIgnoreCase("%")) {
                    j++;
                }
            }
            result = reader.readLine();
            while (!result.equalsIgnoreCase("%")) {
                buffer.append(result);
                buffer.append("\n");
                j++;
                result = reader.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(CorbaServant.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(CorbaServant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return buffer.toString();
    }

    @Override
    public String printCookie() {
        Random generator = new Random();
        int i = generator.nextInt(3583);
        int j = 0;
        buffer = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(CorbaServant.class.getResourceAsStream("/fortunes/fortunes.txt")));
            result = reader.readLine();
            while (j < i) {
                result = reader.readLine();
                if (result.equalsIgnoreCase("%")) {
                    j++;
                }
            }
            result = reader.readLine();
            while (!result.equalsIgnoreCase("%")) {
                buffer.append(result);
                buffer.append("\n");
                j++;
                result = reader.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(CorbaServant.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(CorbaServant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return buffer.toString();
    }
}