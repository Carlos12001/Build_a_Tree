import python.connection.SocketClientClass as cl

if __name__ == "__main__":
    Client = cl.SocketClientClass(2050, 8050)
    Client.Client_ON()
