package corbacookie.Cookie;


/**
* corbacookie/Cookie/cookieserverPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Cookie.idl
* Samstag, 17. November 2012 17:18 Uhr MEZ
*/

public abstract class cookieserverPOA extends org.omg.PortableServer.Servant
 implements corbacookie.Cookie.cookieserverOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("printCookieNum", new java.lang.Integer (0));
    _methods.put ("printCookie", new java.lang.Integer (1));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // corbacookie/Cookie/cookieserver/printCookieNum
       {
         int i = in.read_long ();
         String $result = null;
         $result = this.printCookieNum (i);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 1:  // corbacookie/Cookie/cookieserver/printCookie
       {
         String $result = null;
         $result = this.printCookie ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:corbacookie/Cookie/cookieserver:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public cookieserver _this() 
  {
    return cookieserverHelper.narrow(
    super._this_object());
  }

  public cookieserver _this(org.omg.CORBA.ORB orb) 
  {
    return cookieserverHelper.narrow(
    super._this_object(orb));
  }


} // class cookieserverPOA
