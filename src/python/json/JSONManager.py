import json


class JSONManager(object):

    def __init__(self):
        self.info = None

    def selectAction(self, info):

        if (type(info) == str):
            self.info = info
            return self.decoder()
        elif (type(info) == dict):
            self.info = info
            return self.encoder()
        else:
            print("Error: The info's type is not valid")

    def decoder(self):
        print(type(self.info))
        dictElem = json.loads(self.info)
        print(type(dictElem))
        print('dict:', dictElem)
        return dictElem

    def encoder(self):
        print(type(self.info))
        jsonStr = json.dumps(self.info)
        print(type(jsonStr))
        print("str: ", jsonStr)
        return jsonStr


