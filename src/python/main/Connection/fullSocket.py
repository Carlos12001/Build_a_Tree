import src.python.main.Connection.Server as server


class fullSocket(object):

    def __init__(self, ip, host):
        self.socket = server.Server(ip, host)

    def listen(self):
        self.socket.listenM()

    def send(self):
        self.socket.sendM()

#test = fullSocket('0.0.0.0', 8080)
test1 = fullSocket('0.0.0.0', 6070)

test1.send()
test1.send()
test1.send()
test1.send()

