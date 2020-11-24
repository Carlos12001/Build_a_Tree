import pygame

from python.screen.SceneGame import SceneGame
from python.sprites.AnimatedState import AnimatedState


class PLayer(pygame.sprite.Sprite):

    def __init__(self, screen):
        super(PLayer, self).__init__()
        self.__screen = screen
        self.__states_dict = {}
        self.__current_state = None
        self.__dx = 0
        self.__dy = 0
        self.__image = None
        self.__jumping = False

        self.__speed = 3.5
        self.__walking_image_right = SceneGame.load_out_img("player1Run.png")
        self.__number_of_sprites_walking = 8

        self.__walking_right_state = AnimatedState(self.__walking_image_right, self.__number_of_sprites_walking,
                                                   800, "walking_right")


        self.__states_dict[self.__walking_right_state.get_name()] = self.__walking_right_state


    def set_current_state(self, key):
        self.__current_state = self.__states_dict[key]

    def update(self):
        pass

    def impulse(self, dx, dy):
        self.__dx = dx
        self.__dy = dy
