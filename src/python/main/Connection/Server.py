# server.py

import socket


class Server(object):
    def __init__(self, host, port):
        self.s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        # host = socket.gethostname()
        self.host = host
        self.port = port
        self.s.bind((host, port))

        self.s.listen(5)

    def listenM(self):
        while True:
            print('Server is listening')
            # The return value is a pair (conn, address) where conn is a new socket object usable
            # to send and receive data on the connection, and address is the address bound to the
            # socket on the other end of the connection.
            conn, addr = self.s.accept()
            from_client = ''
            while True:
                data = conn.recv(4096)  # up to 4096 bytes received
                if not data: break
                dataStr = data.decode("utf-8")
                print('Got connection from', addr)
                print(addr, 'says: ', dataStr)
                answerFORClient = self.analizer(dataStr)
                conn.send(bytes(answerFORClient, 'utf-8'))
                """from_client += dataStr
                print('Got connection from', addr)
                print(addr, 'says: ', from_client)
                conn.send(b'Thank you for your connecting')"""
            conn.close()
            print('client disconnected')

    def analizer(self, clientsMessage):

        if (clientsMessage == ".newPlayer"):
            return "(100,150).100.0"


    def sendM(self):

        self.s.connect((self.host, 8080))
        self.s.send(b"Im a new client")
        from_server = self.s.recv(4096)
        from_serverSrt = from_server.decode("utf-8")

        self.s.close()
        print('Server says: ', from_serverSrt)

socketserver = Server('0.0.0.0',8080)

socketserver.listenM()
