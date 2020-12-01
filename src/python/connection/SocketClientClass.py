# client.py

import os
import socket
import threading
import time


class SocketClientClass(object):

    def __init__(self, hosting_port, sending_port):
        self.s = threading.Thread.__init__(self)
        self.t = threading.Thread.__init__(self)
        self.socket_client_send = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.socket_client_listen = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.hostname = socket.gethostname()
        self.server_ip = socket.gethostbyname(self.hostname)
        self.hosting_port = hosting_port
        self.send_port = sending_port
        self.hosted = False
        self.connection = False

    def sending(self):
        # self.socket_client_send.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

        while not self.connection:
            try:
                self.socket_client_send.connect((self.server_ip, self.send_port))
                self.connection = True
            except:
                print("Error: Connection Refused... trying again")


        info_to_send = "Hello Server"

        self.socket_client_send.send(bytes(info_to_send, 'utf-8'))

        self.socket_client_send.shutdown(socket.SHUT_RDWR)
        self.socket_client_send.detach()

        print("Mensaje Enviado")
        self.socket_client_send.close()
        self.connection = False
        time.sleep(10)


        #time.sleep(10)
        #self.connection = False
        #self.socket_client_send.close()


    def listening(self):
        self.socket_client_listen.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

        while not self.hosted:
            try:
                self.socket_client_listen.bind((self.server_ip, self.hosting_port))
                self.hosted = True
            except:
                print("Intentando reconectar...")

        while self.hosted:
            self.socket_client_listen.listen(5)
            print("EL cliente está escuchando...")

            server_conn, addr = self.socket_client_listen.accept()
            print(f"Connection to {addr} established")

            message = server_conn.recv(1401)
            str_message = message.decode("utf-8")
            print("Message says: " + str_message)

            server_conn.shutdown(socket.SHUT_RDWR)
            server_conn.close()

            if str_message == "CLOSE_PATROL":
                self.hosted = False
            print("Rebooting Listening"
                  "...")

        self.socket_client_listen.shutdown(socket.SHUT_RDWR)
        self.socket_client_listen.detach()
        self.socket_client_listen.close()

    def start_listen(self):

        pass

    def hilo1(self):
        self.s = threading.Thread(target=self.listening)
        self.s.start()

    def hilo2(self):
        self.t = threading.Thread(target=self.sending())
        self.t.start()
        while not self.t.is_alive():
            print("its alive!")
            self.hilo2()
        #self.t.stop()

    def Client_ON(self):
        self.hilo1()
        self.hilo2()

