from Modelos.Resultado import Resultado
from Modelos.Mesa import Mesa
from Modelos.Candidato import Candidato

from Repositorios.RepositorioResultado import RepositorioResultado
from Repositorios.RepositorioMesa import RepositorioMesa
from Repositorios.RepositorioCandidato import RepositorioCandidato


class ControladorResultado():
    def __init__(self):
        self.repositorioResultado = RepositorioResultado()
        self.repositorioMesas = RepositorioMesa()
        self.repositorioCandidatos = RepositorioCandidato()


    def index(self):
        return self.repositorioResultado.findAll()


    """
    Asignacion mesa y candidato a resultado
    """

    def create(self, infoResultado, id_mesa, id_candidato):
        nuevoResultado = Resultado(infoResultado)
        laMesa = Mesa(self.repositorioMesas.findById(id_mesa))
        elCandidato = Candidato(self.repositorioCandidatos.findById(id_candidato))
        nuevoResultado.mesa = laMesa
        nuevoResultado.candidato = elCandidato
        return self.repositorioResultado.save(nuevoResultado)

    def show(self, id):
        elResultado = Resultado(self.repositorioResultado.findById(id))
        return elResultado.__dict__

    """
    Modificación de resultado (mesa y candidato)
    """

    def update(self, id, infoResultado, id_mesa, id_candidato):
        elResultado = Resultado(self.repositorioResultado.findById(id))
        elResultado.numero_mesa = infoResultado["numero_mesa"]
        elResultado.cedula_candidato = infoResultado["cedula_candidato"]
        elResultado.numero_votos = infoResultado["numero_votos"]

        laMesa = Mesa(self.repositorioMesas.findById(id_mesa))
        elCandidato = Candidato(self.repositorioCandidatos.findById(id_candidato))

        elResultado.mesa = laMesa
        elResultado.candidato = elCandidato
        return self.repositorioResultado.save(elResultado)

    def delete(self, id):
        return self.repositorioResultado.delete(id)

    """
    Obtener todos los resultados por candidato
    """

    def listarResultadosPorCandidato(self, id_candidato):
        return self.repositorioResultado.getListadoResultadosPorCandidato(id_candidato)

    """
    Obtener los resultados por candidato y mesa
    """
    def ResultadosPorCandidatoPorMesa(self, id_candidato, id_mesa):
        return self.repositorioResultado.getResultadoPorCandidatoPorMesa(id_candidato, id_mesa)


    """
    Obtener mayor numero de votos por candidato
    """

    def numeroVotosMasAltoPorCandidato(self):
        return self.repositorioResultado.getMayorNumeroVotosPorCandidato()

