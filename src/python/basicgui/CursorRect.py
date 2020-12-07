import pygame


class CursorRect(pygame.Rect):
    """
    Description: Updates the image of the button if the cursor is on it or collides with it.

    Attruibutes: None

    """
    def __init__(self):
        """
        Description: Constructor
        Sets the pygame.Rect to its initial values

        Parameters:
            self

        """
        pygame.Rect.__init__(self, 0, 0, 1, 1)

    def update(self):
        """
        Description: Takes the mouse position with a rect, to get the coordinates on the screen

        Parameters:
            self
            initial values

        """
        self.left, self.top = pygame.mouse.get_pos()
