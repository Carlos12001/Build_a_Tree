import python.connection.SocketClientClass as cl

if __name__ == "__main__":
    Client = cl.SocketClientClass(2030, 8080)
    Client.client_on()
    Client.start_listen()