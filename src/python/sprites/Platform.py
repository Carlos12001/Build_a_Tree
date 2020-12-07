import pygame


class Platform(pygame.sprite.Sprite):
    """
    Description: This class will be the one in charge of the management of the platform sprites.
     It extends from pygame.sprite.Sprite

    """

    def __init__(self, screen: pygame.Surface, px: int, py: int, size: tuple):
        """
        Description: -- Constructor--
        And makes some actions as setting the image and position for the sprite.

        Attributes:
            screen: surface on which the sprite will be showed
            states_dict: dictionary with the states of the sprites
            current_state: attribute with an instance of entityState
            dx: position in the x coordinates of the sprite
            dy: position in the y coordinates of the sprite
            image: image of the surface that will be set later
            size: tuple with the width of the sprite and the height of the sprite

        """
        super(Platform, self).__init__()
        self.__screen: pygame.Surface = screen
        self.__states_dict: dict = {}
        from python.sprites.EntitySate import EntityState
        self.__current_state: EntityState = None
        self.__dx: int = 0
        self.__dy: int = 0
        self.image: pygame.Surface = None
        self.__size: tuple = size


        # Images
        from python.screen import SceneGame
        walking_image_right: pygame.Surface = SceneGame.SceneGame.load_out_img("platform.png", self.__size)

        from python.sprites.StaticState import StaticState
        self.__resting_right_state = StaticState(walking_image_right,
                                                 "resting_right")

        self.__states_dict[self.__resting_right_state.get_name()] = self.__resting_right_state

        self.set_current_state(self.__resting_right_state.get_name())

        # Set position
        self.image = self.__current_state.get_current_sprite()
        self.rect = self.image.get_rect()
        self.rect.x = px
        self.rect.y = py

    def set_current_state(self, key: int):
        self.__current_state = self.__states_dict[key]

    def get_rect_x(self) -> int:
        return self.rect.x

    def get_rect_y(self) -> int:
        return self.rect.y

    def get_width(self):
        return self.image.get_width()
