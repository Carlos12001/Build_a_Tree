# client.py


import os
import socket

def sending():
    client_socket_send = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client_socket_send.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    hostname = socket.gethostname()
    server_ip = socket.gethostbyname(hostname)
    server_port = 8080

    connection = False
    while not connection:
        try:
            client_socket_send.connect((server_ip, server_port))
            connection = True
        except:
            print("Error: Connection Refused... trying again")

    console_input = str(input("Write a message: "))
    client_socket_send.send(bytes(console_input, 'utf-8'))

    client_socket_send.close()
    print("Mensaje Enviado")

    listening()

    # message_from_server = client_socket.recv(4098)
    # print(message_from_server.decode("futuros de temelfono", "utf-8"))


def listening():
    client_socket_listen = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client_socket_listen.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

    connected = False
    while not connected:
        try:
            client_socket_listen.bind((socket.gethostname(), 2030))
            connected = True
        except:
            print("INtentando reconectar")
            os.close(client_socket_listen.fileno())

    client_socket_listen.listen(5)
    print("EL cliente está escuchando...")

    server_conn, addr = client_socket_listen.accept()
    print(f"Connection to {addr} established")

    message = server_conn.recv(1401)
    str = message.decode("utf-8")
    print("Message says: " + str)

    server_conn.shutdown(socket.SHUT_RDWR)
    server_conn.close()
    client_socket_listen.shutdown(socket.SHUT_RDWR)
    client_socket_listen.detach()
    client_socket_listen.close()

    sending()


sending()

"""
class Socket(object):

    def __init__(self):
        self.client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    def SendM(self, server_ip, server_port):
        s = server_ip
        self.client.connect((socket.gethostname(), server_port))
        self.client.send(b"Im a new client")

        # from_server = self.client.recv(4096)
        # from_server_srt = from_server.decode("utf-8")

        self.client.close()
        # print('Server says: ', from_server_srt)

        # self.client.close()

    def ClientListens(self):
        self.client.bind((socket.gethostname(), 8080))
        self.client.listen(5)

        server_socket, add = self.client.accept()
        print(f"Connection form {add} has been established!")
        self.client.send(bytes("Welcome to Scatman's world", "utf-8"))


Socket().SendM("", 1024)
"""