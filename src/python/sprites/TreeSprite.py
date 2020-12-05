import pygame

class TreeSprite(pygame.sprite.Sprite):

    def __init__(self, screen: pygame.Surface, id: str, px: int, py: int):
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
        return self.rect.x

    def get_rect_y(self) -> int:
        return self.rect.y

    def get_id(self) -> str:
        return self.__id

    def get_current_key(self) -> str:
        return self.__current_key

    def __set_dict_images(self):
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
        self.__current_state = self.__states_dict[key]

    def set_next(self):
        tmp = int(self.__current_key) + 1
        if tmp < 6:
            self.__current_state = self.__states_dict[str(tmp)]
            self.__current_key = str(tmp)

    def default(self):
        self.set_current_state("0")
        self.__current_key = "0"

    def update(self, dt):
        self.__current_state.update(dt=None)
        self.image = self.__current_state.get_current_sprite()

    def is_completed(self):
        return self.__current_key == "5"
