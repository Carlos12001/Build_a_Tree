# client.py
import socket

s = socket.socket()

#host = socket.socket()
host = "0.0.0.0"
port = 1234

s.connect((host, port))
print(s.recv(1024))
