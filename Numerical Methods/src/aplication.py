from java.awt.event import ActionListener
from javax.swing.event import ChangeListener
from javax.swing import JOptionPane
from java.lang import Integer

import numerical

class Aplicacion:
    form = None

    @staticmethod
    def run():
        Aplicacion.form = numerical.GUI()
        Aplicacion.form.visible = True
