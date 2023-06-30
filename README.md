# Ing-sw-2023 project My Shelfie

Group:

 - Biondo Silvia
 - Currò Davide
 - Di Salvo Claudio
 - Lombardo Matilde
 
 Function:
 - :white_check_mark:Cli
 - :white_check_mark:Socket
 - :white_check_mark:Multiple games
 - :x:GUI
 - :x:RMI
## Test
![Coverage](/Deliverables/Test/Coverage.jpg)

## How To

 1. Install [JDK19](https://www.oracle.com/java/technologies/downloads/) at least  	
 2. Run .jar with " `java -jar Progetto.jar [args]` " 	
 3. The args are: 
			 -	Server, if you want to start server client, this MUST be launched before client.
			 -	Client, if you want to play at the game, if you use this you MUST use the second args if the address is not localhost.


### Example
***Server***

    java -jar Progetto.jar Server
  ***Client***
	If the host is localhost
	
    java -jar Progetto.jar Client

if the host is not localhost

    java -jar Progetto.jar Client 192.168.1.78
