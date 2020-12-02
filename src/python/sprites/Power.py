import pygame, random




class Power(pygame.sprite.Sprite):

    def __init__(self, screen: pygame.Surface, px: int, py: int):
        super(Power, self).__init__()
        self.__screen: pygame.Surface = screen
        self.__states_dict: dict = {}
        from python.sprites.EntitySate import EntityState
        self.__current_state: EntityState = None
        self.image: pygame.Surface = None

        self.__dx: int = 0
        self.__dy: int = 0
        self.__jumping: bool = False

        self.__floor = 1000
        self.__floor_max = 0
        self.__floor_min = 0
        self.__name: str = "jumper"#random.choice(["jumper", "shield", "push"])



        # Setter images
        self.__set_dict_images()

        # Set position
        self.image = self.__current_state.get_current_sprite()
        self.rect = self.image.get_rect()
        self.rect.x = px
        self.rect.y = py

    def get_rect_x(self) -> int:
        return self.rect.x

    def get_rect_y(self) -> int:
        return self.rect.y

    def get_name(self) -> str:
        return self.__name

    def __set_dict_images(self):
        from python.screen.SceneGame import SceneGame
        from python.sprites.StaticState import StaticState
        image: pygame.Surface = None

        print(self.__name)
        if self.__name == "jumper":

            image = (SceneGame.load_out_img("jump.png", (50, 50)))
            self.__image_state = StaticState(image, "image")
            print(self.__image_state, image, self.__name)

        elif self.__name == "shield":
            image = (SceneGame.load_out_img("escudo.png", (50, 50)))
            self.__image_state = StaticState(image, "image")

        elif self.__name == "push":
            image = (SceneGame.load_out_img("push.png", (50, 50)))
            self.__image_state = StaticState(image, "image")

        self.__states_dict[self.__image_state.get_name()] = self.__image_state

        self.set_current_state(self.__image_state.get_name())

    def set_current_state(self, key: int):
        self.__current_state = self.__states_dict[key]

    def set_floor(self, floor: int):
        self.__floor = floor

    def set_rect_x(self, directx: int) -> None:
        self.rect.x = directx

    def set_rect_y(self, rect: int) -> None:
        self.rect.y = rect

    def impulse(self, dx, dy) -> None:
        self.__dx = dx
        self.__dy = dy

    def calculate_gravity(self) -> None:
        self.__dy = self.__dy + 0.01

    def jump(self, jump_force):
        self.impulse(self.__dx, -jump_force)

    def update(self, dt):

        self.calculate_gravity()

        self.rect.x += self.__dx
        self.rect.y += self.__dy

        if self.rect.y + self.rect.height > self.__floor:
            self.rect.y = self.__floor - self.rect.height
            self.__jumping = False
            self.__dy = 0

        if self.__floor_max <= self.rect.x:
            self.__floor = 1000

        if self.__floor_min > self.rect.x:
            self.__floor = 1000

        self.__current_state.update(dt=dt)
        self.image = self.__current_state.get_current_sprite()

    def collision(self, floor=None):
        if floor is not None:
            self.set_floor(floor[0])
            self.__floor_min = floor[1]
            self.__floor_max = floor[2]
