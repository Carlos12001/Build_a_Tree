import pygame


class Token(pygame.sprite.Sprite):

    def __init__(self, screen: pygame.Surface, name: str, px: int, py: int):
        super(Token, self).__init__()
        self.__screen: pygame.Surface = screen
        self.__states_dict: dict = {}
        from python.sprites.EntitySate import EntityState
        self.__current_state: EntityState = None
        self.image: pygame.Surface = None

        self.__dx: int = 0
        self.__dy: int = 0
        self.__jumping: bool = False
        self.dont_work = False

        self.__floor = 1000
        self.__floor_max = 0
        self.__floor_min = 0
        self.__name: str = name

        # Setter images
        self.__set_dict_images()

        # Set position
        self.image = self.__current_state.get_current_sprite()
        self.rect = self.image.get_rect()
        self.rect.x = px
        self.rect.y = py

    def get_rect_x(self) -> int:
        if not self.dont_work:
            return self.rect.x

    def get_rect_y(self) -> int:
        if not self.dont_work:
            return self.rect.y

    def get_name(self) -> str:
        return self.__name

    def get_dont_work(self) -> bool:
        return self.dont_work

    def __set_dict_images(self):
        tmp = self.__name.split("@")[0]
        num = int(self.__name.split("@")[1])
        from python.screen.SceneGame import SceneGame
        from python.sprites.StaticState import StaticState
        image: pygame.Surface = None
        if tmp == "treeB" and num != -1:

            image = (SceneGame.load_out_img("nodeB.png", (50, 50)))
            self.__image_state = StaticState(image, "image")

            self.__states_dict[self.__image_state.get_name()] = self.__image_state
        elif tmp == "treeBST" and num != -1:
            image = (SceneGame.load_out_img("nodeBST.png", (50, 50)))
            self.__image_state = StaticState(image, "image")

            self.__states_dict[self.__image_state.get_name()] = self.__image_state
        elif tmp == "treeAVL" and num != -1:
            image = (SceneGame.load_out_img("nodeAVL.png", (50, 50)))
            self.__image_state = StaticState(image, "image")

            self.__states_dict[self.__image_state.get_name()] = self.__image_state
        elif tmp == "treeSplay" and num != -1:
            image = (SceneGame.load_out_img("nodeSplay.png", (50, 50)))
            self.__image_state = StaticState(image, "image")

        if not image is None:
            self.__states_dict[self.__image_state.get_name()] = self.__image_state
        else:
            self.dont_work = True

        self.set_current_state(self.__image_state.get_name())

    def set_current_state(self, key: int):
        if not self.dont_work:
            self.__current_state = self.__states_dict[key]

    def set_floor(self, floor: int):
        if not self.dont_work:
            self.__floor = floor

    def set_rect_x(self, rectx: int) -> None:
        if not self.dont_work:
            self.rect.x = rectx

    def set_rect_y(self, recty: int) -> None:
        if not self.dont_work:
            self.rect.y = recty

    def impulse(self, dx, dy) -> None:
        self.__dx = dx
        self.__dy = dy

    def calculate_gravity(self) -> None:
        self.__dy = self.__dy + 0.01

    def jump(self, jump_force):
        self.impulse(self.__dx, -jump_force)

    def update(self, dt):
        if not self.dont_work:

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
        if not self.dont_work:
            if not floor is None:
                self.set_floor(floor[0])
                self.__floor_min = floor[1]
                self.__floor_max = floor[2]
