# derby-network-server

Start derby network server (persistent) using spring boot executable jar.

Create executable jar using below command

clean install spring-boot:repackage

and launch derby network server using below command

java -jar target/derby-network-server-0.0.1-SNAPSHOT.jar 192.168.1.4

You can use any database client tool to access this derby server.  Username and password are kept blank. This is a persistent store
and data saved in one runtime is available in next restart of the server.  Please ensure that you use the same connection
string each time you connect to the server (especially the db path)

jdbc:derby://192.168.1.4:1527//home/ubuntu/derbydb;create=true
