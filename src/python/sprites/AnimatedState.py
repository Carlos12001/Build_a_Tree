import pygame

from python.sprites.EntitySate import EntityState


class AnimatedState(EntityState):
    """
    Description: This class will be the parent of the sprites clases, this will have all of the attributes
    related to sprite management, as amount of sprites, speed that the sprite will be updated, name and
    the image. It extends from EntityState

    """

    def __init__(self, images, number_of_sprites, speed, name):
        """
        Description: -- Constructor--

        Attributes:
            images: it contains the sprites that will be used
            number_of_sprites: number of sprites that the image has
            speed: speed to update the sprites
            name: name of the sprite
            current_prite: sets the sprite that is being showed at the moment
            width: width of the sprite
            height: height of the sprite
            is_loop boolean that indicates if it will show the first image when it gets to the last one.

        """
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

    def update(self, dt: int = None):
        """
        Description: updates the sprite using a time differential, and all the info on the attributes

        Parameters:
            dt: time differential

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
