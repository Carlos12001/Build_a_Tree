import pygame


from python.sprites.AnimatedState import AnimatedState
from python.sprites.EntitySate import EntityState
from python.sprites.StaticState import StaticState


class Player(pygame.sprite.Sprite):

    def __init__(self, screen: pygame.Surface, name: str, px: int, py: int):
        super(Player, self).__init__()
        self.__screen: pygame.Surface = screen
        self.__states_dict: dict = {}
        self.__current_state = None
        self.__dx: int = 0
        self.__dy: int = 0
        self.image: pygame.Surface = None

        self.__name: str = name

        self.__jumping: bool = False
        self.__speed: int = 3.5

        # Images
        from python.screen import SceneGame
        self.__walking_image_right: pygame.Surface = SceneGame.SceneGame.load_out_img("player1Run.png")
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

        # Set position
        self.image = self.__current_state.get_current_sprite()
        self.rect = self.image.get_rect()
        self.rect.x = px
        self.rect.y = py

    def set_current_state(self, key: int):
        self.__current_state = self.__states_dict[key]

    def update(self):
        pass

    def impulse(self, dx, dy):
        self.__dx = dx
        self.__dy = dy

    def get_rect_x(self) -> int:
        return self.rect.x

    def get_rect_y(self) -> int:
        return self.rect.y

    def calculate_gravity(self):
        self.__dy = self.__dy + 0.35

    def jump(self, jump_force):
        self.impulse(self.__dx, -jump_force)

    def key_down(self, key):
        if key == pygame.K_UP:
            if not self.__jumping:
                self.jump(10)
                self.__jumping = False
        if key == pygame.K_DOWN:
            pass
        if key == pygame.K_RIGHT:
            self.__dx = self.__speed
        if key == pygame.K_LEFT:
            self.__dx = self.__speed
            self.set_current_state(self.__walking_right_state.get_name())

    def key_up(self, key):
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

    def update(self, dt):

        self.calculate_gravity()
        self.rect.x = self.rect.x + self.__dx
        self.rect.y = self.rect.y + self.__dy

        if self.rect.y + self.rect.height > self.__screen.get_height():
            self.rect.y = self.__screen.get_height() - self.rect.height
            self.__jumping = False
            self.__dy = 0


        print(type(self.__current_state))
        self.__current_state.update(dt=dt)
        self.image = self.__current_state.get_current_sprite()
