import python.connection.SocketClientClass as cl

if __name__ == "__main__":
    Client = cl.SocketClientClass()
    Client.sending(8080)
    Client.listening(2030)
