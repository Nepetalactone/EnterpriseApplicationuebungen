1. orbd mittels "start orbd -ORBInitialPort 1050" starten
2. Server mittels "java -jar CorbaServer.jar ORBInitialPort 1050 -ORBInitialhost localhost" starten
3. Client mittels "java -jar CorbaClient.jar ORBInitialPort 1050 -ORBInitialhost localhost <zufällige Zahl>" starten

fortunes.txt befindet sich in der CorbaServer.jar, es ist nicht nötig sie irgendwo anzugeben