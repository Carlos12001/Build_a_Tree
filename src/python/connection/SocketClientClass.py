# client.py

import os

import threading
import socket
import time
import json

# The port used by the server

class SocketClientClass(object):

    def __init__(self, send_port, listen_port):
        self.listen_port = listen_port
        self.send_port = send_port
        self.ip = '192.168.100.11'
        self.data_received = ""

    def send(self):
        while (True):
            self.ip = '192.168.100.11'  # The server's hostname or IP address
            self.send_port = 9999
            s=socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            s.connect((self.ip, self.send_port))
            x = {
                "name": "John",
                "age": 30,
                "city": "New York"
            }
            y = json.dumps(x)

            s.sendall(bytes(y,encoding="utf-8"))
            print("Hola como estas")
            s.close()
            time.sleep(1)



    def listen(self):
        self.ip = '192.168.100.11'  # Standard loopback interface address (localhost)
        self.listen_port = 9998        # Port to listen on (non-privileged ports are > 1023)
        while True:
            with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
                s.bind((self.ip, self.listen_port))
                s.listen()
                conn, addr = s.accept()
                with conn:
                    print('Connected in server by', addr)
                    while True:
                        data = conn.recv(1024)
                        self.data_received = data
                        if not data:
                            break
                        print("This is the data ",data)

    def start_listen(self):
        t1 = threading.Thread(target=self.send)
        # starting thread 1
        t1.start()

    def start_sending(self):
        t2 = threading.Thread(target=self.listen)
        # starting thread 1
        t2.start()


new_client = Client(9999,9998)
new_client.start_listen()
new_client.start_sending()

