from typing import Tuple

import pygame
from pygame.surface import Surface


class Button(pygame.sprite.Sprite):

    def __init__(self, image_normal: Surface, image_selection: Surface, position: Tuple[int], screen: pygame.Surface):
        self.__image_normal: Surface = image_normal
        self.__image_selection: Surface = image_selection
        self.__image_current: Tuple[int] = self.__image_normal
        self.__screen: pygame.Surface = screen
        self.__rect: pygame.Rect = self.__image_current.get_rect()
        self.__rect.center = position


    def update(self, cursor):
        if cursor.colliderect(self.__rect):
            self.__image_current = self.__image_selection
        else:
            self.__image_current = self.__image_normal

        self.__screen.blit(self.__image_current, self.__rect)


    def get_rect(self):
        return self.__rect
