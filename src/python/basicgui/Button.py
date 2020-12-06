from typing import Tuple
import pygame
from pygame.surface import Surface
from python.basicgui.CursorRect import CursorRect


class Button(pygame.sprite.Sprite):
    """
    Description:
    This  class creates a button using an image, so the user interface gets more suitable for the user
    Attributes: None
    """

    def __init__(self, image_normal: Surface, image_selection: Surface, position: Tuple[int], screen: pygame.Surface):
        """
        Description: Constructor
        Sets the variables of the class at its initial state

        Parameters:
            image_normal: its set as a surface
            image_selection: its set as another surface
            position: its a tuple that with coordinates of its initial position
            screen: the screen on that it will be showed on
        """
        self.__image_normal: Surface = image_normal
        self.__image_selection: Surface = image_selection
        self.__image_current: Tuple[int] = self.__image_normal
        self.__screen: pygame.Surface = screen
        self.__rect: pygame.Rect = self.__image_current.get_rect()
        self.__rect.center = position

    def update(self, cursor: CursorRect):
        """
        Description: Updates the image of the button if the cursor is on it or collides with it.

        Parameters:
            cursor: is the position of the cursor

        """
        if cursor.colliderect(self.__rect):
            self.__image_current = self.__image_selection
        else:
            self.__image_current = self.__image_normal

        self.__screen.blit(self.__image_current, self.__rect)

    def get_rect(self):
        return self.__rect
