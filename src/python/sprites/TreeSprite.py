import pygame

class TreeSprite(pygame.sprite.Sprite):
    """
    Description: This class manages the tree creation, it updates the images
    as the player takes tokes or fails and take the wrong ones, where
    to place them and takes into account when it is completed

    """

    def __init__(self, screen: pygame.Surface, id: str, px: int, py: int):
        """
        Description: -- Constructor--

        Attributes:
            screen: surface on which the sprite will be showed
            states_dict: dictionary with the states of the sprites
            current_state: attribute with an instance of entityState
            image: image of the surface that will be set later
            set_dict_images: sets the images in the dictionary following the method params
            rectx : px or position in x axis
            recty: py or position in y axis

        """
        super(TreeSprite, self).__init__()
        self.__screen: pygame.Surface = screen

        self.__states_dict: dict = {}
        from python.sprites.EntitySate import EntityState
        self.__current_state: EntityState = None
        self.image: pygame.Surface = None

        self.__id: str = id
        self.__current_key: str = "0"
        # Setter images
        self.__set_dict_images()

        # Set position
        self.image = self.__current_state.get_current_sprite()
        self.rect = self.image.get_rect()
        self.rect.x = px
        self.rect.y = py


    def get_rect_x(self) -> int:
        """
        Description: Gets the x axis bound of the rect that "contains" the power up

        Returns: int
        """
        return self.rect.x

    def get_rect_y(self) -> int:
        """
        Description: Gets the y axis bound of the rect that "contains" the power up

        Returns: int
        """
        return self.rect.y

    def get_id(self) -> str:
        """
        Description: Gets the tipe of the tree to check if is the same type as
        the token

        """
        return self.__id

    def get_current_key(self) -> str:
        """
        Description: Gets the key of the token that the tree will be updating
        if he grabs a token from his tree type

        """
        return self.__current_key

    def __set_dict_images(self):
        """
        Description: This method selects the image, its size and where is it going to be showed, depending
        on which string value receives and saves it on the states dict attribute.

        """
        from python.screen.SceneGame import SceneGame
        from python.sprites.StaticState import StaticState

        tmp = self.__id

        if tmp == "treeB":

            image_0 = (SceneGame.load_out_img("treeB@0.png", None))
            self.__image_state_0 = StaticState(image_0, "0")

            image_1 = (SceneGame.load_out_img("treeB@1.png", None))
            self.__image_state_1  = StaticState(image_1, "1")

            image_2 = (SceneGame.load_out_img("treeB@2.png", None))
            self.__image_state_2 = StaticState(image_2, "2")

            image_3 = (SceneGame.load_out_img("treeB@3.png", None))
            self.__image_state_3  = StaticState(image_3, "3")

            image_4 = (SceneGame.load_out_img("treeB@4.png", None))
            self.__image_state_4 = StaticState(image_4, "4")

            image_5 = (SceneGame.load_out_img("treeB@5.png", None))
            self.__image_state_5  = StaticState(image_5, "5")

            # add images to
            self.__states_dict[self.__image_state_0.get_name()] = self.__image_state_0
            self.__states_dict[self.__image_state_1.get_name()] = self.__image_state_1
            self.__states_dict[self.__image_state_2.get_name()] = self.__image_state_2
            self.__states_dict[self.__image_state_3.get_name()] = self.__image_state_3
            self.__states_dict[self.__image_state_4.get_name()] = self.__image_state_4
            self.__states_dict[self.__image_state_5.get_name()] = self.__image_state_5

        elif tmp == "treeBST":

            image_0 = (SceneGame.load_out_img("treeBST@0.png", None))
            self.__image_state_0 = StaticState(image_0, "0")

            image_1 = (SceneGame.load_out_img("treeBST@1.png", None))
            self.__image_state_1  = StaticState(image_1, "1")

            image_2 = (SceneGame.load_out_img("treeBST@2.png", None))
            self.__image_state_2 = StaticState(image_2, "2")

            image_3 = (SceneGame.load_out_img("treeBST@3.png", None))
            self.__image_state_3  = StaticState(image_3, "3")

            image_4 = (SceneGame.load_out_img("treeBST@4.png", None))
            self.__image_state_4 = StaticState(image_4, "4")

            image_5 = (SceneGame.load_out_img("treeBST@5.png", None))
            self.__image_state_5  = StaticState(image_5, "5")

            # add images to
            self.__states_dict[self.__image_state_0.get_name()] = self.__image_state_0
            self.__states_dict[self.__image_state_1.get_name()] = self.__image_state_1
            self.__states_dict[self.__image_state_2.get_name()] = self.__image_state_2
            self.__states_dict[self.__image_state_3.get_name()] = self.__image_state_3
            self.__states_dict[self.__image_state_4.get_name()] = self.__image_state_4
            self.__states_dict[self.__image_state_5.get_name()] = self.__image_state_5

        elif tmp == "treeAVL":

            image_0 = (SceneGame.load_out_img("treeAVL@0.png", None))
            self.__image_state_0 = StaticState(image_0, "0")

            image_1 = (SceneGame.load_out_img("treeAVL@1.png", None))
            self.__image_state_1  = StaticState(image_1, "1")

            image_2 = (SceneGame.load_out_img("treeAVL@2.png", None))
            self.__image_state_2 = StaticState(image_2, "2")

            image_3 = (SceneGame.load_out_img("treeAVL@3.png", None))
            self.__image_state_3  = StaticState(image_3, "3")

            image_4 = (SceneGame.load_out_img("treeAVL@4.png", None))
            self.__image_state_4 = StaticState(image_4, "4")

            image_5 = (SceneGame.load_out_img("treeAVL@5.png", None))
            self.__image_state_5  = StaticState(image_5, "5")

            # add images to
            self.__states_dict[self.__image_state_0.get_name()] = self.__image_state_0
            self.__states_dict[self.__image_state_1.get_name()] = self.__image_state_1
            self.__states_dict[self.__image_state_2.get_name()] = self.__image_state_2
            self.__states_dict[self.__image_state_3.get_name()] = self.__image_state_3
            self.__states_dict[self.__image_state_4.get_name()] = self.__image_state_4
            self.__states_dict[self.__image_state_5.get_name()] = self.__image_state_5

        elif tmp == "treeSplay" :

            image_0 = (SceneGame.load_out_img("treeSplay@0.png", None))
            self.__image_state_0 = StaticState(image_0, "0")

            image_1 = (SceneGame.load_out_img("treeSplay@1.png", None))
            self.__image_state_1  = StaticState(image_1, "1")

            image_2 = (SceneGame.load_out_img("treeSplay@2.png", None))
            self.__image_state_2 = StaticState(image_2, "2")

            image_3 = (SceneGame.load_out_img("treeSplay@3.png", None))
            self.__image_state_3  = StaticState(image_3, "3")

            image_4 = (SceneGame.load_out_img("treeSplay@4.png", None))
            self.__image_state_4 = StaticState(image_4, "4")

            image_5 = (SceneGame.load_out_img("treeSplay@5.png", None))
            self.__image_state_5  = StaticState(image_5, "5")

            # add images to
            self.__states_dict[self.__image_state_0.get_name()] = self.__image_state_0
            self.__states_dict[self.__image_state_1.get_name()] = self.__image_state_1
            self.__states_dict[self.__image_state_2.get_name()] = self.__image_state_2
            self.__states_dict[self.__image_state_3.get_name()] = self.__image_state_3
            self.__states_dict[self.__image_state_4.get_name()] = self.__image_state_4
            self.__states_dict[self.__image_state_5.get_name()] = self.__image_state_5

        self.set_current_state("0")

    def set_current_state(self, key: str):
        """
        Description: This method receives an integer that sets which state the power up is on taking it
        from the states dictionary, and placing the value as the current state.

        """
        self.__current_state = self.__states_dict[key]

    def set_next(self):
        """
        Description: Sets which is the next token that should be grabed by the player
        using the current key

        """
        tmp = int(self.__current_key) + 1
        if tmp < 6:
            self.__current_state = self.__states_dict[str(tmp)]
            self.__current_key = str(tmp)

    def default(self):
        """
        Description: It sets the self.current key and state to 0 menaing that
        the tree has 0 tokens in it.

        """
        self.set_current_state("0")
        self.__current_key = "0"

    def update(self, dt):
        """
        Description: It calculates the gravity value and updates the current position of the player
        based on the time differential parameter.

        Parameter:
            dt: time diferential

        """
        self.__current_state.update(dt=None)
        self.image = self.__current_state.get_current_sprite()

    def is_completed(self):
        """
        Description: It sets the self.current key to 5 that is the maximum
        amount of tokens that a tree has. So it means that the tree is completed

        """
        return self.__current_key == "5"
