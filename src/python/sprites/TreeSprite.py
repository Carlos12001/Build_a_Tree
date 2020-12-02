import pygame

class TreeSprite(pygame.sprite.Sprite):

    def __init__(self, screen: pygame.Surface, id: str, px: int, py: int):
        self.__screen: pygame.Surface = screen


        self.__states_dict: dict = {}
        from python.sprites.EntitySate import EntityState
        self.__current_state: EntityState = None
        self.image: pygame.Surface = None

        self.__id: str = id
        # Setter images
        self.__set_dict_images()





        # Set position
        self.image = self.__current_state.get_current_sprite()
        self.rect = self.image.get_rect()
        self.rect.x = px
        self.rect.y = py


    def get_rect_x(self) -> int:
        return self.rect.x

    def get_id(self):
        return ""

    def get_rect_y(self) -> int:
        return self.rect.y
    def __set_dict_images(self):
        from python.screen.SceneGame import SceneGame
        from python.sprites.StaticState import StaticState
        tmp = self.__id
        if tmp == "treeB" and num != -1:


            image_0 = (SceneGame.load_out_img("treeB0.png", (50, 50)))

            self.__image_state_0 = StaticState(image_0, "0")

            image_1 = (SceneGame.load_out_img("treeB1.png", (50, 50)))
            self.__image_state_1  = StaticState(image_1, "1")


            self.__states_dict[self.__image_state_0.get_name()] = self.__image_state_0
            self.__states_dict[self.__image_state_1.get_name()] = self.__image_state_1
        elif tmp == "treeBST" and num != -1:
            pass
        elif tmp == "treeAVL" and num != -1:
            pass
        elif tmp == "treeSplay" and num != -1:
            pass

    def set_current_state(self, key: str):
        self.__current_state = self.__states_dict[key]

    def defeault(self):
        self.set_current_state("0")

    def update(self):
        self.__current_state.update(dt=None)
        self.image = self.__current_state.get_current_sprite()