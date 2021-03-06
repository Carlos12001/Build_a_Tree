import pygame


class EntityState:
    """
    Description: this class manages all the information that is necesary to show and manage the sprite
    update in the interface.

    """

    def __init__(self):
        """
        Description: -- Constructor--

        Attributes:

            current_sprite: sets the sprite that is being showed at the moment
            name: string with the name of the sprite

        """
        self.__current_sprite: pygame.Surface = None
        self.__name: str = ""

    def get_name(self):
        return self.__name

    def get_current_sprite(self):
        return self.__current_sprite

    def set_name(self, name: str):
        self.__name = name

    def set_current_sprite(self, current_sprite: pygame.Surface):
        self.__current_sprite = current_sprite

    def get_width(self):
        return self.__current_sprite.get_width()

    def get_height(self):
        return self.__current_sprite.get_height()

    def update(self, dt=None):
        pass
