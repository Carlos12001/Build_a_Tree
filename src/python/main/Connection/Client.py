# client.py

import socket


class Socket(object):

    def __init__(self, host, port):
        self.client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

        # host = socket.socket()
        self.host = host
        self.port = port

    def sendM(self):
        self.client.connect((self.host, self.port))
        self.client.send(b"Im a new client")

        self.client.close()

    def readM(self):
        #self.client.connect((self.host, self.port))
        from_server = self.client.recv(4096)
        from_serverSrt = from_server.decode("utf-8")
        print('Server says: ', from_serverSrt)
