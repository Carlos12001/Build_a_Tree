import python.connection.SocketClientClass as cl
import python.json.UpdateInfo as Updateinf

if __name__ == "__main__":
    Client = cl.SocketClientClass(9999, 9998)
    info = Updateinf.UpdateInfo()
    Client.info_managed = info
    Client.start_listen()
    Client.start_sending()


