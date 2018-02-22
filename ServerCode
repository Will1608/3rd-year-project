import socket
import thread

peers=list()
MacAddress=list()
ConnectedList=list()
AvailablePeers=list()
flag=0
HOST, PORT='',8080

listen_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM) #uses ipv4
listen_socket.setsockopt(socket.SOL_SOCKET,socket.SO_REUSEADDR,1)
listen_socket.bind((HOST, PORT))#Binds socket to localip and port 8080
listen_socket.listen(2)#accept 2 connection



print("Listening on port %s" %PORT)

while True:
	client_connection, client_address = listen_socket.accept()
	request=client_connection.recv(1024)#number of bytes accepted by server
	connectedIp,number=client_address#Isolating userIP from tuple client_address
	stringIp=str(connectedIp)
	if len(request)==17:
		if request not in MacAddress:
			MacAddress.append(request)
		if stringIp not in peers: #will only append peers when new peer is connected
			peers.append(stringIp) #adds new peer to the list
		print("Message from----> " +stringIp)#client address
		print("Message from client : "+request)#show message from client
			#print("Known peers: "+repr(peers))
		if len(peers) >= 2 and stringIp==peers[0]:		#if enough peers are connected will check which peer is currently 
			print("sending :" + MacAddress[1]+" to "+peers[0])#connected and send it the ip address of the second phone				
			print("")
			client_connection.send(MacAddress[1])
		elif len(peers) >= 2 and stringIp==peers[1]:	#if enough peers are connected will check which peer is currently 				e first phone
			print("sending :" + MacAddress[0]+" to "+peers[1])#connected and send it the ip address of the second phone	
			print("")
			client_connection.send(MacAddress[0])
		else:										
			print("Waiting for second peer.....")		#not enough peers are currently connected
			print("")
		client_connection.close()
	else:
		print("New peers")
		if stringIp not in ConnectedList:
			ConnectedList.append(stringIp)
		if request !="1":
			incomingMsg=request
			print("Received Message is: "+incomingMsg)
			print("Waiting for available peer")
		if len(ConnectedList) >= 2:
			print("Sending.....")
			client_connection.sendall(incomingMsg)
			print("Message sent to peer: "+incomingMsg)
			client_connection.close()
	



	
