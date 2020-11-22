from typing import Union, List, Tuple, Optional, Any

import pygame
from pygame.font import FontType
from pygame.ftfont import Font
pygame.font.init()


class Text:
    __fonts: List[Union[None, Font, FontType]] = [
        pygame.font.SysFont("Times New Roman", 70),
        pygame.font.SysFont("Times New Roman", 20),
        pygame.font.SysFont("Times New Roman", 30),
        pygame.font.SysFont("Times New Roman", 50)
    ]

    def __init__(self, text, type_font, color, surface, position):
        """
        :rtype: object
        """
        self.__text: str = text
        self.__type_font: int = type_font
        self.__color: Tuple[int] = color
        self.__surface: None = surface
        self.__position: List[int] = position
        self.txtObj: Optional[Any] = (Text.__fonts[self.__type_font]).render(self.__text, 1, self.__color)
        self.txtRect = self.txtObj.get_rect()
        self.txtRect.center = self.__position
        self.draw_surface()

    def draw_me(self):
        self.__surface.blit(self.txtObj, self.txtRect)
