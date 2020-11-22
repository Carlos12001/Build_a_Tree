import sys
import os
from typing import Tuple
import pygame
from pygame.surface import Surface
import python.basicgui.Button
from python.basicgui.CursorRect import CursorRect
from python.basicgui.LabelText import Text


class SceneGame:
    """
   A class used to represent an Animal

   ...

   Attributes
   ----------
   __scene_size_X : int
       a formatted string to print out what the animal says

   Methods
   -------

   """
    # Static Attributes
    __path_game: str = os.getcwd()[0:len(os.getcwd()) - 5]
    __colors: dict = {"black": (0, 0, 0),
                                       "cyan": (14, 190, 187),
                                       "red_light": (190, 74, 71),
                                       "cyan_light": (178, 223, 219)}

    def __init__(self) -> None:
        """

        :rtype: None
        """
        pygame.init()
        self.__scene_size_X: int = 1000
        self.__scene_size_Y: int = 800
        self.__screen: pygame.Surface = pygame.display.set_mode((self.__scene_size_X, self.__scene_size_Y))
        pygame.display.set_caption("Build a Tree")
        pygame.display.set_icon(SceneGame.load_out_img("iconGame.png"))

        self.__menu_view()

    def __menu_view(self) -> None:

        bg_image = SceneGame.load_out_img("background.png", (self.__scene_size_X, self.__scene_size_Y))
        cursorRect = CursorRect()
        label_tittle: Text = Text("Build a Tree", 0, SceneGame.get_color()["black"], self.__screen, (500, 50))
        button_start = python.basicgui.Button.Button(SceneGame.load_out_img("buttonStartNormal.png", (150, 100)),
                                                     SceneGame.load_out_img("buttonStartSelection.png", (150, 100)),
                                                     (500, 600), self.__screen)
        run = True
        while run:
            self.__screen.blit(bg_image, [0, 0])

            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    pygame.quit()
                    sys.exit()
                if event.type == pygame.MOUSEBUTTONDOWN:
                    if button_start.get_rect().collidepoint(event.pos):
                        print("HI")
            cursorRect.update()
            label_tittle.draw_me()
            button_start.update(cursorRect)

            pygame.display.flip()

    @staticmethod
    def get_path_game() -> str:
        """
        :return: Return a string with the path game
        """
        return SceneGame.__path_game

    @staticmethod
    def get_color() -> dict:
        return SceneGame.__colors

    @staticmethod
    def load_out_img(resource: str, scale: Tuple[int] = None) -> pygame.Surface:
        """
        :rtype: Pygame image
        """
        image: pygame.Surface = pygame.image.load(os.path.join(SceneGame.__path_game + "\images", resource))
        if scale is None:
            return image
        else:
            return pygame.transform.scale(image, scale)
