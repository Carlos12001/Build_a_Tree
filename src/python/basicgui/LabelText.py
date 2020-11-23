from typing import Union, List, Tuple, Optional, Any

import pygame
from pygame.font import FontType
from pygame.ftfont import Font
pygame.font.init()


class LabelText(pygame.sprite.Sprite):
    __fonts: List[Union[None, Font, FontType]] = [
        pygame.font.SysFont("Times New Roman", 70),
        pygame.font.SysFont("Times New Roman", 20),
        pygame.font.SysFont("Times New Roman", 30),
        pygame.font.SysFont("Times New Roman", 50)
    ]

    def __init__(self, text: str, type_font: int, color: Tuple[int], surface: pygame.Surface, position: Tuple[int]):
        """
        :rtype: object
        """
        self.__text: str = text
        self.__type_font: int = type_font
        self.__color: Tuple[int] = color
        self.__surface: pygame.Surface = surface
        self.__position: List[int] = position
        self.__txtObj: Optional[Any] = (LabelText.__fonts[self.__type_font]).render(self.__text, 1, self.__color)
        self.__txtRect = self.__txtObj.get_rect()
        self.__txtRect.center = self.__position
        self.draw_me()

    def draw_me(self):
        self.__surface.blit(self.__txtObj, self.__txtRect)

    def set_text(self, text: str):
        self.__text: str = text
        self.__txtObj: Optional[Any] = (LabelText.__fonts[self.__type_font]).render(self.__text, 1, self.__color)
        self.__txtRect = self.__txtObj.get_rect()
        self.__txtRect.center = self.__position

    def get_rect(self):
        return self.__txtRect
