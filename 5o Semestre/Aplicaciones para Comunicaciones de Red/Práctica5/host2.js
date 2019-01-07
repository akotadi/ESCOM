let port = process.env.PORT;
if (port == null || port === '' || port === undefined) {
  port = 4000;
}

var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);
var ioClient = require('socket.io-client');

app.get('/', function(req, res){
  res.sendFile(__dirname + '/public/index.html');
});

let dataDictionary = new Map();


dataDictionary.set('Nube', 'Agregado visible de minúsculas gotitas de agua, de cristales de hielo o de ambos, suspendido en la atmósfera y producido por la condensación de vapor de agua.');
dataDictionary.set('Arista', 'Línea que resulta de la intersección de dos planos, considerada por su parte exterior.');
dataDictionary.set('Mano', 'Parte del cuerpo humano unida a la extremidad del antebrazo y que comprende desde la muñeca inclusive hasta la punta de los dedos.');
dataDictionary.set('Computadora', 'Máquina electrónica que, mediante determinados programas, permite almacenar y tratar información, y resolver problemas de diversa índole.');
dataDictionary.set('Pastel', 'Masa de harina y manteca, cocida al horno, en que ordinariamente se envuelve crema o dulce, y a veces carne, fruta o pescado.');
dataDictionary.set('Pintura', 'Tabla, lámina o lienzo en que está pintado algo.');
dataDictionary.set('Abismo', 'Profundidad grande, imponente y peligrosa, como la de los mares, la de un tajo, la de una sima, etc.');
dataDictionary.set('Ascensor', 'Aparato para trasladar personas de unos pisos a otros.');
dataDictionary.set('Espátula', 'Paleta, generalmente pequeña, con bordes afilados y mango largo, que utilizan los farmacéuticos y los pintores para hacer ciertas mezclas, y usada también en otros oficios.');
dataDictionary.set('Pulga', 'nsecto afaníptero, sin alas, de unos dos milímetros de longitud, color negro rojizo, cabeza pequeña, antenas cortas, patas fuertes, largas y adaptadas al salto, y parásito del ser humano y algunos animales.');
dataDictionary.set('Pinza', 'Instrumento, generalmente compuesto de dos piezas, cuyos extremos se aproximan para hacer presión sobre algo y sujetarlo.');
dataDictionary.set('Rapiña', 'Robo, expoliación o saqueo que se ejecuta arrebatando con violencia.');
dataDictionary.set('Vapor', 'Fluido gaseoso cuya temperatura es inferior a su temperatura crítica. Su presión no aumenta al ser comprimido, sino que se transforma parcialmente en líquido; p. ej., el producido por la ebullición del agua.');
dataDictionary.set('Calavera', 'Conjunto de los huesos de la cabeza mientras permanecen unidos, pero despojados de la carne y de la piel.');
dataDictionary.set('Altiplano', 'Meseta de mucha extensión, situada a gran altitud.');
dataDictionary.set('Colisión', 'Choque de dos cuerpos.');
dataDictionary.set('Peste', 'Enfermedad contagiosa y grave que causa gran mortandad.');
dataDictionary.set('Mago', 'Dicho de una persona: Versada en la magia o que la practica.');
dataDictionary.set('Militar', 'Servir en la guerra.');
dataDictionary.set('Sonámbulo', 'Dicho de una persona: Que mientras está dormida tiene cierta aptitud para ejecutar algunas funciones correspondientes a la vida de relación exterior, como las de levantarse, andar y hablar.');
dataDictionary.set('Vanidoso', 'Dicho de una persona: Que tiene vanidad y la muestra.');
dataDictionary.set('Merienda', 'Comida ligera que se toma a media tarde.');
dataDictionary.set('Piano', 'Instrumento musical de cuerdas generalmente metálicas dispuestas dentro de una caja de resonancia, que son golpeadas por macillos accionados desde un teclado.');
dataDictionary.set('Gasa', 'Tela de seda o hilo muy clara y fina.');
dataDictionary.set('Bufanda', 'Prenda larga y estrecha, por lo común de lana o seda, con que se envuelve y abriga el cuello y la boca.');
dataDictionary.set('Grano', 'Semilla y fruto de los cereales.');
dataDictionary.set('Barriga', 'Región exterior del cuerpo humano correspondiente al abdomen, especialmente si es abultado.');
dataDictionary.set('Tazón', 'Recipiente comúnmente mayor que una taza, de contorno aproximadamente semiesférico, a veces con un pie diferenciado y generalmente sin asa.');
dataDictionary.set('Aceituna', 'Fruto del olivo.');
dataDictionary.set('Templo', 'Edificio o lugar destinado pública y exclusivamente a un culto.');
dataDictionary.set('Pared', 'Obra de albañilería vertical, que cierra o limita un espacio.');
dataDictionary.set('Exterior', 'Que está por la parte de fuera.');
dataDictionary.set('Sombrero', 'Prenda para cubrir la cabeza, que consta de copa y ala.');
dataDictionary.set('Viñedo', 'Terreno plantado de vides.');
dataDictionary.set('Colilla', 'Resto del cigarro, que se tira por no poder o no querer fumarlo.');
dataDictionary.set('Etiqueta', 'Pieza de papel, cartón u otro material semejante, generalmente rectangular, que se coloca en un objeto o en una mercancía para identificación, valoración, clasificación, etc.');
dataDictionary.set('Ratonera', 'Trampa en que se cogen o cazan los ratones.');
dataDictionary.set('Embudo', 'Instrumento hueco, ancho por arriba y estrecho por abajo, en forma de cono y rematado en un canuto, que sirve para trasvasar líquidos.');
dataDictionary.set('Comisaría', 'Oficina del comisario.');
dataDictionary.set('Hielo', 'Agua convertida en cuerpo sólido y cristalino por un descenso suficiente de temperatura.');

const mainServer = 'http://localhost:8000';
const thisServer = 'http://localhost:' + port;

io.on('connection', socket => {
  console.log('new Client Connected');

  socket.on('request-def', (word) => {
    let definition = dataDictionary.get(word);
    console.log(word, definition);
    if (definition) {
      socket.emit('def', dataDictionary.get(word));
      return;
    }

    let clientSock = ioClient(mainServer);
    clientSock.emit('request-ip', word);
    clientSock.on('ip', ip => {
      console.log('ip', ip);
      clientSock.disconnect();
      if (ip && ip !== 'false') {
        clientSock = ioClient(ip);
        clientSock.emit('request-def', word);
        clientSock.on('def', def => {
          clientSock.disconnect();
          socket.emit('def', def);
        });
      } else {
        socket.emit('def', 'Not Found.');
      }
    });
  });

  socket.on('add', (data) => {
    console.log(data);
    dataDictionary.set(data.word, data.def);

    clientSock = ioClient(mainServer);
    clientSock.emit('add', { word: data.word, ip: thisServer });
    clientSock.on('added', () => {
      clientSock.disconnect();
    });
  });

  socket.on('erase', (word) => {
    let definition = dataDictionary.get(word);
    if (definition) {
      dataDictionary.delete(word);
    }

    clientSock = ioClient(mainServer);
    clientSock.emit('erase', word);
    clientSock.on('erased', () => {
      clientSock.disconnect();
    });
  });

  socket.on('disconnect', function () {
    console.log('user disconnected');
  });

});

http.listen(port, function () {
  console.log('listening on *:' + port);
});