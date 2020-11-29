import pygame


from python.sprites.AnimatedState import AnimatedState
from python.sprites.EntitySate import EntityState
from python.sprites.StaticState import StaticState


class Player(pygame.sprite.Sprite):

    def __init__(self, screen: pygame.Surface, name: str, px: int, py: int, id: int):
        super(Player, self).__init__()
        self.__screen: pygame.Surface = screen
        self.__states_dict: dict = {}
        self.__current_state: EntityState = None
        self.__dx: int = 0
        self.__dy: int = 0
        self.image: pygame.Surface = None

        self.__name: str = name
        self.__life: int = 1500
        self.__attack: int = 50
        self.__id: int = id
        self.__tree: str = ""

        self.__jumping: bool = False
        self.__speed: int = 5.5
        self.__floor: int = 0
        self.__game_over: bool = False

        #Setter images
        self.__set_dict_images()

        # Set position
        self.image = self.__current_state.get_current_sprite()
        self.rect = self.image.get_rect()
        self.rect.x = px
        self.rect.y = py

    def set_current_state(self, key: int):
        self.__current_state = self.__states_dict[key]

    def impulse(self, dx, dy) -> None:
        self.__dx = dx
        self.__dy = dy

    def get_rect_x(self) -> int:
        return self.rect.x

    def get_rect_y(self) -> int:
        return self.rect.y

    def get_name(self) -> str:
        return self.__name

    def get_tree(self) -> str:
        return self.__tree

    def get_life(self) -> int:
        return self.__life

    def get_attack(self) -> int:
        return self.__attack

    def get_game_over(self) -> bool:
        return self.__game_over

    def calculate_gravity(self) -> None:
        self.__dy = self.__dy + 0.35

    def jump(self, jump_force):
        self.impulse(self.__dx, -jump_force)

    def __set_dict_images(self):
        if self.__id==0:
            self.__set_dict_player_0()
        elif self.__id==1:
            self.__set_dict_player_1()
        elif self.__id==2:
            self.__set_dict_player_2()
        elif self.__id==3:
            self.__set_dict_player_3()

    def __set_dict_player_0(self):
        # Images
        from python.screen import SceneGame
        self.__walking_image_right: pygame.Surface = SceneGame.SceneGame.load_out_img("player1Run.png",(1800,300))
        self.__number_of_sprites_walking = 8

        self.__walking_right_state = AnimatedState(self.__walking_image_right, self.__number_of_sprites_walking,
                                                   800, "walking_right")

        self.__resting_right_state = StaticState(self.__walking_image_right.subsurface(0, 0,
                                                                                       self.__walking_image_right.get_width() / self.__number_of_sprites_walking,
                                                                                       self.__walking_image_right.get_height()),
                                                 "resting_right")

        self.__states_dict[self.__walking_right_state.get_name()] = self.__walking_right_state
        self.__states_dict[self.__resting_right_state.get_name()] = self.__resting_right_state

        self.set_current_state(self.__resting_right_state.get_name())

    def __set_dict_player_1(self):
        from python.screen import SceneGame
        self.__walking_image_right: pygame.Surface = SceneGame.SceneGame.load_out_img("player1Run.png",(1800,300))
        self.__number_of_sprites_walking = 8

        self.__walking_right_state = AnimatedState(self.__walking_image_right, self.__number_of_sprites_walking,
                                                   800, "walking_right")

        self.__resting_right_state = StaticState(self.__walking_image_right.subsurface(0, 0,
                                                                                       self.__walking_image_right.get_width() / self.__number_of_sprites_walking,
                                                                                       self.__walking_image_right.get_height()),
                                                 "resting_right")

        self.__states_dict[self.__walking_right_state.get_name()] = self.__walking_right_state
        self.__states_dict[self.__resting_right_state.get_name()] = self.__resting_right_state

        self.set_current_state(self.__resting_right_state.get_name())

    def __set_dict_player_2(self):
        from python.screen import SceneGame
        self.__walking_image_right: pygame.Surface = SceneGame.SceneGame.load_out_img("player1Run.png",(1800,300))
        self.__number_of_sprites_walking = 8

        self.__walking_right_state = AnimatedState(self.__walking_image_right, self.__number_of_sprites_walking,
                                                   800, "walking_right")

        self.__resting_right_state = StaticState(self.__walking_image_right.subsurface(0, 0,
                                                                                       self.__walking_image_right.get_width() / self.__number_of_sprites_walking,
                                                                                       self.__walking_image_right.get_height()),
                                                 "resting_right")

        self.__states_dict[self.__walking_right_state.get_name()] = self.__walking_right_state
        self.__states_dict[self.__resting_right_state.get_name()] = self.__resting_right_state

        self.set_current_state(self.__resting_right_state.get_name())

    def __set_dict_player_3(self):
        from python.screen import SceneGame
        self.__walking_image_right: pygame.Surface = SceneGame.SceneGame.load_out_img("player1Run.png",(1800,300))
        self.__number_of_sprites_walking = 8

        self.__walking_right_state = AnimatedState(self.__walking_image_right, self.__number_of_sprites_walking,
                                                   800, "walking_right")

        self.__resting_right_state = StaticState(self.__walking_image_right.subsurface(0, 0,
                                                                                       self.__walking_image_right.get_width() / self.__number_of_sprites_walking,
                                                                                       self.__walking_image_right.get_height()),
                                                 "resting_right")

        self.__states_dict[self.__walking_right_state.get_name()] = self.__walking_right_state
        self.__states_dict[self.__resting_right_state.get_name()] = self.__resting_right_state

        self.set_current_state(self.__resting_right_state.get_name())



    def key_down(self, key):
            if self.__id==0:
                self.__key_down_player_0(key)
            elif self.__id==1:
                self.__key_down_player_1(key)
            elif self.__id==2:
                self.__key_down_player_2(key)
            elif self.__id==3:
                self.__key_down_player_3(key)

    def __key_down_player_0(self, key):
        if key == pygame.K_w:
            if not self.__jumping:
                self.jump(10)
                self.__jumping = True
        if key == pygame.K_s:
            pass
        if key == pygame.K_d:
            self.__dx = self.__speed
            self.set_current_state(self.__walking_right_state.get_name())
        if key == pygame.K_a:
            self.__dx = -self.__speed
            # Aqui seria left
            self.set_current_state(self.__walking_right_state.get_name())

    def __key_down_player_1(self, key):
        if key == pygame.K_UP:
            if not self.__jumping:
                self.jump(10)
                self.__jumping = True
        if key == pygame.K_DOWN:
            pass
        if key == pygame.K_RIGHT:
            self.__dx = self.__speed
            self.set_current_state(self.__walking_right_state.get_name())
        if key == pygame.K_LEFT:
            self.__dx = -self.__speed
            # Aqui seria left
            self.set_current_state(self.__walking_right_state.get_name())

    def __key_down_player_2(self, key):
        if key == pygame.K_i:
            if not self.__jumping:
                self.jump(10)
                self.__jumping = True
        if key == pygame.K_k:
            pass
        if key == pygame.K_l:
            self.__dx = self.__speed
            self.set_current_state(self.__walking_right_state.get_name())
        if key == pygame.K_j:
            self.__dx = -self.__speed
            # Aqui seria left
            self.set_current_state(self.__walking_right_state.get_name())

    def __key_down_player_3(self, key):
        if key == pygame.K_g:
            if not self.__jumping:
                self.jump(10)
                self.__jumping = True
        if key == pygame.K_b:
            pass
        if key == pygame.K_n:
            self.__dx = self.__speed
            self.set_current_state(self.__walking_right_state.get_name())
        if key == pygame.K_v:
            self.__dx = -self.__speed
            # Aqui seria left
            self.set_current_state(self.__walking_right_state.get_name())


    def key_up(self, key):
        if self.__id==0:
            self.__key_up_player_0(key)
        elif self.__id==1:
            self.__key_up_player_1(key)
        elif self.__id==2:
            self.__key_up_player_2(key)
        elif self.__id==3:
            self.__key_up_player_3(key)

    def __key_up_player_0(self, key):
        if key == pygame.K_w:
            pass
        if key == pygame.K_s:
            pass
        if key == pygame.K_d:
            if self.__dx > 0:
                self.__dx = 0
                # Aqui seria left
                self.set_current_state(self.__resting_right_state.get_name())
        if key == pygame.K_a:
            if self.__dx < 0:
                self.__dx = 0
                self.set_current_state(self.__resting_right_state.get_name())

    def __key_up_player_1(self, key):
        if key == pygame.K_UP:
            pass
        if key == pygame.K_DOWN:
            pass
        if key == pygame.K_RIGHT:
            if self.__dx > 0:
                self.__dx = 0
                # Aqui seria left
                self.set_current_state(self.__resting_right_state.get_name())
        if key == pygame.K_LEFT:
            if self.__dx < 0:
                self.__dx = 0
                self.set_current_state(self.__resting_right_state.get_name())

    def __key_up_player_2(self, key):
        if key == pygame.K_i:
            pass
        if key == pygame.K_k:
            pass
        if key == pygame.K_l:
            if self.__dx > 0:
                self.__dx = 0
                # Aqui seria left
                self.set_current_state(self.__resting_right_state.get_name())
        if key == pygame.K_j:
            if self.__dx < 0:
                self.__dx = 0
                self.set_current_state(self.__resting_right_state.get_name())

    def __key_up_player_3(self, key):
        if key == pygame.K_g:
            pass
        if key == pygame.K_b:
            pass
        if key == pygame.K_n:
            if self.__dx > 0:
                self.__dx = 0
                # Aqui seria left
                self.set_current_state(self.__resting_right_state.get_name())
        if key == pygame.K_v:
            if self.__dx < 0:
                self.__dx = 0
                self.set_current_state(self.__resting_right_state.get_name())

    def update(self, dt):

        self.calculate_gravity()

        self.rect.x += self.__dx
        self.rect.y += self.__dy

        if self.rect.y + self.rect.height > self.__screen.get_height():
            self.rect.y = self.__screen.get_height() - self.rect.height
            self.__jumping = False
            self.__dy = self.__floor


        self.__current_state.update(dt=dt)
        self.image = self.__current_state.get_current_sprite()

    def collision(self, power=None, token=None, floor=None):
        if not floor is None:
            pass
        if not power is None:
            pass
        if not token is None:
            pass