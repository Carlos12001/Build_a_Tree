# client.py

import os
import socket

class SocketClientClass(object):

    def __init__(self):
        self.socket_client_send = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.socket_client_listen = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.hostname = socket.gethostname()
        self.server_ip = socket.gethostbyname(self.hostname)

    def sending(self, port):
        self.socket_client_send.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        server_port = port

        connection = False
        while not connection:
            try:
                self.socket_client_send.connect((self.server_ip, server_port))
                connection = True
            except:
                print("Error: Connection Refused... trying again")

        console_input = str(input("Write a message: "))
        self.socket_client_send.send(bytes(console_input, 'utf-8'))

        self.socket_client_send.close()
        print("Mensaje Enviado")

    def listening(self, port):
        self.socket_client_listen.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        hosting_port = port

        connected = False
        while not connected:
            try:
                self.socket_client_listen.bind((socket.gethostname(), hosting_port))
                connected = True
            except:
                print("Intentando reconectar...")
                os.close(self.socket_client_listen.fileno())

        self.socket_client_listen.listen(5)
        print("EL cliente está escuchando...")

        server_conn, addr = self.socket_client_listen.accept()
        print(f"Connection to {addr} established")

        message = server_conn.recv(1401)
        str = message.decode("utf-8")
        print("Message says: " + str)

        server_conn.shutdown(socket.SHUT_RDWR)
        server_conn.close()
        self.socket_client_listen.shutdown(socket.SHUT_RDWR)
        self.socket_client_listen.detach()
        self.socket_client_listen.close()



