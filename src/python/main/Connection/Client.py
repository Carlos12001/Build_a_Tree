# client.py

import socket


class Socket(object):

    def __init__(self, host, port):
        self.client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.serverAnswer = ""
        self.gameData = []
        # host = socket.socket()
        self.host = host
        self.port = port
        self.pos = ()
        self.life = 0
        self.time = 0

    def inicialSequence(self):
        self.presentation()


    def presentation(self): # Suponiendo que el servidor responde posición inicial, vida, tiempo

        self.sendM(".newPlayer")
        splittedAnswer = self.serverAnswer.split(".")
        answerLen = len(splittedAnswer)
        print('srvAnswer: ', splittedAnswer)
        self.setStats(splittedAnswer, answerLen)
        self.getStats()

    def setStats(self, splittedAnswer, answerLen):
        counter = 0
        while counter < answerLen:
            if(counter == 0):
                self.pos = splittedAnswer[counter]
            elif(counter == 1):
                self.life = splittedAnswer[counter]
            elif (counter == 2):
                self.time = splittedAnswer[counter]
            counter += 1;

    def getStats(self):
        pos = self.pos
        life = self.life
        time = self.time

        print('current Pos: ', pos, " ", 'current Life: ', life, " ", 'Time: ', time)



    def sendM(self, message):

        message = message

        self.client.connect((self.host, self.port))
        self.client.send(bytes(message,"utf-8"))
        from_server = self.client.recv(4096)
        self.serverAnswer = from_server.decode("utf-8")

        self.client.close()
        print('Server says: ', self.serverAnswer)

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

socket = Socket('0.0.0.0', 8080)

socket.inicialSequence()
