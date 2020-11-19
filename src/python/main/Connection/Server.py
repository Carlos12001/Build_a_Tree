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
                from_client += dataStr
                print('Got connection from', addr)
                print(addr, 'says: ', from_client)
                conn.send(b'Thank you for your connecting')
            conn.close()
            print('client disconnected')

# socketserver = Server('0.0.0.0',8080)

# socketserver.listenM()
