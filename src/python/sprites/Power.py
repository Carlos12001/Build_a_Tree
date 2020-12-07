import pygame, random




class Power(pygame.sprite.Sprite):
    """
    Description: This class is going to be the one that manages the aspects of the game related to power ups
    Selecting which power up is going to appear on screen, selecting the proper image for it, moving it and
    the position across the screen, collisions and assigning it to a player.

    """

    def __init__(self, screen: pygame.Surface, px: int, py: int):
        """
        Description: -- Constructor--

        Attributes:
            screen: surface on which the sprite will be showed
            states_dict: dictionary with the states of the sprites
            current_state: attribute with an instance of entityState
            dx: position in the x coordinates of the sprite
            dy: position in the y coordinates of the sprite
            names: list with the names of the different powers available
            image: image of the surface that will be set later
            jumping: boolean if its jumping sets to true
            set_dict_images: sets the images in the dictionary following the method params
            rectx : px or position in x axis
            recty: py or position in y axis

        """

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
        self.__name: str = random.choice(["jumper", "shield", "push"])

        # Setter images
        self.__set_dict_images()

        # Set position
        self.image = self.__current_state.get_current_sprite()
        self.rect = self.image.get_rect()
        self.rect.x = px
        self.rect.y = py

    def get_rect_x(self) -> int:
        """
        Description: Gets the x axis bound of the rect that "contains" the power up

        Returns: int
        """
        return self.rect.x

    def get_rect_y(self) -> int:
        """
        Description: Gets the y axis bound of the rect that "contains" the power up

        Returns: int
        """
        return self.rect.y

    def get_name(self) -> str:
        """
        Description: Returns the name of the power up

        Returns
        """
        return self.__name

    def __set_dict_images(self):
        """
        Description: This method selects the image, its size and where is it going to be showed, depending
        on which string value receives and saves it on the states dict attribute.

        """
        from python.screen.SceneGame import SceneGame
        from python.sprites.StaticState import StaticState
        image: pygame.Surface = None


        if self.__name == "jumper":

            image = (SceneGame.load_out_img("jump.png", (50, 50)))
            self.__image_state = StaticState(image, "image")


        elif self.__name == "shield":
            image = (SceneGame.load_out_img("escudo.png", (50, 50)))
            self.__image_state = StaticState(image, "image")

        elif self.__name == "push":
            image = (SceneGame.load_out_img("push.png", (50, 50)))
            self.__image_state = StaticState(image, "image")

        self.__states_dict[self.__image_state.get_name()] = self.__image_state

        self.set_current_state(self.__image_state.get_name())

    def set_current_state(self, key: int):
        """
        Description: This method receives an integer that sets which state the power up is on taking it
        from the states dictionary, and placing the value as the current state.

        """
        self.__current_state = self.__states_dict[key]

    def set_floor(self, floor: int):
        """
        Description: This method receives an integer that sets which state the power up is on taking it
        from the states dictionary, and placing the value as the current state.

        """
        self.__floor = floor

    def set_rect_x(self, directx: int) -> None:
        """
        Description: Sets the x axis bound of the rect that "contains" the power up

        """
        self.rect.x = directx

    def set_rect_y(self, rect: int) -> None:
        """
        Description: Sets the y axis bound of the rect that "contains" the power up

        """
        self.rect.y = rect

    def impulse(self, dx, dy) -> None:
        """
        Description: Impulse changes the position in the x or y coordinates of the player

        Parameters:
            dx: new position on x axis
            dy new position on y axis

        """
        self.__dx = dx
        self.__dy = dy

    def calculate_gravity(self) -> None:
        """
        Description: this calculetes the speed for the players to fall, it uses the dy axis value
        and adds 0.01 to it.

        """
        self.__dy = self.__dy + 0.01

    def jump(self, jump_force):
        """
        Description: It uses the impulse method to place a jump force

        Parameters:
            jump_force: y axis value that is gonna be changed
        """
        self.impulse(self.__dx, -jump_force)

    def update(self, dt):
        """
        Description: It calculates the gravity value and updates the current position of the player
        based on the time differential parameter.

        Parameter:
            dt: time diferential

        """

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
        """
        Description: It calculates the collision and sets a result value depending on:
        if the player is on the flor
        if the player has powers

        Parameter:
            floor: if the player is on the floor
        """
        if floor is not None:
            self.set_floor(floor[0])
            self.__floor_min = floor[1]
            self.__floor_max = floor[2]
