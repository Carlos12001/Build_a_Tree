import python.connection.Client as cl

Client = cl.Socket('0.0.0.0', 8080)
Client.sendM('0.0.0.0', 1024)
