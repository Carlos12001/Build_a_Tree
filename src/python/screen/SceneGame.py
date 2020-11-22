import sys
import os

import pygame
from src.python.basicgui.Text import Text


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
    __colors: dict = {"black": (0, 0, 0), "cyan": (14, 190, 187), "red_light": (190, 74, 71)}

    def __init__(self):
        """

        :rtype: object
        """
        pygame.init()
        self.__scene_size_X: int = 1000
        self.__scene_size_Y: int = 800
        self.__screen: None = pygame.display.set_mode((self.__scene_size_X, self.__scene_size_Y))
        pygame.display.set_caption("Build a Tree")
        pygame.display.set_icon(
            pygame.image.load(os.path.join(SceneGame.__path_game + "\images", "iconGame.png")).convert())

        self.__menu_view()

    def __menu_view(self):

        label_tittle: Text = Text("Build a Tree", 2, SceneGame.get_color()["black"], self.__screen, (200, 400))

        run = True
        while run:
            self.__screen.fill(SceneGame.get_color()["cyan"])
            label_tittle.draw_me()

            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    pygame.quit()
                    sys.exit()
                if event.type == pygame.MOUSEBUTTONDOWN:
                    pass

            pygame.display.flip()

    @staticmethod
    def get_path_game():
        """
        :return: Return a string with the path game
        """
        return SceneGame.__path_game

    @staticmethod
    def get_color():
        return SceneGame.__colors
