import pygame

from python.sprites.EntitySate import EntityState


class AnimatedState(EntityState):

    def __init__(self, images, number_of_sprites, speed, name):
        super(AnimatedState, self).__init__()
        self.__images = images
        self.__number_of_sprites = number_of_sprites
        self.__speed = speed
        self.set_name(name)
        self.set_current_sprite(self.__images.subsurface(0, 0,
                                                         self.__images.get_width() / self.__number_of_sprites,
                                                         self.__images.get_height()))

        self.__width = self.__images.get_width() / self.__number_of_sprites
        self.__height = self.__images.get_height()

        self.__is_loop = True
        self.__current_delta = 0

    def update(self, dt: int):
        """

        :type dt: int
        """
        self.__current_delta = self.__current_delta + dt
        if self.__current_delta > self.__speed:
            if self.__is_loop:
                self.__current_delta = 0
            else:
                self.__current_delta = self.__current_delta - dt
        sprite_index = int(self.__current_delta * self.__number_of_sprites / self.__speed)

        if sprite_index > self.__number_of_sprites - 1:
            sprite_index = self.__number_of_sprites - 1

        self.set_current_sprite(self.__images.subsurface(sprite_index * self.__width, 0, self.__width, self.__height))
