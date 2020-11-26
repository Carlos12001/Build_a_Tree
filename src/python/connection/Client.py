# client.py

import socket


class Socket(object):

    def __init__(self):
        self.client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    def SendM(self, server_ip, server_port):
        s = server_ip
        self.client.connect((socket.gethostname(), server_port))
        self.client.send(b"Im a new client")

        from_server = self.client.recv(4096)
        from_server_srt = from_server.decode("utf-8")

        self.client.close()
        print('Server says: ', from_server_srt)

        # self.client.close()

    def ClientListens(self):
        self.client.bind((socket.gethostname(), 8080))
        self.client.listen(5)

        server_socket, add = self.client.accept()
        print(f"Connection form {add} has been established!")
        self.client.send(bytes("Welcome to Scatman's world", "utf-8"))


Socket().SendM("", 1024)
