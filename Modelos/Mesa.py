# de esta clase se heredará el constructor especializado
# con esto se está ahorrando de copiar y pegar el constructor en todas las clases
from Modelos.AbstractModelo import AbstractModelo


# se está definiendo la clase “Mesa” y dentro de paréntesis se coloca
# el padre de esta clase “AbstractModelo”,
class Mesa(AbstractModelo):
    # palabra reservada “pass” la cual es para hacer caso omiso y
    # permitir que la clase tenga un primer cuerpo sin presentar errores.
    pass