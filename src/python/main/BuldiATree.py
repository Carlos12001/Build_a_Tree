import src.python.main.Connection.Server as srv
import src.python.main.Connection.Client as clnt


#server = srv.Server()
clnt = clnt.Socket("localhost", 1024)
clnt.sendM()
clnt.readM()

