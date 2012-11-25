1. Glassfish Server starten, unter Connection Factorys eine TopicConnectionFactory mit Pool- und JNDI-Name 
topic/ConnectionFactory, und unter Zielressourcen ein javax.jms.Topic mit JNDI-Name topic/topic0 erstellen
2. Subscriber.jar in der Konsole per "<Pfad von appclient im Glassfishverzeichnis> -client Subscriber.jar" starten
3. Subscriber.jar wieder beenden
4. Publisher.jar in der Konsole per "<Pfad von appclient im Glassfishverzeichnis> -client Publisher.jar 
<Nachricht>" starten
5. Publisher.jar beenden
6. Subscriber.jar wieder starten -> Subscriber empfängt Nachricht die während seiner Abwesenheit gesendet wurde