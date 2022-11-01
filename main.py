import json

from flask import request
from flask import Flask
from flask import jsonify
from flask_cors import CORS
from waitress import serve

from Controladores.ControladorCandidato import ControladorCandidato
from Controladores.ControladorMesa import ControladorMesa
from Controladores.ControladorPartido import ControladorPartido
from Controladores.ControladorResultado import ControladorResultado

miControladorCandidato = ControladorCandidato()
miControladorMesa = ControladorMesa()
miControladorPartido = ControladorPartido()
miControladorResultado = ControladorResultado()


# Permite hacer pruebas al servidor desde la misma maquina donde se corre el programa
app=Flask(__name__)
cors = CORS(app)

######################################################################################

# @app.route --> flask detecte que se está declarando un servicio,
@app.route("/",methods=['GET'])
# funcion test de prueba
def test():
    json = {}
    json["message"]="Server running ..."
    return jsonify(json)

######################################################################################


@app.route("/mesas",methods=['GET'])
def getMesas():
    json=miControladorMesa.index()
    return jsonify(json)


@app.route("/mesas",methods=['POST'])
def crearMesa():
    data = request.get_json()
    json=miControladorMesa.create(data)
    return jsonify(json)


@app.route("/mesas/<string:id>",methods=['GET'])
def getMesa(id):
    json=miControladorMesa.show(id)
    return jsonify(json)


@app.route("/mesas/<string:id>",methods=['PUT'])
def modificarMesa(id):
    data = request.get_json()
    json=miControladorMesa.update(id,data)
    return jsonify(json)


@app.route("/mesas/<string:id>",methods=['DELETE'])
def eliminarMesa(id):
    json=miControladorMesa.delete(id)
    return jsonify(json)

#####################################################################################


@app.route("/partidos",methods=['GET'])
def getPartidos():
    json=miControladorPartido.index()
    return jsonify(json)


@app.route("/partidos/<string:id>",methods=['GET'])
def getPartido(id):
    json=miControladorPartido.show(id)
    return jsonify(json)


@app.route("/partidos",methods=['POST'])
def crearPartido():
    data = request.get_json()
    json=miControladorPartido.create(data)
    return jsonify(json)


@app.route("/partidos/<string:id>",methods=['PUT'])
def modificarPartido(id):
    data = request.get_json()
    json=miControladorPartido.update(id,data)
    return jsonify(json)


@app.route("/partidos/<string:id>",methods=['DELETE'])
def eliminarPartido(id):
    json=miControladorPartido.delete(id)
    return jsonify(json)


#####################################################################################

@app.route("/candidatos",methods=['GET'])
def getCandidatos():
    json=miControladorCandidato.index()
    return jsonify(json)


@app.route("/candidatos/<string:id>",methods=['GET'])
def getCandidato(id):
    json=miControladorCandidato.show(id)
    return jsonify(json)


@app.route("/candidatos",methods=['POST'])
def crearCandidato():
    data = request.get_json()
    json=miControladorCandidato.create(data)
    return jsonify(json)


@app.route("/candidatos/<string:id>",methods=['PUT'])
def modificarCandidato(id):
    data = request.get_json()
    json=miControladorCandidato.update(id,data)
    return jsonify(json)


@app.route("/candidatos/<string:id>",methods=['DELETE'])
def eliminarCandidato(id):
    json=miControladorCandidato.delete(id)
    return jsonify(json)


####################################################################################

@app.route("/candidatos/<string:id>/partido/<string:id_partido>",methods=['PUT'])
def asignarPartidoACandidato(id,id_partido):
    json = miControladorCandidato.asignarPartido(id,id_partido)
    return jsonify(json)

#######################################################################################


@app.route("/resultados", methods=['GET'])
def getResultados():
    json = miControladorResultado.index()
    return jsonify(json)


@app.route("/resultados/<string:id>", methods=['GET'])
def getResultado(id):
    json=miControladorResultado.show(id)
    return jsonify(json)


@app.route("/resultados/mesa/<string:id_mesa>/candidato/<string:id_candidato>",methods=['POST'])
def crearResultado(id_mesa,id_candidato):
    data = request.get_json()
    json=miControladorResultado.create(data,id_mesa,id_candidato)
    return jsonify(json)


@app.route("/resultados/<string:id_resultado>/mesa/<string:id_mesa>/candidato/<string:id_candidato>",methods=['PUT'])
def modificarResultado(id_resultado,id_mesa,id_candidato):
    data = request.get_json()
    json=miControladorResultado.update(id_resultado,data,id_mesa,id_candidato)  #Linea corregida
    return jsonify(json)


@app.route("/resultados/<string:id_resultado>",methods=['DELETE'])
def eliminarResultado(id_resultado):
    json=miControladorResultado.delete(id_resultado)
    return jsonify(json)


@app.route("/resultados/candidato/<string:id_candidato>",methods=['GET'])
def resultadosPorCandidato(id_candidato):
    json= miControladorResultado.listarResultadosPorCandidato(id_candidato)
    return jsonify(json)


@app.route("/resultados/candidato/<string:id_candidato>/mesa/<string:id_mesa>", methods=['GET'])
def resultadosPorCandidatoPorMesa(id_candidato, id_mesa):
        json= miControladorResultado.ResultadosPorCandidatoPorMesa(id_candidato, id_mesa)
        return jsonify(json)

@app.route("/resultados/mayores_votaciones",methods=['GET'])
def getMayorNumeroVotos():
    json=miControladorResultado.numeroVotosMasAltoPorCandidato()
    return jsonify(json)

#######################################################################################
# Metodo leer el archivo de configuracion del proyecto


def loadFileConfig():
    with open('config.json') as f:
        data = json.load(f)
    return data


# Es la primera linea que se lee al ejecutar el archivo
if __name__=='__main__':
    # Se carga el archivo config.json
    dataConfig = loadFileConfig()
    # impresion de mensaje con informacion del serivor corriendo y puerto de servicio
    print("Server running : "+"http://"+dataConfig["url-backend"]+":" + str(dataConfig["port"]))
    # Crea la instancia del servidor con la url del backend y puerto especifico del archivo de configuracion
    serve(app,host=dataConfig["url-backend"],port=dataConfig["port"])



