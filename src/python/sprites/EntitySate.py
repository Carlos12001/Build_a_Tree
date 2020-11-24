import pygame


class EntityState:

    def __init__(self):
        self.__current_sprite: None = None
        self.__name: str = ""

    def get_name(self):
        return self.__name

    def get_current_sprite(self):
        return self.__current_sprite

    def set_name(self, name: str):
        self.__name = name

    def set_current_sprite(self, current_sprite):
        self.__current_sprite = current_sprite

    def get_weight(self):
        return self.__current_sprite.get_weight()

    def get_height(self):
        return self.__current_sprite.get_height()

    def update(self):
        pass
