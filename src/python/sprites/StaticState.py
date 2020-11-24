import pygame

from python.sprites.EntitySate import EntityState


class StaticState(EntityState):


    def __init__(self, image, name):
        super(StaticState, self).__init__()
        self.set_current_sprite(image)
        self.set_name(name)