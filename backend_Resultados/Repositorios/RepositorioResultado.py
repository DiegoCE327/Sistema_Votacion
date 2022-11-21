from Repositorios.InterfaceRepositorio import InterfaceRepositorio
from Modelos.Resultado import Resultado

from bson import ObjectId


class RepositorioResultado(InterfaceRepositorio[Resultado]):

    def getListadoResultadosPorCandidato(self, id_candidato):
        theQuery = {"candidato.$id": ObjectId(id_candidato)}
        return self.query(theQuery)

    def getResultadoPorCandidatoPorMesa(self, id_candidato, id_mesa):
        theQuery = {'candidato.$id': ObjectId(id_candidato), 'mesa.$id': ObjectId(id_mesa)}
        return self.query(theQuery)



    def getMayorNumeroVotosPorCandidato(self):
        query1 = {
            "$group": {
                "_id": "$candidato",
                "max": {
                    "$max": "$numero_votos"
                },
                "doc": {
                    "$first": "$$ROOT"
                }
              }
            }
        pipeline = [query1]
        return self.queryAggregation(pipeline)

