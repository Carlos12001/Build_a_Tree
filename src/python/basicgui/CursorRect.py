import pygame


class CursorRect(pygame.Rect):
    """
    Description: Updates the image of the button if the cursor is on it or collides with it.

    Attruibutes: 

    """
    def __init__(self):
        """
        Description: Updates the image of the button if the cursor is on it or collides with it.

        Parameters:
            cursor: is the position of the cursor

        """
        pygame.Rect.__init__(self, 0, 0, 1, 1)

    def update(self):
        """
        Description: Updates the image of the button if the cursor is on it or collides with it.

        Parameters:
            cursor: is the position of the cursor

        """
        self.left, self.top = pygame.mouse.get_pos()
