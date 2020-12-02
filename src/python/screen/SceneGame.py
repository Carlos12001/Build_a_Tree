import os
import sys
from typing import Tuple

import pygame, random
from python.basicgui.Button import Button
from python.basicgui.LabelText import LabelText
from python.basicgui.CursorRect import CursorRect
from python.sprites.Platform import Platform
from python.sprites.Player import Player
from python.json.UpdateInfo import UpdateInfo as UI
from python.sprites.Token import Token


class SceneGame:
    """
   A class used to represent an Animal

   ...

   Attributes
   ----------
__scene_size_X : int
    Es una el tamaño en X de la ventana

__path_game: str

   Methods
   -------

   """

    __path_game: str = os.getcwd()[0:len(os.getcwd())]
    __colors: dict = {"black": (0, 0, 0),
                      "cyan": (14, 190, 187),
                      "red_light": (190, 74, 71),
                      "cyan_light": (178, 223, 219),
                      "brown": (141, 137, 128),
                      "white": (255, 255, 255)}

    def __init__(self) -> None:

        pygame.init()
        self.__scene_size_X: int = 1600
        self.__scene_size_Y: int = 1000
        self.__screen: pygame.Surface = pygame.display.set_mode((self.__scene_size_X, self.__scene_size_Y))

        self.__trees_group: pygame.sprite.Group = pygame.sprite.Group()
        self.__players_group: pygame.sprite.Group = pygame.sprite.Group()
        self.__platforms_group: pygame.sprite.Group = pygame.sprite.Group()
        self.__tokens_group: pygame.sprite.Group = pygame.sprite.Group()
        self.__powers_group: pygame.sprite.Group = pygame.sprite.Group()
        self.__all_sprite_group: pygame.sprite.Group = pygame.sprite.Group()

        self.__time_server: int
        self.__time_pygame: pygame.time.Clock = pygame.time.Clock()

        self.running: bool = True

        pygame.display.set_caption("Build a Tree")
        pygame.display.set_icon(SceneGame.load_out_img("iconGame.png"))

        self.__menu_view()

    def __menu_view(self) -> None:

        bg_image = SceneGame.load_out_img("backgroundMenu.png", (self.__scene_size_X, self.__scene_size_Y))
        cursorRect = CursorRect()

        label_text_title: LabelText = LabelText("Build a Tree", 0, SceneGame.get_color()["black"], self.__screen,
                                                (self.__scene_size_X/2, 50))

        active_text_field_1 = False
        text_field_1 = pygame.Rect(self.__scene_size_X/2-200, 150, 400, 50)
        label_text_indication_1 = LabelText("Player 1", 2, SceneGame.get_color()["black"], self.__screen, (self.__scene_size_X/2, 125))
        user_name_1 = ""
        label_text_text_field_1 = LabelText(user_name_1, 1, SceneGame.get_color()["white"], self.__screen,
                                            (text_field_1.x + 200, text_field_1.y + 25))

        active_text_field_2 = False
        text_field_2 = pygame.Rect(self.__scene_size_X/2-200, 250, 400, 50)
        label_text_indication_2 = LabelText("Player 2", 2, SceneGame.get_color()["black"], self.__screen, (self.__scene_size_X/2, 225))
        user_name_2 = ""
        label_text_text_field_2 = LabelText(user_name_2, 1, SceneGame.get_color()["white"], self.__screen,
                                            (text_field_2.x + 200, text_field_2.y + 25))

        active_text_field_3 = False
        text_field_3 = pygame.Rect(self.__scene_size_X/2-200, 350, 400, 50)
        label_text_indication_3 = LabelText("Player 3", 2, SceneGame.get_color()["black"], self.__screen, (self.__scene_size_X/2, 325))
        user_name_3 = ""
        label_text_text_field_3 = LabelText(user_name_3, 1, SceneGame.get_color()["white"], self.__screen,
                                            (text_field_3.x + 200, text_field_3.y + 25))

        active_text_field_4 = False
        text_field_4 = pygame.Rect(self.__scene_size_X/2-200, 450, 400, 50)
        label_text_indication_4 = LabelText("Player 4", 2, SceneGame.get_color()["black"], self.__screen, (self.__scene_size_X/2, 425))
        user_name_4 = ""
        label_text_text_field_4 = LabelText(user_name_4, 1, SceneGame.get_color()["white"], self.__screen,
                                            (text_field_4.x + 200, text_field_4.y + 25))

        button_start = Button(SceneGame.load_out_img("buttonStartNormal.png", (150, 100)),
                              SceneGame.load_out_img("buttonStartSelection.png", (150, 100)),
                              (self.__scene_size_X/2, 600), self.__screen)

        run: bool = True
        while run:
            self.__screen.blit(bg_image, [0, 0])
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    pygame.quit()
                    sys.exit()
                if event.type == pygame.MOUSEBUTTONDOWN:
                    if button_start.get_rect().collidepoint(event.pos):
                        active_text_field_1 = False
                        active_text_field_2 = False
                        active_text_field_3 = False
                        active_text_field_4 = False

                        validation = 0

                        if user_name_1 != "":
                            validation += 1

                        if user_name_2 != "":
                            validation += 1

                        if user_name_3 != "":
                            validation += 1

                        if user_name_4 != "":
                            validation += 1

                        if validation >= 2:
                            run = False
                            self.__transition_game([user_name_1, user_name_2, user_name_3, user_name_4])

                    elif text_field_1.collidepoint(event.pos):
                        active_text_field_1 = True
                        active_text_field_2 = False
                        active_text_field_3 = False
                        active_text_field_4 = False

                    elif text_field_2.collidepoint(event.pos):
                        active_text_field_2 = True
                        active_text_field_1 = False
                        active_text_field_3 = False
                        active_text_field_4 = False

                    elif text_field_3.collidepoint(event.pos):
                        active_text_field_3 = True
                        active_text_field_2 = False
                        active_text_field_1 = False
                        active_text_field_4 = False

                    elif text_field_4.collidepoint(event.pos):
                        active_text_field_4 = True
                        active_text_field_2 = False
                        active_text_field_3 = False
                        active_text_field_1 = False

                    else:
                        active_text_field_1 = False
                        active_text_field_2 = False
                        active_text_field_3 = False
                        active_text_field_4 = False
                elif event.type == pygame.KEYDOWN:
                    if active_text_field_1:
                        if event.key == pygame.K_BACKSPACE:
                            user_name_1 = user_name_1[0:-1]
                            label_text_text_field_1.set_text(user_name_1)
                        elif event.key == pygame.K_TAB:
                            user_name_1 = user_name_1
                            label_text_text_field_1.set_text(user_name_1)
                        else:
                            user_name_1 += event.unicode
                            label_text_text_field_1.set_text(user_name_1)

                    elif active_text_field_2:
                        if event.key == pygame.K_BACKSPACE:
                            user_name_2 = user_name_2[0:-1]
                            label_text_text_field_2.set_text(user_name_2)
                        elif event.key == pygame.K_TAB:
                            user_name_2 = user_name_2
                            label_text_text_field_2.set_text(user_name_2)
                        else:
                            user_name_2 += event.unicode
                            label_text_text_field_2.set_text(user_name_2)
                    elif active_text_field_3:
                        if event.key == pygame.K_BACKSPACE:
                            user_name_3 = user_name_3[0:-1]
                            label_text_text_field_3.set_text(user_name_3)
                        elif event.key == pygame.K_TAB:
                            user_name_3 = user_name_3
                            label_text_text_field_3.set_text(user_name_3)
                        else:
                            user_name_3 += event.unicode
                            label_text_text_field_3.set_text(user_name_3)
                    elif active_text_field_4:
                        if event.key == pygame.K_BACKSPACE:
                            user_name_4 = user_name_4[0:-1]
                            label_text_text_field_4.set_text(user_name_4)
                        elif event.key == pygame.K_TAB:
                            user_name_4 = user_name_4
                            label_text_text_field_4.set_text(user_name_4)
                        else:
                            user_name_4 += event.unicode
                            label_text_text_field_4.set_text(user_name_4)

            cursorRect.update()
            label_text_title.draw_me()
            button_start.update(cursorRect)

            pygame.draw.rect(self.__screen, SceneGame.get_color()["brown"], text_field_1)
            pygame.draw.rect(self.__screen, SceneGame.get_color()["brown"], text_field_2)
            pygame.draw.rect(self.__screen, SceneGame.get_color()["brown"], text_field_3)
            pygame.draw.rect(self.__screen, SceneGame.get_color()["brown"], text_field_4)

            if active_text_field_1:
                pygame.draw.rect(self.__screen, SceneGame.get_color()["cyan"], text_field_1)
            if active_text_field_2:
                pygame.draw.rect(self.__screen, SceneGame.get_color()["cyan"], text_field_2)

            if active_text_field_3:
                pygame.draw.rect(self.__screen, SceneGame.get_color()["cyan"], text_field_3)

            if active_text_field_4:
                pygame.draw.rect(self.__screen, SceneGame.get_color()["cyan"], text_field_4)

            label_text_indication_1.draw_me()
            label_text_text_field_1.draw_me()
            label_text_indication_2.draw_me()
            label_text_text_field_2.draw_me()
            label_text_indication_3.draw_me()
            label_text_text_field_3.draw_me()
            label_text_indication_4.draw_me()
            label_text_text_field_4.draw_me()

            pygame.display.flip()

    def __transition_game(self, names: list) -> None:

        j: int = 0
        for i in names:
            num = 50 * random.choice([-1, 1]) * j
            if i != "":
                player = Player(self.__screen, i, self.__scene_size_X / 2 + num, 0, j)
                self.__players_group.add(player)
                self.__all_sprite_group.add(player)
                j += 1

        platform = Platform(self.__screen, 50, 700)
        print()
        self.__platforms_group.add(platform)
        self.__all_sprite_group.add(platform)

        # crear  los vacíos arboles y meterlos en el grupo

        self.__game_view()

    def __game_view(self) -> None:
        bg_image = SceneGame.load_out_img("backgroundGame.png", (self.__scene_size_X, self.__scene_size_Y))
        info = UI()
        serverTime = "Tiempo: "
        label_time = LabelText(str(serverTime), 3, SceneGame.get_color()["black"], self.__screen, (120, 10))

        token = Token(self.__screen, "treeSplay@23", 50, 0)
        self.__tokens_group.add(token)
        self.__all_sprite_group.add(token)
        while self.running:
            self.__screen.blit(bg_image, [0, 0])
            label_time.set_text("Tiempo: " + str(self.__time_pygame.get_time()))
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    self.running = False
                    pygame.quit()
                    sys.exit()

                if event.type == pygame.KEYDOWN:

                    self.__key_down(event.key)

                if event.type == pygame.KEYUP:
                    self.__key_up(event.key)



            self.__all_sprite_group.draw(self.__screen)
            dt = self.__time_pygame.tick(60)
            self.__update_players(dt)
            label_time.draw_me()
            if self.__is_time_challenge():
                self.__update_tokens(dt)
            else:
                self.__tokens_group.empty()
                self.__add_point_to_player()


            self.__collisions()
            pygame.display.flip()

    def __key_down(self, event_key) -> None:
        for i in self.__players_group:
            i.key_down(event_key)

    def __key_up(self, event_key) -> None:
        for i in self.__players_group:
            i.key_up(event_key)

    def __collisions(self):
        collide_platform_PY = pygame.sprite.groupcollide(self.__players_group, self.__platforms_group, False, False)

        if collide_platform_PY != {}:
            crasher: Platform = list(collide_platform_PY.values())[0][0]
            victim: Player = list(collide_platform_PY.keys())[0]
            if victim.rect.y <= crasher.get_rect_y():
                victim.collision(floor=[crasher.get_rect_y(), crasher.get_rect_x()+50, crasher.get_rect_x() + crasher.get_width()-50])

        for i in self.__players_group:
            current = self.__players_group.copy()
            current.remove(i)
            collide_player_PY = pygame.sprite.spritecollide(i, current, False, False)
            if collide_player_PY != []:
                victim: Player = i
                if abs(victim.get_rect_x() -collide_player_PY[0].get_rect_x())==10:
                    if victim.get_rect_x()>collide_player_PY[0].get_rect_x():
                        victim.set_rect_x(75)
                        collide_player_PY[0].set_rect_x(-75)
                    else:
                        victim.set_rect_x(-75)
                        collide_player_PY[0].set_rect_x(75)

        collide_platform_TK =  pygame.sprite.groupcollide(self.__tokens_group, self.__platforms_group, False, False)

        if collide_platform_TK != {}:
            crasher: Platform = list(collide_platform_TK.values())[0][0]
            victim: Player = list(collide_platform_TK.keys())[0]
            if victim.rect.y <= crasher.get_rect_y():
                victim.collision(floor=[crasher.get_rect_y(), crasher.get_rect_x()+50, crasher.get_rect_x() + crasher.get_width()-50])



    def get_Y(self) -> int:
        return self.__scene_size_Y

    def get_X(self) -> int:
        return self.__scene_size_X

    def __update_players(self, dt) -> None:
        for i in self.__players_group:
            i.update(dt)

    def __is_time_challenge(self) -> bool:
        result = True
        # if (self.__trees_group==[]):
        #     for i in self.__update_info.get_challenges():
        #         self.__trees_group.add(i)
        #
        # for i in self.__update_info.get_challenges():
        #     if i=="":
        #         result = False
        #         break
        #     else:
        #         result = True
        #         if py.get_tree()=="":
        #             for py in self.__players_group:
        #                 py.set_tree(i)
        #         else:
        #             pass
        #         break
        # self.__trees_group.empty() #borra los sprites de los arboles
        return result


    def __update_tokens(self, dt):
        for i in self.__tokens_group:
            i.update(dt)

    def __add_point_to_player(self) -> None:
        # tree: TreeSprite
        # num_max_token_take_it = 0 # numero en la posicion mayor de sprites
        # for tree in self.__trees_group:
        #     num2 =  tree.get_position_image()
        #     #logica para saber cual es el mayor
        # #     tree = mayor position image
        #
        #
        # for py in self.__players_group:
        #     if py.get_tree()==tree.get_id():
        #         py.set_points(num_max_token_take_it*100)
        #
        # VOLVER RECETEAR
        pass


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
        image: pygame.Surface = pygame.image.load(
            os.path.join(SceneGame.__path_game + "\images", resource))
        if scale is None:
            return image
        else:
            return pygame.transform.scale(image, scale)
