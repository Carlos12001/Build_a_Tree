# client.py
import socket

client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

#host = socket.socket()
host = "0.0.0.0"
port = 8080

client.connect((host, port))
client.send(b"Im a new client")
from_server = client.recv(4096)
from_serverSrt = from_server.decode("utf-8")

client.close()
print('Server says: ', from_serverSrt)
