# client.py

import os

import threading
import socket
import time
import json
import python.connection.UpdateInfo as UP



# The port used by the server


class SocketClientClass(object):
    """
    Description: This class is the one that creates the socket client, it will receive information
    from the server, processes it and gives and answer

    """

    def __init__(self, send_port:int, listen_port:int):
        """
        Description: Constructor

        Parameters:
            send_port:this is the port in which the client will send info
            listen_port: this is the port in which the client will listen for info
            ip: the local ip
            data_received: string json with the data received
            name_list: String list with the names of the players
            info_managed: Dictionary with the info that has to be treated and updated
        """

        self.listen_port = listen_port
        self.send_port = send_port
        self.ip = '127.0.0.1'
        self.data_received = ""
        self.name_list = []
        self.info_managed = {}

    def send(self):
        """
        Description: Sends a message to the server on a json string format
        takes the port that is set on the class, and saves the message on the info_managed attribute

        """

        from BuldiATree import newInfo
        first_conn = True
        while True:
            self.ip = '127.0.0.1'  # The server's hostname or IP address
            self.send_port = 9999
            s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            s.connect((self.ip, self.send_port))

            if first_conn:
                self.info_managed.setPlayersName(self.name_list)
                self.info_managed.setPlayersGameOver([True, True, True, True])
                first_conn = False
                new_dict = self.info_managed.createDict()
            else:
                new_dict = newInfo.createDict()

            y = json.dumps(new_dict)

            s.sendall(bytes(y, encoding="utf-8"))
            s.close()
            time.sleep(1)

    def listen(self):
        """
        Description: Listens on the ip and port that are set on the attributes, it creates a socket with that
        information, when it receives a connection it decodes the message that comes on a jsonString format
        and saves it as a dictionary on the Update Info class

        """
        from BuldiATree import newInfo
        self.ip = '127.0.0.1'  # Standard loopback interface address (localhost)
        self.listen_port = 9998  # Port to listen on (non-privileged ports are > 1023)
        while True:
            with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
                s.bind((self.ip, self.listen_port))
                s.listen()
                conn, addr = s.accept()
                with conn:

                    while True:
                        data = conn.recv(1024)

                        self.data_received = data.decode("utf-8")

                        if not data:
                            break

                        newInfo.unWrapper(json.loads(self.data_received))


    def start_listen(self):
        """
        Description: It starts the thread that sends a message to the server.

        """

        t1 = threading.Thread(target=self.send)
        # starting thread 1
        t1.start()

    def start_sending(self):
        """
        Description: It starts the thread that listens on the information set on the attributes.

        """

        t2 = threading.Thread(target=self.listen)
        # starting thread 1
        t2.start()

