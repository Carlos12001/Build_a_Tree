#from python.json.JSONManager import JSONManager


class UpdateInfo(object):

    class __UpdateInfoInstance:
        __instance = None

        def __init__(self):
            # Data that's gonna be sent to the server
            self.playersName = []
            self.playersGameOver = []
            self.treeB = ""
            self.treeBST = ""
            self.treeAVL = ""
            self.treeSplay = ""

            # Data that's gonna be received from the server
            self.time = 0
            self.tokenSend = ""
            self.challenge = []

    def updateWrapper(self, playersName, playersGameOver, treeB, treeBST, treeAVL, treeSplay):
        self.setPlayersName(playersName)
        self.setPlayersGameOver(playersGameOver)
        self.setTreeB(treeB)
        self.setTreeBST(treeBST)
        self.setTreeAVL(treeAVL)
        self.setTreeSplay(treeSplay)
        dictionary = self.createDict()
        return dictionary

    def createDict(self):
        return {"playersName": self.playersName, "playersGameOver": self.playersGameOver, "treeB": self.treeB,
                "treeBST": self.treeBST, "treeAVL": self.treeAVL, "treeSplay": self.treeSplay}

    def unWrapper(self, dictio):
        self.setTime(dictio['time'])
        self.setTokenSend(dictio['tokenSend'])
        self.setChallenge(dictio['challenge'])
        print(self.time)
        print(self.tokenSend)
        print(self.challenge)

    def setPlayersName(self, playersName):
        self.playersName = playersName

    def getPlayersName(self):
        return self.playersName

    def setPlayersGameOver(self, playersGameOver):
        self.playersGameOver = playersGameOver

    def getPlayersGameOver(self):
        return self.playersGameOver

    def setTreeB(self, treeB):
        self.treeB = treeB

    def getTreeB(self):
        return self.treeB

    def setTreeBST(self, treeBST):
        self.treeBST = treeBST

    def getTreeBST(self):
        return self.treeBST

    def setTreeAVL(self, treeAVL):
        pass

    def getTreeAVL(self):
        return self.treeAVL

    def setTreeSplay(self, treeSplay):
        self.treeSplay = treeSplay

    def getTreeSplay(self):
        return self.treeSplay

    def setTime(self, time):
        self.time = time

    def getTime(self):
        return self.time

    def setTokenSend(self, tokenSend):
        self.tokenSend = tokenSend

    def getTokenSend(self):
        return self.tokenSend

    def setChallenge(self, challenge):
        self.challenge = challenge

    def getChallenge(self):
        return self.challenge

