# from python.json.JSONManager import JSONManager


class UpdateInfo(object):
    """
    Description:
    This class is the one that saves the information that comes from the server on a dictionary format.
    And the info is taken from this class to send it back to the server.

    """

    def __init__(self):
        """
        Description: --Constructor--

        Attributes:
            playersName: It's a list with the names of the players
            playersGameOver: List that contains booleans that indicate which players are still in the game
            treeB: If the player that has the B tree assinged takes a token, it will be saved here.
            treeBST: If the player that has the BST tree assinged takes a token, it will be saved here.
            treeAVL: If the player that has the AVL tree assinged takes a token, it will be saved here.
            treeSplay: If the player that has the Splay tree assinged takes a token, it will be saved here.
            time: Its information that comes from the server, the time passed since the game started
            tokenSend: Its information that comes from the server, the token that comes from the server
            challenge: Its information that comes from the server, list of challenges for each alive player
        """
        # Data that's gonna be sent to the server
        self.playersName = []
        self.playersGameOver = []
        self.treeB = ""
        self.treeBST = ""
        self.treeAVL = ""
        self.treeSplay = ""

        # Data that's gonna be received from the server
        self.time = ""
        self.tokenSend = ""
        self.challenge = ["", "", "", ""]

    def updateWrapper(self, playersName, playersGameOver, treeB, treeBST, treeAVL, treeSplay):
        """
        Description: THis method converts the attributes of the class into dictionary. Creates the create dict
        method to update all of the attributes

        Parameters:
            playersName
            playersGameOver
            treeB
            treeBST
            treeAVL
            treeSplay

        Returns:
            dictionary: A dictionary with the parameters and its information
        """
        self.setPlayersName(playersName)
        self.setPlayersGameOver(playersGameOver)
        self.setTreeB(treeB)
        self.setTreeBST(treeBST)
        self.setTreeAVL(treeAVL)
        self.setTreeSplay(treeSplay)
        dictionary = self.createDict()
        return dictionary

    def UpdateFile(self, newInfo):
        """
        Description: This method updates the attributes that come from the server

        Parameters:
            newInfo : the decoded info that comes from the server


        """
        self.time = newInfo.getTime()
        self.tokenSend = newInfo.getTokenSend()
        self.challenge = newInfo.getChallenge()

    def createDict(self):
        """
        Description: This method thakes all of the server attributes and puts them in a dictionary

        Returns:
            A dictionary that will work as an object


        """
        return {"playersName": self.playersName, "playersGameOver": self.playersGameOver, "treeB": self.treeB,
                "treeBST": self.treeBST, "treeAVL": self.treeAVL, "treeSplay": self.treeSplay, "time": self.time}

    def unWrapper(self, dictio):
        """
        Description: This method takes the info that comes in the dictionary that the server sent.
        and updates the info in it's respective attribute in this class

        Parameters:
           dictio : Dictionary set by the server

        """
        self.setTime(dictio['time'])
        self.setTokenSend(dictio['tokenSend'])
        self.setChallenge(dictio['challenge'])

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
        self.treeAVL = treeAVL

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
