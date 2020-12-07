import os
import sys
from typing import Tuple

import pygame, random
from python.basicgui.Button import Button
from python.basicgui.LabelText import LabelText
from python.basicgui.CursorRect import CursorRect
from python.sprites.Platform import Platform
from python.sprites.Player import Player
# import python.connection.UpdateInfo as UI
from python.sprites.Power import Power
from python.sprites.Token import Token
from python.sprites.TreeSprite import TreeSprite


# import python.connection.SocketClientClass as cl


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
                      "white": (255, 255, 255),
                      "red": (255,39,42)}

    def __init__(self) -> None:

        """
        Description: --Constructor--

        Attributes:
            scene_size_X: It sets the size of the scene on an X coordinate, width.
            scene_size_Y: It sets the size of the scene on an X coordinate, height.
            screen: it creates anew suface to work and the size is the one set on the previous attributes

            trees_group: This is an attribute that loads the tree sprites
            players_group: This is an attribute that loads the players sprites
            platforms_group: This is an attribute that loads the paltforms sprites
            tokens_group: This is an attribute that loads the tokens sprites
            powers_group: This is an attribute that loads the powers sprites
            all_sprite_group: This is an attribute that loads the all type sprites

            time_server: This attribute will be uptdate with the time that comes from the server
            time_pygame: pygame.time.Clock = pygame.time.Clock()

            running: This attribute determines if the game is running

            UI = this is the instance of the info that is sent form the server

            last_token_B: this attribute saves the last B tree token that came from the server till a player grabs it
            last_token_BST: this attribute saves the last BST tree token that came from the server till a player grabs it
            last_token_AVL: this attribute saves the last AVL tree token that came from the server till a player grabs it
            last_token_Splay: this attribute saves the last Splay tree token that came from the server till a player grabs it

        """

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

        self.__time_server: int = 0
        self.__time_pygame: pygame.time.Clock = pygame.time.Clock()

        self.running: bool = True

        from BuldiATree import newInfo

        self.UI = newInfo

        self.__last_token_B: str = ""
        self.__last_token_BST: str = ""
        self.__last_token_AVL: str = ""
        self.__last_token_Splay: str = ""

        pygame.display.set_caption("Build a Tree")
        pygame.display.set_icon(SceneGame.load_out_img("iconGame.png"))

        self.__menu_view()

    def __menu_view(self) -> None:

        bg_image = SceneGame.load_out_img("backgroundMenu.png", (self.__scene_size_X, self.__scene_size_Y))
        cursorRect = CursorRect()

        label_text_title: LabelText = LabelText("Build a Tree", 0, SceneGame.get_color()["black"], self.__screen,
                                                (self.__scene_size_X / 2, 50))

        active_text_field_1 = False
        text_field_1 = pygame.Rect(self.__scene_size_X / 2 - 200, 150, 400, 50)
        label_text_indication_1 = LabelText("Player 1", 2, SceneGame.get_color()["black"], self.__screen,
                                            (self.__scene_size_X / 2, 125))
        user_name_1 = ""
        label_text_text_field_1 = LabelText(user_name_1, 1, SceneGame.get_color()["white"], self.__screen,
                                            (text_field_1.x + 200, text_field_1.y + 25))

        active_text_field_2 = False
        text_field_2 = pygame.Rect(self.__scene_size_X / 2 - 200, 250, 400, 50)
        label_text_indication_2 = LabelText("Player 2", 2, SceneGame.get_color()["black"], self.__screen,
                                            (self.__scene_size_X / 2, 225))
        user_name_2 = ""
        label_text_text_field_2 = LabelText(user_name_2, 1, SceneGame.get_color()["white"], self.__screen,
                                            (text_field_2.x + 200, text_field_2.y + 25))

        active_text_field_3 = False
        text_field_3 = pygame.Rect(self.__scene_size_X / 2 - 200, 350, 400, 50)
        label_text_indication_3 = LabelText("Player 3", 2, SceneGame.get_color()["black"], self.__screen,
                                            (self.__scene_size_X / 2, 325))
        user_name_3 = ""
        label_text_text_field_3 = LabelText(user_name_3, 1, SceneGame.get_color()["white"], self.__screen,
                                            (text_field_3.x + 200, text_field_3.y + 25))

        active_text_field_4 = False
        text_field_4 = pygame.Rect(self.__scene_size_X / 2 - 200, 450, 400, 50)
        label_text_indication_4 = LabelText("Player 4", 2, SceneGame.get_color()["black"], self.__screen,
                                            (self.__scene_size_X / 2, 425))
        user_name_4 = ""
        label_text_text_field_4 = LabelText(user_name_4, 1, SceneGame.get_color()["white"], self.__screen,
                                            (text_field_4.x + 200, text_field_4.y + 25))

        button_start = Button(SceneGame.load_out_img("buttonStartNormal.png", (150, 100)),
                              SceneGame.load_out_img("buttonStartSelection.png", (150, 100)),
                              (self.__scene_size_X / 2, 600), self.__screen)

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
        """
        Description:
            This method is transition from the main menu to the start of the game

        Parameters:
           names : list with the names of the players that will be sumoned

        """

        from BuldiATree import newInfo

        tmp = ["", "", "", ""]
        tmp2 = [False, False, False, False]
        j: int = 0
        for i in names:
            if i != "":
                player = Player(self.__screen, i, random.randrange(200, 1200), 0, j)
                self.__players_group.add(player)
                self.__all_sprite_group.add(player)
                tmp[j] = i
                tmp2[j] = True
                j += 1

        newInfo.setPlayersName(tmp)
        newInfo.setPlayersGameOver(tmp2)

        platform = Platform(self.__screen, 150, 700, (1050, 100))

        self.__platforms_group.add(platform)
        self.__all_sprite_group.add(platform)

        platform = Platform(self.__screen, 250, 550, (500, 5))

        self.__platforms_group.add(platform)
        self.__all_sprite_group.add(platform)

        platform = Platform(self.__screen, 650, 400, (100, 5))

        self.__platforms_group.add(platform)
        self.__all_sprite_group.add(platform)

        platform = Platform(self.__screen, 850, 250, (100, 5))

        self.__platforms_group.add(platform)
        self.__all_sprite_group.add(platform)

        treeB = TreeSprite(self.__screen, "treeB", 1350, 0)

        self.__trees_group.add(treeB)
        self.__all_sprite_group.add(treeB)

        treeBST = TreeSprite(self.__screen, "treeBST", 1350, 250)

        self.__trees_group.add(treeBST)
        self.__all_sprite_group.add(treeBST)

        treeAVL = TreeSprite(self.__screen, "treeAVL", 1350, 500)

        self.__trees_group.add(treeAVL)
        self.__all_sprite_group.add(treeAVL)

        treeSPLAY = TreeSprite(self.__screen, "treeSplay", 1350, 750)

        self.__trees_group.add(treeSPLAY)
        self.__all_sprite_group.add(treeSPLAY)
        self.__last_token_send: str = ""

        self.__base = Platform(self.__screen, -250, 1500, (2600, 400))

        self.__game_view()

    def __game_view(self) -> None:
        """
        Description: This set the interface objects that will be showed when the game starts.
        Puts all of the labels, and sprites on the right place, and if the game is rnning updates the
        sprites.

        """

        from BuldiATree import newInfo
        self.__timePower = pygame.time.get_ticks()

        bg_image = SceneGame.load_out_img("backgroundGame.png", (self.__scene_size_X, self.__scene_size_Y))

        serverTime = "Tiempo: "
        label_time = LabelText(str(serverTime), 0, SceneGame.get_color()["black"], self.__screen, (200, 30))

        while self.running:
            secondsPower = (pygame.time.get_ticks() - self.__timePower) // 1000
            self.__screen.blit(bg_image, [0, 0])
            label_time.set_text("Tiempo: " + str(self.UI.getTime()))

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
            self.__update_players_and_powers(dt)
            label_time.draw_me()

            if secondsPower > random.randrange(5, 15) and len(self.__tokens_group) + len(self.__powers_group) < 7:
                power = Power(self.__screen, random.randrange(200, 1200), 0)
                self.__powers_group.add(power)
                self.__all_sprite_group.add(power)
                self.__timePower = pygame.time.get_ticks()

            if self.UI.getChallenge() != None and self.__is_time_challenge():
                self.__create_token()
                self.__tokens_group.draw(self.__screen)
                self.__update_tokens(dt)
            else:
                for token in self.__tokens_group:
                    token.kill()
                self.__tokens_group.empty()
                self.__add_point_to_player()

            self.__collisions()

            x = 1
            for n in self.__players_group:
                img = n.get_image()
                label_name = LabelText(n.get_name(), 3, SceneGame.get_color()["black"], self.__screen, (x * 150, 900))
                label_points = LabelText(str(n.get_points()), 3, SceneGame.get_color()["black"], self.__screen,
                                         (x * 150, 925))
                label_tree = LabelText(n.get_tree(), 3, SceneGame.get_color()["black"], self.__screen, (x * 150, 950))
                label_power = LabelText(n.get_power(), 3, SceneGame.get_color()["black"], self.__screen, (x * 150, 975))

                self.__screen.blit(img, [x * 150 - 25, 800])
                label_name.draw_me()
                label_points.draw_me()
                label_tree.draw_me()
                label_power.draw_me()

                x += 1
            self.__GAME_OVER()
            pygame.display.flip()

    def __key_down(self, event_key) -> None:
        """
        Description: this method is activated when the down key is pressed

        Parameters:
           event_key: code with the respective event key

        """

        for i in self.__players_group:
            i.key_down(event_key)

    def __key_up(self, event_key) -> None:
        """
        Description: this method is activated when the up key is pressed

        Parameters:
           event_key: code with the respective event key

        """

        for i in self.__players_group:
            i.key_up(event_key)

    def __collisions(self):
        """
        Description: this method is activated when the two the bounds of two sprites collide.
        It takes into account all of the collision aspects that could affect, like the power ups,
        depending on this it calculates how much the sprite will be pushed and on wjhat direction.


          returns:
           result of the collision

        """

        collide_platform_PY = pygame.sprite.groupcollide(self.__players_group, self.__platforms_group, False, False)

        if collide_platform_PY != {}:
            crasher: Platform = list(collide_platform_PY.values())[0][0]
            victim: Player = list(collide_platform_PY.keys())[0]
            if victim.rect.y <= crasher.get_rect_y():
                victim.collision(floor=[crasher.get_rect_y(), crasher.get_rect_x() + 50,
                                        crasher.get_rect_x() + crasher.get_width() - 50])

        for i in self.__players_group:
            current = self.__players_group.copy()
            current.remove(i)
            collide_player_PY = pygame.sprite.spritecollide(i, current, False, False)
            if collide_player_PY != []:
                victim: Player = i
                if abs(victim.get_rect_x() - collide_player_PY[0].get_rect_x()) <= 10:

                    push = 100
                    v_s = victim.get_shield()
                    v_p = victim.get_push()

                    c_s = collide_player_PY[0].get_shield()
                    c_p = collide_player_PY[0].get_push()
                    if v_s != 0:
                        v_s = push + c_p

                    if c_s != 0:
                        v_s = push + v_p

                    if victim.get_rect_x() > collide_player_PY[0].get_rect_x():
                        victim.set_rect_x(push - v_s + c_p)
                        collide_player_PY[0].set_rect_x(-push + c_s - v_p)
                    else:
                        victim.set_rect_x(-push - (-v_s + c_p))
                        collide_player_PY[0].set_rect_x(push - (c_s - v_p))

        collide_platform_TK = pygame.sprite.groupcollide(self.__tokens_group, self.__platforms_group, False, False)
        if collide_platform_TK != {}:
            crasher: Platform = list(collide_platform_TK.values())[0][0]
            victim: Token = list(collide_platform_TK.keys())[0]
            if victim.rect.y <= crasher.get_rect_y():
                victim.collision(floor=[crasher.get_rect_y(), crasher.get_rect_x() + 50,
                                        crasher.get_rect_x() + crasher.get_width() - 50])

        collide_token_PY = pygame.sprite.groupcollide(self.__players_group, self.__tokens_group, False, False)
        if collide_token_PY != {}:
            crasher: Token = list(collide_token_PY.values())[0][0]
            victim: Player = list(collide_token_PY.keys())[0]


            tree_tmp: TreeSprite = None
            for tree in self.__trees_group:
                if tree.get_id() == victim.get_tree():
                    tree_tmp = tree
                    break

            name_token = crasher.get_name()

            if victim.get_tree() == name_token.split("@")[0]:
                crasher.kill()
                tree_tmp.set_next()

            else:
                crasher.kill()
                tree_tmp.default()

            if victim.get_tree() == "treeSplay":
                self.UI.setTreeSplay(name_token)
            elif victim.get_tree() == "treeAVL":
                self.UI.setTreeAVL(name_token)
            elif victim.get_tree() == "treeB":
                self.UI.setTreeB(name_token)
            elif victim.get_tree() == "treeBST":
                self.UI.setTreeBST(name_token)


            if tree_tmp.is_completed():

                self.__tokens_group.empty()
                self.__add_point_to_player()



        collide_token_PY = pygame.sprite.groupcollide(self.__players_group, self.__powers_group, False, False)
        if collide_token_PY != {}:
            crasher: Power = list(collide_token_PY.values())[0][0]
            victim: Player = list(collide_token_PY.keys())[0]

            victim.collision(power=crasher.get_name())
            crasher.kill()

        collide_platform_PW = pygame.sprite.groupcollide(self.__powers_group, self.__platforms_group, False, False)
        if collide_platform_PW != {}:
            crasher: Platform = list(collide_platform_PW.values())[0][0]
            victim: Token = list(collide_platform_PW.keys())[0]
            if victim.rect.y <= crasher.get_rect_y():
                victim.collision(floor=[crasher.get_rect_y(), crasher.get_rect_x() + 50,
                                        crasher.get_rect_x() + crasher.get_width() - 50])

        collide_game_over = pygame.sprite.spritecollide(self.__base, self.__players_group, False, False)
        if collide_game_over != []:
            collide_game_over[0].kill()
            # Agregar logica

    def get_Y(self) -> int:
        """
        Description: It returns the scene size Y (width) of the scene saved in the attribute

        """
        return self.__scene_size_Y

    def get_X(self) -> int:
        """
        Description: It returns the scene size X (height) of the scene saved in the attribute

        """

        return self.__scene_size_X

    def __update_players_and_powers(self, dt) -> None:
        """
        Description: It updates the players and powers sprites and positions on the screen

        parameters:
        dt: diferencial de tiempo

        """
        for i in self.__players_group:
            i.update(dt)

        for i in self.__powers_group:
            i.update(dt)

        for i in self.__trees_group:
            i.update(dt)

    def __is_time_challenge(self) -> bool:
        """
        Description: It checks if is time for challenge, if the tokenSend attribute of the update info object
        is empty it means it isn't time for a challenge, otherwise, it returns True

        Returns:
            True or False if a challenge is in progress or not

        """
        result: bool = False

        for i in self.UI.getChallenge():

            if i == "":
                result = False
                break
            else:
                result = True

                break

        self.__put_the_tree_to_players(result, self.UI.getChallenge())

        return result

    def __put_the_tree_to_players(self, result: bool, challenge: list):
        """
        Description: It assigns a tree to each player that is alive on the game

        Parameters:
            result: boolean that determines if a challenge is in progress
            challenge: list of shallenges for each player
        """

        if result:
            i = 0
            for py in self.__players_group:
                if py.get_tree() == "":
                    py.set_tree(challenge[i])
                    i += 1
                else:
                    break

    def __create_token(self):
        """
        Description: Takes the info of tokenSend from the server and creates a token.
        Sets the respective info of the token and assigns an image.

        """

        tmp = self.UI.getTokenSend()
        if tmp != None and tmp != "":
            result = True
            for token in self.__tokens_group:
                if token.get_name() == tmp:
                    result = False
                    break

            if result and tmp != self.__last_token_send:
                token = Token(self.__screen, tmp, random.randrange(200, 1200), 0)
                self.__tokens_group.add(token)
                #self.__all_sprite_group.add(token)
                self.__last_token_send = tmp


    def __update_tokens(self, dt):
        """
        Description: Actualiza los tokens.

        Parameters: dt : diferencial de tiempo
        """
        for i in self.__tokens_group:
            if not i.get_dont_work():
                i.update(dt)

    def __add_point_to_player(self) -> None:
        """
        Description: Verifica si el jugador ya completo el arbol completa mente y de ser asi le suma 1000
        puntos a su puntaje actual.

        """

        list_tree = self.__trees_group.sprites()
        max_tree: TreeSprite = list_tree[0]

        for tree in list_tree[1:]:
            tmp: int = int(tree.get_current_key())
            num_s = int(max_tree.get_current_key())
            if num_s < tmp:
                max_tree = tree
            elif num_s == tmp:
                max_tree = random.choice([tree, max_tree])

        for py in self.__players_group:
            if py.get_tree() == max_tree.get_id():
                py.set_points(int(max_tree.get_current_key()) * 1000)
                py.set_tree("")

        for tree in self.__trees_group:
            tree.default()

    def __GAME_OVER(self) -> None:
        """
        Description: This method determines the moment on which the game is over.
        It takes into account the time, the position of the players and if the amount of players alive in
        the game.

        When the game ends it displays the menu image, and shows who the winner was, based on which player had
        the higher score

        """
        if len(self.__players_group) == 1 or self.UI.getTime() == "3:0":
            self.__add_point_to_player()
            max_player = self.__players_group.sprites()[0]
            for player in self.__players_group.sprites()[1:]:
                if max_player.get_points() < player.get_points():
                    max_player = player

            bg_image = SceneGame.load_out_img("backgroundMenu.png", (self.__scene_size_X, self.__scene_size_Y))

            tmp = True
            while tmp:
                bg_image.blit(self.__screen, [0, 0])
                for event in pygame.event.get():
                    if event.type == pygame.QUIT:
                        tmp = False
                        pygame.quit()
                        sys.exit()

                label = LabelText("Ganó   " + max_player.get_name(),0, SceneGame.get_color()["red"], self.__screen,
                                  (self.__scene_size_X / 2, self.__scene_size_Y / 2))

                label.draw_me()

                pygame.display.flip()


    @staticmethod
    def get_path_game() -> str:
        """
        :return: Return a string with the path game
        """
        return SceneGame.__path_game

    @staticmethod
    def get_color() -> dict:
        """
        :return: Return a string with a color that will be used for the bg of the game
        """
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
